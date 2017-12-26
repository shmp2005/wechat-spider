package com.zhongba01.service.impl;

import com.zhongba01.VerifyCodeException;
import com.zhongba01.dao.AuthorDao;
import com.zhongba01.dao.JobDao;
import com.zhongba01.dao.UserDao;
import com.zhongba01.dao.ArticleDao;
import com.zhongba01.domain.Author;
import com.zhongba01.domain.Job;
import com.zhongba01.domain.User;
import com.zhongba01.domain.Article;
import com.zhongba01.service.UserService;
import com.zhongba01.utils.DateUtil;
import com.zhongba01.utils.WebClientUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/13.
 */
@Service
public class UserServiceImpl implements UserService {
    private final static String GOU_ROOT = "http://weixin.sogou.com/weixin";
    private final static String WX_ROOT = "https://mp.weixin.qq.com";
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserDao userDao;

    @Autowired
    ArticleDao articleDao;

    @Autowired
    AuthorDao authorDao;

    @Autowired
    JobDao jobDao;

    @Override
    public void dumpUser(String weixin) throws VerifyCodeException {
        final String query = WebClientUtil.toUtf8(weixin);
        String url = GOU_ROOT + "?query=" + query + "&_sug_type_=&s_from=input&_sug_=y&type=1&ie=utf8";
        long startTime = System.currentTimeMillis();

        boolean hasNext = true;
        while (hasNext) {
            Document document = WebClientUtil.getDocument(url);
            Elements elements = document.select(".news-list2 > li");

            parseUsers(weixin, elements);

            Element nextPage = document.selectFirst("#pagebar_container #sogou_next");
            if (null == nextPage) {
                hasNext = false;
            } else {
                url = GOU_ROOT + nextPage.attr("href");
            }
        }
        LOGGER.info("done。秒数：" + (System.currentTimeMillis() - startTime) / 1000);

        long count = userDao.count();
        LOGGER.info("共有微信公众号：" + count);
    }

    /**
     * 解析微信公众号信息
     *
     * @param weixin   微信号
     * @param elements box集合
     */
    private void parseUsers(String weixin, Elements elements) throws VerifyCodeException {
        for (Element el : elements) {
            String avatar = el.selectFirst(".img-box img").attr("src");
            String nickname = el.selectFirst(".txt-box .tit").text().replace(" ", "");
            String weixinhao = el.selectFirst(".txt-box .info label[name='em_weixinhao']").text();
            if (!weixin.equals(weixinhao)) {
                continue;
            }

            String description = null, orgName = null;
            Timestamp lastPublish = null;
            Elements dlList = el.select("dl");
            for (Element dl : dlList) {
                if ("功能介绍：".equalsIgnoreCase(dl.selectFirst("dt").text())) {
                    description = dl.selectFirst("dd").text();
                }
                if ("微信认证：".equalsIgnoreCase(dl.selectFirst("dt").text())) {
                    orgName = dl.selectFirst("dd").text();
                }
                if ("最近文章：".equalsIgnoreCase(dl.selectFirst("dt").text())) {
                    lastPublish = DateUtil.parseTimestamp(dl.selectFirst("dd span script").html());
                }
            }

            User user = userDao.findByWeixin(weixin);

            if (null == user) {
                continue;
            }

            user.setName(nickname);
            user.setMemo(description);
            user.setOrgName(orgName);
            user.setLastPublish(lastPublish);
            user.setUpdatedAt(DateUtil.getUtcNow());
            userDao.save(user);

            String profileUrl = el.selectFirst(".txt-box .tit a[uigs]").attr("href");
            userProfile(weixin, profileUrl);
        }
    }

    @Override
    public void userProfile(String weixin, String profileUrl) throws VerifyCodeException {
        User user = userDao.findByWeixin(weixin);
        if (null == user) {
            return;
        }

        List<Article> articleList = new ArrayList<>(15);
        Document document = WebClientUtil.getDocument(profileUrl);
        Elements articleCards = document.select(".weui_msg_card_list .weui_msg_card");
        for (Element el : articleCards) {
            int seq = 1;
            //同一个card下面可能有多个box，即多篇文章
            for (Element box : el.select(".weui_media_box.appmsg")) {
                String msgId = box.attr("msgid");
                String title = box.selectFirst(".weui_media_title").text();
                Element copyRight = box.selectFirst(".weui_media_title span#copyright_logo");
                boolean isOrigin = (null != copyRight);
                if (isOrigin) {
                    title = title.substring(2);
                }

                long count = articleDao.countByUserIdAndMsgId(user.getId(), msgId);
                if (count > 0) {
                    LOGGER.warn("userId: " + user.getId() + ", msgId: " + msgId + " 已经存在，忽略。" + title);
                    LOGGER.warn("profileUrl: " + profileUrl);
                    continue;
                }

                String detailUrl = WX_ROOT + box.selectFirst(".weui_media_title").attr("hrefs");
                String digest = box.selectFirst(".weui_media_desc").text();
                //2017年11月28日 原创
                String date = box.selectFirst(".weui_media_extra_info").text();
                date = date.replace("原创", "").trim();
                Date pubDate = DateUtil.parseDate(date);

                Timestamp now = DateUtil.getUtcNow();
                Article article = new Article();
                article.setUserId(user.getId());
                article.setMsgId(msgId);
                article.setSeq(seq);
                article.setTitle(title);
                article.setOrigin(isOrigin);
                article.setPubTime(pubDate);
                article.setUrl(detailUrl);
                article.setDigest(digest);
                article.setCreatedAt(now);
                article.setUpdatedAt(now);
                articleList.add(article);

                seq += 1;
            }
        }

        for (Article ar : articleList) {
            articleDao.save(ar);

            Document doc = WebClientUtil.getDocument(ar.getUrl());
            String cssQuery = "#meta_content em.rich_media_meta.rich_media_meta_text";
            String authorName = null;
            for (Element el : doc.select(cssQuery)) {
                if (el.hasAttr("id")) {
                    continue;
                }
                authorName = el.text();
            }
            Element jsContent = doc.selectFirst("#js_content");
            if (null == jsContent) {
                LOGGER.warn("EMPTY js_content: " + ar.getUrl());
                continue;
            }

            if (!StringUtils.isEmpty(authorName)) {
                Author author = findOrCreateAuthor(authorName);
                ar.setAuthorId(author.getId());
            }
            ar.setContent(jsContent.html());
            articleDao.save(ar);

            //丢一个job
            createJob(ar.getId());
        }
    }

    @Override
    public List<User> findActives() {
        return userDao.findByActive(true);
    }

    private Author findOrCreateAuthor(String authorName) {
        Assert.notNull(authorName, "authorName isnull");

        Author author = authorDao.findByName(authorName);
        if (null == author) {
            author = new Author();
            Timestamp now = DateUtil.getUtcNow();
            author.setName(authorName);
            author.setScore(60L);
            author.setAliases("");
            author.setCreatedAt(now);
            author.setUpdatedAt(now);
            authorDao.save(author);
        }
        return author;
    }

    private void createJob(long articleId) {
        Job job = new Job();
        Timestamp now = DateUtil.getUtcNow();
        job.setPriority(100);

        String handler = "\"--- !ruby/object:Delayed::PerformableMethod\\n" +
                "object: !ruby/class 'Zbq::Wechat::Article'\\nmethod_name: " +
                ":save_images\\nargs:\\n- " + articleId + "\\n\"";
        job.setHandler(handler);
        job.setRunAt(now);
        job.setCreatedAt(now);
        job.setUpdatedAt(now);

        jobDao.save(job);
    }
}
