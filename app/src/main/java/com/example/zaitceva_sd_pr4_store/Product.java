package com.example.zaitceva_sd_pr4_store;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

    int id;
    String name;
    int price;
    int image;
    boolean box;
    Product(int id, String name, int price, int image, boolean box) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.box = box;
    }

    protected Product(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readInt();
        image = in.readInt();
        box = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeInt(image);
        dest.writeByte((byte) (box ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
