package com.grimar.feedy.adapters;

import android.os.Parcel;
import android.os.Parcelable;

public class StringListItem implements Parcelable {

    public static final Creator<StringListItem> CREATOR = new Creator<StringListItem>() {
        @Override
        public StringListItem createFromParcel(Parcel in) {
            return new StringListItem(in);
        }

        @Override
        public StringListItem[] newArray(int size) {
            return new StringListItem[size];
        }
    };

    public String unicode;
    private String str;
    private String url;

    public StringListItem(String str, String url) {
        this.str = str;
        this.url = url;
    }

    public StringListItem(String str, String url, String unicode) {
        this.str = str;
        this.url = url;
        this.unicode = unicode;
    }

    protected StringListItem(Parcel in) {
        str = in.readString();
        url = in.readString();
        unicode = in.readString();
    }

    public String getString() {
        return this.str;
    }

    public void setString(String str) {
        this.str = str;
    }


    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(str);
        out.writeString(url);
        out.writeString(unicode);
    }
}