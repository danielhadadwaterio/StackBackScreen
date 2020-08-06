package com.water_io.stackscreens.entities;

import android.os.Bundle;

public class ScreenInfo {
    private int screenID;
    private String screenName;
    private Bundle bundle;

    public ScreenInfo(int screenID, String screenName, Bundle bundle) {
        this.screenID = screenID;
        this.screenName = screenName;
        this.bundle = bundle;
    }

    public int getScreenID() {
        return screenID;
    }

    public void setScreenID(int screenID) {
        this.screenID = screenID;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public String toString() {
        return "ScreenInfo{" +
                "screenID=" + screenID +
                ", screenName='" + screenName + '\'' +
                ", bundle=" + ((bundle == null) ? "empty" : "has data") +
                '}' + "\n";
    }
}
