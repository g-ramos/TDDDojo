package org.swnat.basecrawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;

public class CrawlerTest {

    private Connection mockedConnection;
    private Document mockedHtmlDoc;
    private MockedStatic<Jsoup> jsoupMockedStatic;

    private Crawler baseCrawler = new Crawler();


    private void mockJsoupResponse() throws IOException {
        jsoupMockedStatic = Mockito.mockStatic(Jsoup.class);

        mockedHtmlDoc = Mockito.mock(Document.class);
        Mockito.doReturn("Hello World").when(mockedHtmlDoc).title();

        mockedConnection = Mockito.mock(Connection.class);
        Mockito.doReturn(mockedHtmlDoc).when(mockedConnection).get();

        jsoupMockedStatic.when(() -> Jsoup.connect(any()))
                .thenReturn(mockedConnection);
    }

    /** This is not a unit test! This is only an example to see that the mocking worked */
    @Test
    public void testChotito() throws IOException {
        mockJsoupResponse();

        String pageTitle = Crawler.getLinkFromWikipedia();
        Assert.assertEquals(pageTitle, "Hello World");

        jsoupMockedStatic.close();
    }

    /** This is an integration test! */
    @Test
    public void testGetTitle() throws IOException {
        CrawlerExample ce = new CrawlerExample();
        String title = ce.getTitle("https://en.wikipedia.org/wiki/Main_Page");

        Assert.assertEquals(title, "Wikipedia, the free encyclopedia");
    }
}
