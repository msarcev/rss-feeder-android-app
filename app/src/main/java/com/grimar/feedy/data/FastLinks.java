package com.grimar.feedy.data;

import java.util.Arrays;
import java.util.List;

public class FastLinks extends Category {


    public FastLinks() {
        setPortalName(initPortalName());
        setPortalURL(initPortalUrl());
        setPortalEncoding(initPortalEncoding());
    }

    private List<String> initPortalName() {
        String[] portalName = new String[]{"SuperSport",
                "Rezultati",
                "Njuskalo",
                "9gag"
        };
        return Arrays.asList(portalName);
    }

    private List<String> initPortalUrl() {
        String[] portalName = new String[]{"http://www.supersport.hr",
                "http://www.rezultati.com",
                "http://www.njuskalo.hr",
                "http://www.9gag.com"
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
