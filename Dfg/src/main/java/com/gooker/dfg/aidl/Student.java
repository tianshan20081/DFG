package com.gooker.dfg.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sczhang on 15/6/28. 下午11:21
 * Package Name com.gooker.dfg.aidl
 * Project Name DFG
 */
public class Student implements Parcelable {

    private int age ;
    private String name ;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.age);
        dest.writeString(this.name);
    }

    public Student() {
    }

    protected Student(Parcel in) {
        this.age = in.readInt();
        this.name = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
