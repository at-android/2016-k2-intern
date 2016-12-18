package vn.asiantech.training.database;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import vn.asiantech.training.object.Address;

/**
 * Created by MaiManhDuy on 12/16/2016.
 */

public class RealmHander {
    public static RealmHander realmHander;
    private Realm realm;
    private Context mContext;

    public RealmHander(Context mContext) {
        this.mContext = mContext;
        Realm.init(mContext);
        realm = Realm.getDefaultInstance();
    }

    public static RealmHander getRealmHander(Context context) {
        if (realmHander == null) {
            realmHander = new RealmHander(context);
        }
        return realmHander;
    }

    public Realm getRealm() {
        return realm;
    }

    public List<Address> getAddress() {
        return realm.where(Address.class).findAll();
    }

    public Address addAddress(Address address) {
        Address address1 = null;
        realm.beginTransaction();
        address = realm.copyToRealmOrUpdate(address);
        realm.commitTransaction();
        return address;
    }
}
