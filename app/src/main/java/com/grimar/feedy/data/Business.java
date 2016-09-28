package com.grimar.feedy.data;

import java.util.Arrays;
import java.util.List;

public class Business extends Category {

    public Business() {
        setPortalName(initPortalName());
        setPortalURL(initPortalUrl());
        setPortalEncoding(initPortalEncoding());
    }

    private List<String> initPortalName() {
        String[] portalName = new String[]{"Netokracija",
                "Lider",
                "Poslovni.hr",
                "Business.hr"
        };
        return Arrays.asList(portalName);
    }

    private List<String> initPortalUrl() {
        String[] portalName = new String[]{"http://feeds.netokracija.com/netokracija",
                "http://liderpress.hr/rss/",
                "http://www.poslovni.hr/feeds/najnovije.xml",
                "http://business.hr/rss.aspx?id=26"
        };
        return Arrays.asList(portalName);
    }

    private List<String> initPortalEncoding() {
        String[] portalName = new String[]{"UTF-8",
                "UTF-8",
                "UTF-8",
                "UTF-8"
        };
        return Arrays.asList(portalName);
    }
}
