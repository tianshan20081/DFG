package com.gooker.aidlserver;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by sczhang on 15/6/29. 上午9:58
 * Package Name com.gooker.aidlserver
 * Project Name DFG
 */
public class Student implements Parcelable {
    private int age ;
    private String name ;
    private Date birthday ;
    private boolean sex;



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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.age);
        dest.writeString(this.name);
        dest.writeLong(birthday != null ? birthday.getTime() : -1);
        dest.writeByte(sex ? (byte) 1 : (byte) 0);
    }

    public Student() {
    }

    protected Student(Parcel in) {
        this.age = in.readInt();
        this.name = in.readString();
        long tmpBirthday = in.readLong();
        this.birthday = tmpBirthday == -1 ? null : new Date(tmpBirthday);
        this.sex = in.readByte() != 0;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", sex=" + sex +
                '}';
    }
}
