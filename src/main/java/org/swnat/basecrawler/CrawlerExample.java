package org.swnat.basecrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class CrawlerExample {

    public String getTitle(String url) throws IOException {
        Document doc = (Document) Jsoup.connect(url).get();
        return doc.title();
    }
}
