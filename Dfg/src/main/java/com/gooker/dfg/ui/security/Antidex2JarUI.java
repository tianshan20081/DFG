/**
 *
 */
package com.gooker.dfg.ui.security;

import android.view.View;
import android.widget.TextView;

import com.gooker.dfg.R;
import com.gooker.dfg.ui.BaseUI;


/**
 * May 21, 2014 5:32:52 PM
 */
public class Antidex2JarUI extends BaseUI {
    private static final int BITS_PER_UNIT = 8;
    private TextView tvAntidex2Jareg;

    /*
     * (non-Javadoc)
     *
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#loadViewLayout()
     */
    @Override
    protected void loadViewLayout() {
        // TODO Auto-generated method stub
        setContentView(R.layout.ui_security_antidex2jar);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#findViewById()
     */
    @Override
    protected void findViewById() {
        // TODO Auto-generated method stub
        tvAntidex2Jareg = (TextView) findView(R.id.tvAntidex2Jareg);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#setListener()
     */
    @Override
    protected void setListener() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#processLogic()
     */
    @Override
    protected void processLogic() {
        // TODO Auto-generated method stub
        int i = position(3);
        toast(String.valueOf(i));

        tvAntidex2Jareg
                .setText("private int position(int idx) { // bits big-endian in each unit \n"
                        + " return 1 << (BITS_PER_UNIT - 1 - (idx % BITS_PER_UNIT));}");
    }

    /**
     * @param
     * @return
     */
    private int position(int idx) { // bits big-endian in each unit
        return 1 << (BITS_PER_UNIT - 1 - (idx % BITS_PER_UNIT));
    }

}
