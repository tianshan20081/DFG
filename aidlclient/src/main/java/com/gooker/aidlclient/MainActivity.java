package com.gooker.aidlclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.gooker.aidlserver.IStudentInterface;
import com.gooker.aidlserver.Student;

public class MainActivity extends Activity {
    private String TAG;
    private IStudentInterface mStudentService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mStudentService = IStudentInterface.Stub.asInterface(service);

            try {
                mStudentService.addStudent(20, "lisi");
                Student stu = mStudentService.getStudent("wangwu");
                Log.d(TAG, stu.toString());
                Toast.makeText(MainActivity.this, stu.toString(), Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mStudentService = null;
        }
    };


    public void callAidl(View view) {
        Intent stuService = new Intent("com.gooker.aidl");
//        bindService()
        bindService(stuService, mConn, BIND_AUTO_CREATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mConn) {
            unbindService(mConn);
        }
    }
}
