/**
 *
 */
package com.gooker.dfg.ui.apps;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.gooker.dfg.R;
import com.gooker.dfg.services.UploadAppsInfoService;


/**
 * May 19, 2014 9:50:02 AM 應用程序管理界面
 */
public class AppManagerUI extends Activity implements OnClickListener {
    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_apps_manager_home);

        this.findViewById(R.id.btnAllApps).setOnClickListener(this);
        this.findViewById(R.id.btnSysApps).setOnClickListener(this);
        this.findViewById(R.id.btnAppSize).setOnClickListener(this);
        this.findViewById(R.id.btnStartOtherApp).setOnClickListener(this);
        this.findViewById(R.id.btnUploadAppsInfo).setOnClickListener(this);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnAllApps:
                startActivity(new Intent(this, AllAppUI.class));
                break;
            case R.id.btnSysApps:
                startActivity(new Intent(this, SysAppsUI.class));
                break;
            case R.id.btnAppSize:
                startActivity(new Intent(this, AppSizeUI.class));
                break;
            case R.id.btnStartOtherApp:

                start2();
                // start();

                break;
            case R.id.btnUploadAppsInfo:
                Intent service = new Intent(AppManagerUI.this, UploadAppsInfoService.class);
                startService(service);
                break;
            default:
                break;
        }
    }

    private void start2() {
        // TODO Auto-generated method stub
        String packageName = "com.DeviceTest";
        Intent intent = new Intent();
        PackageManager packageManager = this.getPackageManager();
        intent = packageManager.getLaunchIntentForPackage(packageName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(intent);
    }

    private void start() {
        // TODO Auto-generated method stub
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        String packageName = "com.DeviceTest";
        String className = "FirstRun";
        ComponentName cn = new ComponentName(packageName, className);
        intent.setComponent(cn);
        startActivity(intent);
    }

}
