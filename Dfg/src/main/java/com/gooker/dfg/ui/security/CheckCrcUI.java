/**
 *
 */
package com.gooker.dfg.ui.security;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.gooker.dfg.R;
import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.utils.common.Logger;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


/**
 * May 22, 2014  2:41:56 PM
 */
public class CheckCrcUI extends BaseUI {

    private TextView tvInfo;

    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.aoeng.degu.ui.BaseUI#loadViewLayout()
     */
    @Override
    protected void loadViewLayout() {
        // TODO Auto-generated method stub
        setContentView(R.layout.ui_security_crc);
    }

    /* (non-Javadoc)
     * @see com.aoeng.degu.ui.BaseUI#findViewById()
     */
    @Override
    protected void findViewById() {
        // TODO Auto-generated method stub
        tvInfo = (TextView) findView(R.id.tvInfo);
    }

    /* (non-Javadoc)
     * @see com.aoeng.degu.ui.BaseUI#setListener()
     */
    @Override
    protected void setListener() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.aoeng.degu.ui.BaseUI#processLogic()
     */
    @Override
    protected void processLogic() {
        // TODO Auto-generated method stub
        setTitle("校验保护演示程序");
        if (checkCRC()) {    //对比classes.dex的校验和
            tvInfo.setTextColor(Color.GREEN);
            tvInfo.setText("程序正常！");
        } else {
            tvInfo.setTextColor(Color.RED);
            tvInfo.setText("程序被修改过！");
        }
    }

    private boolean checkCRC() {
        boolean beModified = false;
        long crc = Long.parseLong(getString(R.string.crc));
        ZipFile zf;
        try {
            zf = new ZipFile(getApplicationContext().getPackageCodePath());
            ZipEntry ze = zf.getEntry("classes.dex");
            Logger.d("com.droider.checkcrc", String.valueOf(ze.getCrc()));
            if (ze.getCrc() == crc) {
                beModified = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            beModified = false;
        }
        return beModified;
    }

}
