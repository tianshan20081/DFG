/**
 *
 */
package com.gooker.dfg.ui.security;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.gooker.dfg.R;
import com.gooker.dfg.ui.BaseUI;


/**
 * May 21, 2014 4:47:44 PM
 */
public class SecurityUI extends BaseUI {

    private Button btnAntidex2Jar;
    private Button btnProguard;
    private Button btnAntiDebug;
    private Button btnCheckEmu;
    private Button btnCheckSignature;
    private Button btnCheckCrc;

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#loadViewLayout()
     */
    @Override
    protected void loadViewLayout() {
        // TODO Auto-generated method stub
        setContentView(R.layout.ui_security_home);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#findViewById()
     */
    @Override
    protected void findViewById() {
        // TODO Auto-generated method stub
        btnAntidex2Jar = (Button) findView(R.id.btnAntidex2Jar);
        btnProguard = (Button) findView(R.id.btnProguard);
        btnAntiDebug = (Button) findView(R.id.btnAntiDebug);
        btnCheckEmu = (Button) findView(R.id.btnCheckEmu);
        btnCheckSignature = (Button) findView(R.id.btnCheckSignature);
        btnCheckCrc = (Button) findView(R.id.btnCheckCrc);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#setListener()
     */
    @Override
    protected void setListener() {
        // TODO Auto-generated method stub
        btnAntidex2Jar.setOnClickListener(this);
        btnProguard.setOnClickListener(this);
        btnAntiDebug.setOnClickListener(this);
        btnCheckEmu.setOnClickListener(this);
        btnCheckSignature.setOnClickListener(this);
        btnCheckCrc.setOnClickListener(this);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#processLogic()
     */
    @Override
    protected void processLogic() {
        // TODO Auto-generated method stub

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
            case R.id.btnAntidex2Jar:
                startActivity(new Intent(this, Antidex2JarUI.class));
                break;
            case R.id.btnProguard:
                startActivity(new Intent(this, ProguardUI.class));
                break;
            case R.id.btnAntiDebug://反调试模式
                startActivity(new Intent(this, AntiDebugUI.class));
                break;
            case R.id.btnCheckEmu://反调试模式
                startActivity(new Intent(this, CheckEmuUI.class));
                break;
            case R.id.btnCheckSignature://反调试模式
                startActivity(new Intent(this, CheckSignatureUI.class));
                break;
            case R.id.btnCheckCrc://反调试模式
                startActivity(new Intent(this, CheckCrcUI.class));
                break;

            default:
                break;
        }
    }

}
