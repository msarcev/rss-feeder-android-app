package com.grimar.feedy.data;

import java.util.Arrays;
import java.util.List;

public class Lifestyle extends Category {

    public Lifestyle() {
        setPortalName(initPortalName());
        setPortalURL(initPortalUrl());
        setPortalEncoding(initPortalEncoding());
    }

    private List<String> initPortalName() {
        String[] portalName = new String[]{"magazin.hr",
                "24sata.hr",
                "Budi IN",
                "Moda.hr",
                "Svijet-Mode.com",
                "Zdrava Krava",
                "Zena.hr"
        };
        return Arrays.asList(portalName);
    }

    private List<String> initPortalUrl() {
        String[] portalName = new String[]{"http://www.net.hr/rss/feeds/magazin/index.xml",
                "http://www.24sata.hr/feeds/lifestyle.xml",
                "http://www.budi.in/feeds/najnovije.xml",
                "http://www.moda.hr/rss/rss.ashx?ID=3",
                "http://www.svijet-mode.com/feed/",
                "http://www.zdravakrava.hr/feeds/najnovije",
                "http://zena.hr/rss/"
        };
        return Arrays.asList(portalName);
    }

    private List<String> initPortalEncoding() {
        String[] portalName = new String[]{"UTF-8",
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
