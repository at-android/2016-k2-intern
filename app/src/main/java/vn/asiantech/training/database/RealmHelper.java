package vn.asiantech.training.database;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import vn.asiantech.training.model.Phone;

/**
 * Created by phuong on 15/12/2016.
 */

public class RealmHelper {
    private static RealmHelper mInstance;
    private Realm mRealm;
    private Context mContext;

    private RealmHelper(Context context) {
        mContext = context;
        Realm.init(context);
        mRealm = Realm.getDefaultInstance();
    }

    public static RealmHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new RealmHelper(context);
        }
        return mInstance;
    }

    public Realm getRealm() {
        return mRealm;
    }

    public List<Phone> getPhones() {
        return mRealm.where(Phone.class).findAll();
    }

    public Phone addPhone(Phone mPhone) {
        Phone phone;
        mRealm.beginTransaction();
        phone = mRealm.copyToRealmOrUpdate(mPhone);
        mRealm.commitTransaction();
        return phone;
    }
}
