package com.grimar.feedy.data;

import java.util.Arrays;
import java.util.List;

public class Fun extends Category {

    public Fun() {
        setPortalName(initPortalName());
        setPortalURL(initPortalUrl());
        setPortalEncoding(initPortalEncoding());
    }

    private List<String> initPortalName() {
        String[] portalName = new String[]{"24sata.hr",
                "Hot.hr",
                "Showbiz.hr",
                "Klix.ba",
                "Story.hr",
                "News-Bar.hr"
        };
        return Arrays.asList(portalName);
    }

    private List<String> initPortalUrl() {
        String[] portalName = new String[]{"http://www.24sata.hr/feeds/show.xml",
                "http://feeds.net.hr/c/33283/f/564933/index.rss",
                "http://pipes.yahoo.com/pipes/pipe.run?_id=e5694b2caeaf1df0155e54860504b38f&_render=rss",
                "http://www.klix.ba/rss/magazin/showbiz",
                "http://www.story.hr/feed",
                "http://www.news-bar.hr/rss"
        };
        return Arrays.asList(portalName);
    }

    private List<String> initPortalEncoding() {
        String[] portalName = new String[]{"UTF-8",
                "UTF-8",
                "UTF-8",
                "UTF-8",
                "UTF-8",
                "UTF-8"
        };
        return Arrays.asList(portalName);
    }
}
