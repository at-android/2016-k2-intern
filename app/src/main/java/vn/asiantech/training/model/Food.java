package vn.asiantech.training.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by phuong on 17/11/2016.
 */

public class Food implements Parcelable {
    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };
    private String mName;
    private String mType;
    private String mCost;
    private int mPicture;

    protected Food(Parcel in) {
        mName = in.readString();
        mType = in.readString();
        mCost = in.readString();
        mPicture = in.readInt();
    }

    public Food(String mName, String mType, String mCost) {
        this.mName = mName;
        this.mType = mType;
        this.mCost = mCost;
        if (mType.equals("food")) {
            mPicture = 1;
        } else {
            if (mType.equals("drink")) {
                mPicture = 2;
            }
            if (mType.equals("other")) {
                mPicture = 3;
            }
        }
    }

    public Food() {
    }

    public Food(String mName, String mType, String mCost, int mPicture) {
        this.mName = mName;
        this.mType = mType;
        this.mCost = mCost;
        this.mPicture = mPicture;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmCost() {
        return mCost;
    }

    public void setmCost(String mCost) {
        this.mCost = mCost;
    }

    public int getmPicture() {
        return mPicture;
    }

    public void setmPicture(int mPicture) {
        this.mPicture = mPicture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeString(mType);
        parcel.writeString(mCost);
        parcel.writeInt(mPicture);
    }

}
