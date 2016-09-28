package com.grimar.feedy.rss;

import java.util.List;
import java.util.Vector;


public class RSSFeed {

    private String title = null;
    private String description = null;
    private String link = null;
    private String pubdate = null;
    private List<RSSItem> itemList;

    public RSSFeed() {
        itemList = new Vector<RSSItem>(0);
    }

    public void addItem(RSSItem item) {
        itemList.add(item);
    }

    public RSSItem getItem(int location) {
        return itemList.get(location);
    }

    public List<RSSItem> getList() {
        return itemList;
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

}
