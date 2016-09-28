package com.grimar.feedy.rss;

public class RSSItem {

    private String title = null;
    private String description = null;
    private String link = null;
    private String pubdate = null;

    public RSSItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        title = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        description = value;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String value) {
        link = value;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String value) {
        pubdate = value;
    }

    @Override
    public String toString() {
// TODO Auto-generated method stub
        return title;
    }
}