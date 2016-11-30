package vn.asiantech.training;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 30/11/2016.
 */

public class AppInfo {
    private String name;
    private Drawable icon;
    public AppInfo(){

    }

    public AppInfo(String name, Drawable icon) {
        this.name = name;
        this.icon = icon;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
