package vn.asiantech.training.model;

/**
 * Created by phuong on 22/11/2016.
 */

public class NavDrawerItem {
    private boolean mShowNotify;
    private String mTitle;

    public NavDrawerItem(boolean mShowNotify, String mTitle) {
        this.mShowNotify = mShowNotify;
        this.mTitle = mTitle;
    }

    public NavDrawerItem() {
    }

    public boolean ismShowNotify() {
        return mShowNotify;
    }

    public void setmShowNotify(boolean mShowNotify) {
        this.mShowNotify = mShowNotify;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
