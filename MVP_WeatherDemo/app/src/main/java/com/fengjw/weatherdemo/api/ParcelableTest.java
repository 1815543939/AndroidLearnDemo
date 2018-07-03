package com.fengjw.weatherdemo.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fengjw on 2018/6/28.
 */

public class ParcelableTest implements Parcelable {

    class User{

    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public User[] newArray(int size) {
            return new User[0];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
