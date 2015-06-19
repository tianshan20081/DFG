package com.gooker.dfg.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private int id;
    private String name;
    private float price;

    public static final Creator<Product> CREATOR = new Creator<Product>() {

        @Override
        public Product[] newArray(int size) {
            // TODO Auto-generated method stub
            return new Product[size];
        }

        @Override
        public Product createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            return new Product(source);
        }
    };

    private Product(Parcel in) {
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {
        // TODO Auto-generated method stub
        id = in.readInt();
        name = in.readString();
        price = in.readFloat();
    }

    public Product() {
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeFloat(price);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
    }

}
