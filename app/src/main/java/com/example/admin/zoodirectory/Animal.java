package com.example.admin.zoodirectory;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 11/14/2017.
 */

public class Animal implements Parcelable{
    private String name;
    private String category;

    public Animal(String name, String category) {
        this.name = name;
        this.category = category;
    }

    protected Animal(Parcel in) {
        name = in.readString();
        category = in.readString();
    }

    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(category);
    }
}
