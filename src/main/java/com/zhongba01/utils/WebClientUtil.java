package com.zhongba01.utils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/13.
 */
public final class WebClientUtil {

    public static Document getDocument(String url) {
        return getDocument(url, 3);
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
        try (WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
            System.out.println("getDocument: " + url);
            Thread.sleep(seconds * 1000);

            htmlPage = webClient.getPage(url);
            return Jsoup.parse(htmlPage.asXml());
        } catch (IOException | InterruptedException e) {
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
}
