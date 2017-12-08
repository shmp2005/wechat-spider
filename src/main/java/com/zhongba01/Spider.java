package com.zhongba01;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * @ author: tangjianhua
 * @ date: 2017/12/8.
 */
public final class Spider {

    private final static String WX_HOST = "https://mp.weixin.qq.com";

    public static void main(String[] args) throws IOException {
        //淡水value 公众号的文章
//        final String url = "https://mp.weixin.qq.com/profile?src=3&timestamp=1512720829&ver=1&signature=Aatz6kC2L38Usa2AYoN61yWg6gXnqy-ZamTLrBsvCU41yrxl3nfW8nKEA2fj*t8sIg0nvAr1tigN*K9LLfd0sQ==";
        final WebClient webClient = new WebClient(BrowserVersion.CHROME);

        final String query = URLEncoder.encode("中巴价值投资研习社", "utf-8");
        final String url = "http://weixin.sogou.com/weixin?type=1&query=" + query + "&ie=utf8&s_from=input&_sug_=n&_sug_type_=";
        final HtmlPage page1 = webClient.getPage(url);
        Document document1 = Jsoup.parse(page1.asXml());
        String url1 = document1.selectFirst(".tit a").attr("href");

        final HtmlPage page = webClient.getPage(url1);

        Document document = Jsoup.parse(page.asXml());
        Elements elements = document.select(".weui_msg_card");
        for (Element el : elements) {
            String nextUrl = parseMsgCard(el);
//            String content = parseArticle(webClient, nextUrl);
//            System.out.println(content);
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }

        webClient.close();
    }

    private static String parseMsgCard(Element msgCardElement) {
        String date = msgCardElement.selectFirst(".weui_msg_card_hd").text();
        System.out.println(date);
        String title = msgCardElement.selectFirst("h4.weui_media_title").text();
        System.out.println(title);
        String nextUrl = msgCardElement.selectFirst("h4.weui_media_title").attr("hrefs");
        String desc = msgCardElement.selectFirst(".weui_media_desc").text();
        System.out.println(WX_HOST + nextUrl);
        System.out.println(desc);
        System.out.println("=========================================================================");
        return WX_HOST + nextUrl;
    }

    private static String parseArticle(WebClient webClient, String articleUrl) throws IOException {
        final HtmlPage page = webClient.getPage(articleUrl);
        Document document = Jsoup.parse(page.asXml());
        return document.selectFirst(".rich_media_content").html();
    }
}
