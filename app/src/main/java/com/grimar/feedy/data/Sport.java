package com.grimar.feedy.data;

import java.util.Arrays;
import java.util.List;

public class Sport extends Category {

    public Sport() {
        setPortalName(initPortalName());
        setPortalURL(initPortalUrl());
        setPortalEncoding(initPortalEncoding());
    }

    private List<String> initPortalName() {
        String[] portalName = new String[]{"Sportski.net",
                "Sportske.hr",
                "24sata.hr",
                "Euronogomet",
                "Gol.hr"
        };
        return Arrays.asList(portalName);
    }

    private List<String> initPortalUrl() {
        String[] portalName = new String[]{"http://sportski.net.hr/rss/index.xml",
                "http://pipes.yahoo.com/pipes/pipe.run?_id=2829337203bbea8ea680bf92f1fbff5d&_render=rss",
                "http://www.24sata.hr/feeds/sport.xml",
                "http://www.euronogomet.com/index.php?format=feed&type=rss",
                "http://gol.dnevnik.hr/lbin/rss.php"
        };
        return Arrays.asList(portalName);
    }

    private List<String> initPortalEncoding() {
        String[] portalName = new String[]{"UTF-8",
                "UTF-8",
                "UTF-8",
                "UTF-8",
                "UTF-8",
        };
        return Arrays.asList(portalName);
    }
}
