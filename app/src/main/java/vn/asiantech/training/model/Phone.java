package vn.asiantech.training.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by phuong on 29/11/2016.
 */

public class Phone extends RealmObject {
    @PrimaryKey
    private String mId;
    private String mName;
    private String mPhone;

    public Phone(String mId, String mName, String mPhone) {
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

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String mPhone) {
        this.mPhone = mPhone;
    }
}
