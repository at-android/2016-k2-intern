package vn.asiantech.training.model;

/**
 * Created by phuong on 29/11/2016.
 */

public class Phone {
    private int mId;
    private String mName;
    private String mPhone;

    public Phone(int mId, String mName, String mPhone) {
        this.mId = mId;
        this.mName = mName;
        this.mPhone = mPhone;
    }

    public Phone() {
    }

    public Phone(String mName, String mPhone) {
        this.mName = mName;
        this.mPhone = mPhone;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }
}
