package com.gooker.dfg.ui.phonegap;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import com.phonegap.api.PhonegapActivity;
import com.phonegap.api.Plugin;
import com.phonegap.api.PluginResult;

/**
 * 扩展二维码扫描的phonegap类实现 实现原理如下： 1.使用phonegap的js类库实现通过插件调用相关的Plugin java类。 2.plugin调用zxing相关的二维码扫码的方法实现。 3.如果调用zxing没有安装，到google下载相关的zxing apk安装，并调用对应的intent实现。
 * <p/>
 * This calls out to the ZXing barcode reader and returns the result.
 */
public class BarcodeScanner extends Plugin {
    public static final int REQUEST_CODE = 0x0ba7c0de;

    public static final String defaultInstallTitle = "Install Barcode Scanner?";
    public static final String defaultInstallMessage = "This requires the free Barcode Scanner app. Would you like to install it now?";
    public static final String defaultYesString = "Yes";
    public static final String defaultNoString = "No";

    public String callback;

    /**
     * Constructor.
     */
    public BarcodeScanner() {
    }

    /**
     * 用于plugin相关的方法，用于暴露相关的方法使用。
     * <p/>
     * Executes the request and returns PluginResult.
     *
     * @param action     The action to execute.
     * @param args       JSONArray of arguments for the plugin.
     * @param callbackId The callback id used when calling back into JavaScript.
     * @return A PluginResult object with a status and message.
     */
    public PluginResult execute(String action, JSONArray args, String callbackId) {
        this.callback = callbackId;

        try {
            if (action.equals("encode")) {
                String type = null;
                if (args.length() > 0) {
                    type = args.getString(0);
                }

                String data = null;
                if (args.length() > 1) {
                    data = args.getString(1);
                }

                String installTitle = defaultInstallTitle;
                if (args.length() > 2) {
                    installTitle = args.getString(2);
                }

                String installMessage = defaultInstallMessage;
                if (args.length() > 3) {
                    installMessage = args.getString(3);
                }

                String yesString = defaultYesString;
                if (args.length() > 4) {
                    yesString = args.getString(4);
                }

                String noString = defaultNoString;
                if (args.length() > 5) {
                    noString = args.getString(5);
                }

                // if data.TypeOf() == Bundle, then call
                // encode(type, Bundle)
                // else
                // encode(type, String)
                this.encode(type, data, installTitle, installMessage, yesString, noString);
            } else if (action.equals("scan")) {
                String barcodeTypes = null;
                if (args.length() > 0) {
                    barcodeTypes = args.getString(0);
                }

                String installTitle = defaultInstallTitle;
                if (args.length() > 1) {
                    installTitle = args.getString(1);
                }

                String installMessage = defaultInstallMessage;
                if (args.length() > 2) {
                    installMessage = args.getString(2);
                }

                String yesString = defaultYesString;
                if (args.length() > 3) {
                    yesString = args.getString(3);
                }

                String noString = defaultNoString;
                if (args.length() > 4) {
                    noString = args.getString(4);
                }

                scan(barcodeTypes, installTitle, installMessage, yesString, noString);
            } else {
                return new PluginResult(PluginResult.Status.INVALID_ACTION);
            }
            PluginResult r = new PluginResult(PluginResult.Status.NO_RESULT);
            r.setKeepCallback(true);
            return r;
        } catch (JSONException e) {
            e.printStackTrace();
            return new PluginResult(PluginResult.Status.JSON_EXCEPTION);
        }
    }

    /**
     * 扫描二维码的方法 备注：在扫描二维码的类型最好不好设置，在前期的zxing可能需要，在后期的版本中不需要， zxing会自动检索二维码的类型，并识别相关二维码。
     * <p/>
     * Initiates a barcode scan. If the ZXing scanner isn't installed, the user will be prompted to install it.
     *
     * @param types          The barcode types to accept
     * @param installTitle   The title for the dialog box that prompts the user to install the scanner
     * @param installMessage The message prompting the user to install the barcode scanner
     * @param yesString      The string "Yes" or localised equivalent
     * @param noString       The string "No" or localised version
     */
    public void scan(String barcodeFormats, String installTitle, String installMessage, String yesString, String noString) {
        Intent intentScan = new Intent("com.google.zxing.client.android.SCAN");
        // intentScan.addCategory(Intent.CATEGORY_DEFAULT);

        // 设置扫描特定类型的二维码
        // if (barcodeFormats != null) {
        // Tell the scanner what types we're after
        // intentScan.putExtra("SCAN_FORMATS", barcodeFormats);
        // }
        try {
            this.ctx.startActivityForResult((Plugin) this, intentScan, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            showDownloadDialog(installTitle, installMessage, yesString, noString);
        }
    }

    /**
     * 用于获取二维码扫描之后获取相关的二维码相关的信息 Called when the barcode scanner exits
     *
     * @param requestCode The request code originally supplied to startActivityForResult(), allowing you to identify who this result came from.
     * @param resultCode  The integer result code returned by the child activity through its setResult().
     * @param intent      An Intent, which can return result data to the caller (various data can be attached to Intent "extras").
     */
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                this.success(new PluginResult(PluginResult.Status.OK, " 条形码为:" + contents + " 条码类型为: " + format), this.callback);
            } else {
                this.error(new PluginResult(PluginResult.Status.ERROR), this.callback);
            }
        }
    }

    /**
     * 创建相关的对话框，在通过没有安装相关的zxing开源组件时候，调用远程的intent或者下载相关执行类实现相关的 功能
     *
     * @param title
     * @param message
     * @param yesString
     * @param noString
     */
    private void showDownloadDialog(final String title, final String message, final String yesString, final String noString) {
        final PhonegapActivity context = this.ctx;
        Runnable runnable = new Runnable() {
            public void run() {

                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle(title);
                dialog.setMessage(message);
                dialog.setPositiveButton(yesString, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dlg, int i) {
                        dlg.dismiss();
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pname:com.google.zxing.client.android"));
                        try {
                            context.startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            // We don't have the market app installed, so download it directly.
                            Intent in = new Intent(Intent.ACTION_VIEW);
                            in.setData(Uri.parse("http://zxing.googlecode.com/files/BarcodeScanner4.1.apk"));
                            context.startActivity(in);

                        }

                    }
                });
                dialog.setNegativeButton(noString, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dlg, int i) {
                        dlg.dismiss();
                    }
                });
                dialog.create();
                dialog.show();
            }
        };
        context.runOnUiThread(runnable);
    }

    /**
     * Initiates a barcode encode. If the ZXing scanner isn't installed, the user will be prompted to install it.
     *
     * @param type           The barcode type to encode
     * @param data           The data to encode in the bar code
     * @param installTitle   The title for the dialog box that prompts the user to install the scanner
     * @param installMessage The message prompting the user to install the barcode scanner
     * @param yesString      The string "Yes" or localised equivalent
     * @param noString       The string "No" or localised version
     */
    public void encode(String type, String data, String installTitle, String installMessage, String yesString, String noString) {
        Intent intentEncode = new Intent("com.google.zxing.client.android.ENCODE");
        intentEncode.putExtra("ENCODE_TYPE", type);
        intentEncode.putExtra("ENCODE_DATA", data);

        try {
            this.ctx.startActivity(intentEncode);
        } catch (ActivityNotFoundException e) {
            showDownloadDialog(installTitle, installMessage, yesString, noString);
        }
    }
}
