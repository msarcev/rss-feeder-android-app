package com.grimar.feedy.data;

import java.util.Arrays;
import java.util.List;

public class Tech extends Category {

    public Tech() {
        setPortalName(initPortalName());
        setPortalURL(initPortalUrl());
        setPortalEncoding(initPortalEncoding());
    }

    private List<String> initPortalName() {
        String[] portalName = new String[]{"Android Hrvatska",
                "Bug.hr",
                "Mob.hr",
                "IT Novosti",
                "PC Chip",
                "Vidi.hr"
        };
        return Arrays.asList(portalName);
    }

    private List<String> initPortalUrl() {
        String[] portalName = new String[]{"http://pipes.yahoo.com/pipes/pipe.run?_id=c3a1ffa456eac391f299af120fea589d&_render=rss",
                "http://www.bug.hr/rss/vijesti/",
                "http://mob.hr/feed/",
                "http://pipes.yahoo.com/pipes/pipe.run?_id=b4c0ecf4858e10a37643235ede477ca4&_render=rss",
                "http://www.pcchip.hr/feed",
                "http://www.vidi.hr/rss/feed/vidi"
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
