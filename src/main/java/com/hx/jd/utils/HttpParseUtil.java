package com.hx.jd.utils;

import com.hx.jd.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class HttpParseUtil {
    public static void main(String[] args) throws IOException {
        new HttpParseUtil().parseJD("java").forEach(System.out::println);
    }

    public List<Content> parseJD(String name) throws IOException {
        //获取请求
        String url = "https://search.jd.com/Search?keyword="+name;
        //解析网页
        Document document = Jsoup.parse(new URL(url), 3000);
        //获取元素
        Element element = document.getElementById("J_goodsList");
        //获取所有li标签,因为货物都在Li标签里面
        Elements elements = element.getElementsByTag("li");

        ArrayList<Content> contents = new ArrayList<>();

        for (Element e : elements) {
            //img标签的第一个元素的src属性
            String image = e.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = e.getElementsByClass("p-price").eq(0).text();
            String title = e.getElementsByClass("p-name").eq(0).text();
            Content content = new Content();
            content.setImg(image);
            content.setName(title);
            content.setPrice(price);
            contents.add(content);
        }

        return contents;
    }
}
