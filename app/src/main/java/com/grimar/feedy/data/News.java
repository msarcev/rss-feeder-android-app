package com.grimar.feedy.data;

import java.util.Arrays;
import java.util.List;

public class News extends Category {

    public News() {
        setPortalName(initPortalName());
        setPortalURL(initPortalUrl());
        setPortalEncoding(initPortalEncoding());
    }

    private List<String> initPortalName() {
        String[] portalName = new String[]{"Index.hr",
                "Net.hr",
                "Jutarnji List",
                "24 sata",
                "Vecernji List",
                "Dnevnik.hr",
                "Dalmacija News",
                "Glas Slavonije",
                "Slobodna"
        };
        return Arrays.asList(portalName);
    }

    private List<String> initPortalUrl() {
        String[] portalName = new String[]{"http://www.index.hr/vijesti/rss.ashx",
                "http://www.net.hr/rss/feeds/naslovnica/index.xml",
                "http://pipes.yahoo.com/pipes/pipe.run?_id=f04c0a7876b2485bbd1bf1a1d916b39f&_render=rss",
                "http://www.24sata.hr/feeds/news.xml",
                "http://www.vecernji.hr/rss/",
                "http://rss.dnevnik.hr/index.rss",
                "http://pipes.yahoo.com/pipes/pipe.run?_id=0ba96a902e1348a4ee7bc065605c5630&_render=rss",
                "http://pipes.yahoo.com/pipes/pipe.run?_id=389cd138a83d539e017e385dea1e286b&_render=rss",
                "http://www.slobodnadalmacija.hr/RssTop.aspx"
        };
        return Arrays.asList(portalName);
    }

    private List<String> initPortalEncoding() {
        String[] portalName = new String[]{"ISO-8859-2",
                "UTF-8",
                "UTF-8",
                "UTF-8",
                "UTF-8",
                "UTF-8",
                "UTF-8",
                "UTF-8",
                "UTF-8"
        };
        return Arrays.asList(portalName);
    }

}
