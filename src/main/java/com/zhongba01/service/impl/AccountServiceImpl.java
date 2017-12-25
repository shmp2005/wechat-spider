package com.zhongba01.service.impl;

import com.zhongba01.dao.AccountDao;
import com.zhongba01.dao.ArticleDao;
import com.zhongba01.domain.Account;
import com.zhongba01.domain.Article;
import com.zhongba01.service.AccountService;
import com.zhongba01.utils.WebClientUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/13.
 */
@Service
public class AccountServiceImpl implements AccountService {
    private final static String GOU_ROOT = "http://weixin.sogou.com/weixin";
    private final static String WX_ROOT = "https://mp.weixin.qq.com";
    private final static Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    AccountDao accountDao;

    @Autowired
    ArticleDao articleDao;

    @Override
    public void dumpAccounts(String keywords) {
        final String query = WebClientUtil.toUtf8(keywords);
        String url = GOU_ROOT + "?query=" + query + "&_sug_type_=&s_from=input&_sug_=y&type=1&ie=utf8";
        long startTime = System.currentTimeMillis();

        boolean hasNext = true;
        while (hasNext) {
            Document document = WebClientUtil.getDocument(url);
            Elements elements = document.select(".news-list2 > li");

            parseAccounts(elements);

            Element nextPage = document.selectFirst("#pagebar_container #sogou_next");
            if (null == nextPage) {
                hasNext = false;
            } else {
                url = GOU_ROOT + nextPage.attr("href");
            }
        }
        LOGGER.info("done。秒数：" + (System.currentTimeMillis() - startTime) / 1000);

        long count = accountDao.count();
        LOGGER.info("共有微信公众号：" + count);
    }

    /**
     * 解析微信公众号信息
     *
     * @param elements box集合
     */
    private void parseAccounts(Elements elements) {
        for (Element el : elements) {
            String avatar = el.selectFirst(".img-box img").attr("src");
            String nickname = el.selectFirst(".txt-box .tit").text().replace(" ", "");
            String wxAccount = el.selectFirst(".txt-box .info label[name='em_weixinhao']").text();

            String description = null, vname = null;
            Date lastPublish = null;
            Elements dlList = el.select("dl");
            for (Element dl : dlList) {
                if ("功能介绍：".equalsIgnoreCase(dl.selectFirst("dt").text())) {
                    description = dl.selectFirst("dd").text();
                }
                if ("微信认证：".equalsIgnoreCase(dl.selectFirst("dt").text())) {
                    vname = dl.selectFirst("dd").text();
                }
                if ("最近文章：".equalsIgnoreCase(dl.selectFirst("dt").text())) {
                    lastPublish = parseTime(dl.selectFirst("dd span script").html());
                }
            }

            Account account = accountDao.findByAccount(wxAccount);
            Timestamp now = getSqlNow();
            if (null == account) {
                account = new Account();
                account.setNickname(nickname);
                account.setAccount(wxAccount);
                account.setDescription(description);
                account.setVname(vname);
                account.setAvatar(avatar);
                account.setActive(true);
                account.setLastPublish(lastPublish);

                account.setCreatedAt(now);
                account.setUpdatedAt(now);
                accountDao.save(account);
            } else {
                account.setDescription(description);
                account.setLastPublish(lastPublish);
                account.setUpdatedAt(now);
                accountDao.save(account);
            }

            String profileUrl = el.selectFirst(".txt-box .tit a[uigs]").attr("href");
            accountProfile(wxAccount, profileUrl);
        }
    }

    @Override
    public void accountProfile(String wxAccount, String profileUrl) {
        Account account = accountDao.findByAccount(wxAccount);

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

                long count = articleDao.countByMsgId(msgId);
                if (count > 0) {
                    LOGGER.warn("msgId: " + msgId + " 已经存在，忽略。" + title);
                    continue;
                }

                Element post = box.selectFirst("span.weui_media_hd");
                String postUrl = null;
                if (null != post) {
                    String[] array = post.attr("style").split("[()]");
                    if (array.length == 2) {
                        postUrl = array[1];
                    }
                }

                String detailUrl = WX_ROOT + box.selectFirst(".weui_media_title").attr("hrefs");
                String digest = box.selectFirst(".weui_media_desc").text();
                //2017年11月28日 原创
                String date = box.selectFirst(".weui_media_extra_info").text();
                date = date.replace("原创", "").trim();
                Date pubDate = parseDate(date);

                Timestamp now = getSqlNow();
                Article article = new Article();
                article.setAccountId(account.getId());
                article.setMsgId(msgId);
                article.setSeq(seq);
                article.setTitle(title);
                article.setOrigin(isOrigin);
                article.setPubDate(pubDate);
                article.setUrl(detailUrl);
                article.setPostUrl(postUrl);
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
            String author = null;
            for (Element el : doc.select(cssQuery)) {
                if (el.hasAttr("id")) {
                    continue;
                }
                author = el.text();
            }
            Element jsContent = doc.selectFirst("#js_content");
            if (null == jsContent) {
                continue;
            }

            ar.setAuthor(author);
            ar.setContent(jsContent.html());
            articleDao.save(ar);
        }
    }

    /**
     * 解析时间
     *
     * @param string，eg: "document.write(timeConvert('1474348154'))"
     * @return datetime
     */
    private Date parseTime(String string) {
        final int expectedLength = 3;
        String[] array = string.split("'");
        if (array.length == expectedLength) {
            return new Date(Long.valueOf(array[1]) * 1000);
        }
        return null;
    }

    /**
     * 解析日期字符串为LocalDate
     *
     * @param string 2017年11月28日
     * @return LocalDate
     */
    private Date parseDate(String string) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");
            java.util.Date date = sdf.parse(string);
            return new Date(date.getTime());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 得到当前的UTC时间戳
     *
     * @return 当前的UTC时间戳
     */
    private Timestamp getSqlNow() {
        long hour8Offset = 8 * 3600;
        return new Timestamp(new java.util.Date().getTime() - hour8Offset);
    }
}
