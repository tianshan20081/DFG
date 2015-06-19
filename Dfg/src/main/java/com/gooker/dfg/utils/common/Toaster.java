package com.gooker.dfg.utils.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class Toaster {
    public static void toast(Context context, String text, boolean isLong) {

        Toast.makeText(context, text, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, String text) {

        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void toastCenter(Context context, String text, boolean isLong) {

        Toast toast = Toast.makeText(context, text, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }

    public static void log(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static ProgressDialog getPro(Context context, String msg) {
        // TODO Auto-generated method stub
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage(msg);
        dialog.show();

        return dialog;
    }

    public static void dismiss(ProgressDialog dialog) {
        if (null != dialog) {
            dialog.dismiss();
            dialog = null;
        }
    }

}
