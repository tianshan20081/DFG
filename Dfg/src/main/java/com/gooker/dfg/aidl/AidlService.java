package com.gooker.dfg.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AidlService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return new AidlServiceImpl();
    }

    public class AidlServiceImpl extends IMyService.Stub {

        @Override
        public String getValue() throws RemoteException {
            // TODO Auto-generated method stub
            return "Hello Aoeng !";
        }

    }
}
