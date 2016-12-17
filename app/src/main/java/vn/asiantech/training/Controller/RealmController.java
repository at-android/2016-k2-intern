package vn.asiantech.training.Controller;

import android.content.Context;
import java.util.List;
import io.realm.Realm;
import vn.asiantech.training.Model.RealmPeople;

/**
 * Created by Administrator on 17/12/2016.
 */

public class RealmController {
    private Realm mRealm;
    private Context mContext;

    public RealmController(Context context) {
        mContext = context;
        Realm.init(mContext);
        mRealm = Realm.getDefaultInstance();
    }

    public List<RealmPeople> getContact() {
        return mRealm.where(RealmPeople.class).findAll();
    }

    public void addPeople(RealmPeople people) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(people);
        mRealm.commitTransaction();
    }
}
