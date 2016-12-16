package vn.asiantech.training.controller;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import vn.asiantech.training.model.People;

/**
 * Created by HoangDuy on 16/12/2016.
 */
public class RealmController {
    private Realm mRealm;
    private Context mContext;

    public RealmController(Context context) {
        mContext = context;
        Realm.init(mContext);
        mRealm = Realm.getDefaultInstance();
    }

    public List<People> getContact() {
        return mRealm.where(People.class).findAll();
    }

    public void addPeople(People contact) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(contact);
        mRealm.commitTransaction();
    }

}
