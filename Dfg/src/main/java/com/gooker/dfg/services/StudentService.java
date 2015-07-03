package com.gooker.dfg.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.gooker.dfg.aidl.IStudentService;
import com.gooker.dfg.aidl.Student;

public class StudentService extends Service {
    public StudentService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        IStudentService sssss = IStudentService.Stub.asInterface();
//        sssss.getInfo(20,"")
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private IStudentService.Stub mBinder = new IStudentService.Stub() {
        @Override
        public int getAge(String name) throws RemoteException {
            return 0;
        }

        @Override
        public Student getInfo(int age, String name) throws RemoteException {
            return null;
        }
    };
}
