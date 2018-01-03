package com.zhongba01.utils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.IncorrectnessListener;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.DefaultJavaScriptErrorListener;
import com.zhongba01.VerifyCodeException;
import org.apache.commons.lang3.RandomUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/13.
 */
public final class WebClientUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebClientUtil.class);

    public static Document getDocument(String url) throws VerifyCodeException {
        int seconds = RandomUtils.nextInt(45, 60);
        Document document = getDocument(url, seconds);

        if (null != document.selectFirst(".verifycode")) {
//            LOGGER.info(document.html());
            throw new VerifyCodeException();
        }

        return document;
    }

    /**
     * 延迟抓取URL
     *
     * @param url     路径
     * @param seconds 延迟秒数
     * @return 文档对象模型
     */
    public static Document getDocument(String url, int seconds) {
        final HtmlPage htmlPage;
        try (WebClient webClient = simpleWebClient()) {
            LOGGER.info(webClient.getBrowserVersion().getNickname() + "; getDoc: " + url);
            LOGGER.info("Will sleep: " + seconds + " 秒。");
            Thread.sleep(seconds * 1000);

            htmlPage = webClient.getPage(url);
            return Jsoup.parse(htmlPage.asXml());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Document(url);
    }

    public static String toUtf8(String string) {
        try {
            return URLEncoder.encode(string, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return string;
        }
    }

    private static WebClient simpleWebClient() {
        WebClient webClient = new WebClient(randBrowserVersion());
        webClient.setCssErrorHandler(new SilentCssErrorHandler());
        webClient.setIncorrectnessListener(new IncorrectnessListener() {
            @Override
            public void notify(String s, Object o) {
                //do nothing
            }
        });
        webClient.setJavaScriptErrorListener(new DefaultJavaScriptErrorListener() {
            @Override
            public void loadScriptError(HtmlPage page, URL scriptUrl, Exception exception) {
                //do nothing
            }

            @Override
            public void malformedScriptURL(HtmlPage page, String url, MalformedURLException malformedURLException) {
                //do nothing
            }
        });
        return webClient;
    }

    private static String getIP() {
        String[] segments = new String[]{
                RandomUtils.nextInt(110, 216) + "",
                RandomUtils.nextInt(29, 200) + "",
                RandomUtils.nextInt(10, 255) + "",
                RandomUtils.nextInt(1, 200) + ""};
        return String.join(".", segments);
    }

    private static BrowserVersion randBrowserVersion() {
        BrowserVersion[] browsers = new BrowserVersion[]{
                BrowserVersion.CHROME,
                BrowserVersion.FIREFOX_45,
                BrowserVersion.FIREFOX_52
        };
        int index = RandomUtils.nextInt(0, browsers.length);
        return browsers[index];
    }
}
