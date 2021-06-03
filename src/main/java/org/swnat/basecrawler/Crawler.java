package org.swnat.basecrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Crawler {
    public static void main(String[] args) {

        try {
            getLinkFromWikipedia();
            getProductInfoFromArete();
            getLinksAndNavigate();

        } catch (Exception e) {
            System.out.println("Pasó algo malo 🤩 😊");
            System.out.println(e);
        }

    }

    private static void getLinksAndNavigate() throws IOException {
        System.out.println("\nvisiting links...");
        Document doc3 = Jsoup.connect("http://www.precio.digital/").get();

        Elements links = doc3.select("a");

        // print the headers of every page
        for (Element link : links) {
            // visit link and print header
            Document linkDoc = Jsoup.connect(link.attr("abs:href")).get();
            System.out.printf("link (%s) [%s]\n", link.attr("href"), linkDoc.title());
        }
    }

    private static void getProductInfoFromArete() throws IOException {
        System.out.println("\ngetting product info from Areté...");
        Document doc = (Document) Jsoup.connect("https://www.arete.com.py/bombones-nestle-especialidades-surtidos-251-gr-p81760").get();
//        System.out.println("H"doc.title());
        String nombre = doc.select("posted_in categories").text();
        String precio = doc.getElementById("producto-precio").text();
        System.out.printf("%s\t%s\n", nombre, precio);
    }

    public static String getLinkFromWikipedia() throws IOException {
        System.out.println("\ngetting link from wikipedia...");
        Document doc = (Document) Jsoup.connect("https://en.wikipedia.org/").get();
        System.out.println(doc.title());
        return doc.title();
//        Elements newsHeadlines = doc.select("#mp-itn b a");
//        for (Element headline : newsHeadlines) {
//            System.out.printf("%s\n\t%s\n",
//                    headline.attr("title"), headline.absUrl("href"));
//        }
    }
}
