package com.grimar.feedy.data;

import java.util.ArrayList;
import java.util.List;

public abstract class Category {

    private static List<String> portalName = new ArrayList<>();
    private static List<String> portalURL = new ArrayList<>();
    private static List<String> portalEncoding = new ArrayList<>();

    public static List<String> getPortalName() {
        return portalName;
    }

    public static void setPortalName(List<String> portalName) {
        Category.portalName = portalName;
    }

    public static List<String> getPortalURL() {
        return portalURL;
    }

    public static void setPortalURL(List<String> portalURL) {
        Category.portalURL = portalURL;
    }

    public static List<String> getPortalEncoding() {
        return portalEncoding;
    }

    public static void setPortalEncoding(List<String> portalEncoding) {
        Category.portalEncoding = portalEncoding;
    }
}
