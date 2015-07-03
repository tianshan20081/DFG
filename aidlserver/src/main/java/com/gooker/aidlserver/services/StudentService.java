package com.gooker.aidlserver.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.gooker.aidlserver.IStudentInterface;
import com.gooker.aidlserver.Student;

public class StudentService extends Service {
    private String TAG = "StudentService";
    public StudentService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    private IStudentInterface.Stub mBinder = new IStudentInterface.Stub() {
        @Override
        public void addStudent(int age, String name) throws RemoteException {
            Log.d(TAG,"addStudent age :\t"+age+"\t name:\t"+name);
        }

        @Override
        public Student getStudent(String name) throws RemoteException {
            Student stu = new Student();
            stu.setName("zhangsan");
            stu.setAge(20);
            return stu;
        }
    };
}
