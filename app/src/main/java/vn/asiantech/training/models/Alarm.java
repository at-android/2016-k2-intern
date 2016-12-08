package vn.asiantech.training.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by phuong on 01/12/2016.
 */

public class Alarm implements Parcelable {
    public static final Creator<Alarm> CREATOR = new Creator<Alarm>() {
        @Override
        public Alarm createFromParcel(Parcel in) {
            return new Alarm(in);
        }

        @Override
        public Alarm[] newArray(int size) {
            return new Alarm[size];
        }
    };
    private String mHour;
    private String mMin;
    private String mRepeart;
    private boolean mStatus;
    private String mRepeartChar;
    private int mId;

    protected Alarm(Parcel in) {
        mHour = in.readString();
        mMin = in.readString();
        mRepeart = in.readString();
        mStatus = in.readByte() != 0;
        mRepeartChar = in.readString();
        mId = in.readInt();
    }

    public Alarm(String mHour, String mMin, String mRepeart, boolean mStatus, String mRepeartChar) {
        this.mHour = mHour;
        this.mMin = mMin;
        this.mRepeart = mRepeart;
        this.mStatus = mStatus;
        this.mRepeartChar = mRepeartChar;
    }

    public Alarm() {
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmHour() {
        return mHour;
    }

    public void setmHour(String mHour) {
        this.mHour = mHour;
    }

    public String getmMin() {
        return mMin;
    }

    public void setmMin(String mMin) {
        this.mMin = mMin;
    }

    public String getmRepeart() {
        return mRepeart;
    }

    public void setmRepeart(String mRepeart) {
        this.mRepeart = mRepeart;
    }

    public boolean ismStatus() {
        return mStatus;
    }

    public void setmStatus(boolean mStatus) {
        this.mStatus = mStatus;
    }

    public String getmRepeartChar() {
        return mRepeartChar;
    }

    public void setmRepeartChar(String mRepeartChar) {
        this.mRepeartChar = mRepeartChar;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "mHour='" + mHour + '\'' +
                ", mMin='" + mMin + '\'' +
                ", mRepeart='" + mRepeart + '\'' +
                ", mStatus=" + mStatus +
                ", mRepeartChar='" + mRepeartChar + '\'' +
                ", mId=" + mId +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mHour);
        parcel.writeString(mMin);
        parcel.writeString(mRepeart);
        parcel.writeByte((byte) (mStatus ? 1 : 0));
        parcel.writeString(mRepeartChar);
        parcel.writeInt(mId);
    }
}
