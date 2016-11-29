package vn.asiantech.training.model;

import android.os.Parcel;

/**
 * Created by phuong on 29/11/2016.
 */

public class Contact {
    private String mName;
    private String mEmail;
    private String mPassword;

    public Contact(String mName, String mEmail, String mPassword) {
        this.mName = mName;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
    }

    protected Contact(Parcel in) {
        mName = in.readString();
        mEmail = in.readString();
        mPassword = in.readString();
    }

    public Contact() {
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

}
