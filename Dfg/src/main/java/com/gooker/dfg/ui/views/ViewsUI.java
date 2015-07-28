/**
 *
 */
package com.gooker.dfg.ui.views;

import android.content.Intent;
import android.view.View;

import com.gooker.dfg.R;
import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.utils.common.UIUtils;


/**
 * Jul 16, 2014 11:18:49 AM
 */
public class ViewsUI extends BaseUI {

    /*
     * (non-Javadoc)
     *
     * @see
     * android.view.View.OnClickListener
     * #onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method
        // stub
        switch (v.getId()) {
            case R.id.btnTableLayout:
                startActivity(new Intent(ViewsUI.this, TableLayoutUI.class));
                break;
            case R.id.btnProgressView:
                UIUtils.startActivity(ProgressUI.class);
                break;
            case R.id.btnNumProgressBar:
                UIUtils.startActivity(NumProgressBarUI.class);
                break;
            case R.id.btnScView:
                UIUtils.startActivity(ScrollViewHomeUI.class);
                break;
            default:
                break;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#
     * loadViewLayout()
     */
    @Override
    protected void loadViewLayout() {
        // TODO Auto-generated method
        // stub
        setContentView(R.layout.ui_views_home);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.aoeng.degu.ui.BaseUI#findViewById
     * ()
     */
    @Override
    protected void findViewById() {
        // TODO Auto-generated method
        // stub

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.aoeng.degu.ui.BaseUI#setListener
     * ()
     */
    @Override
    protected void setListener() {
        // TODO Auto-generated method
        // stub
        findView(R.id.btnTableLayout).setOnClickListener(this);
        findView(R.id.btnProgressView).setOnClickListener(this);
        findView(R.id.btnNumProgressBar).setOnClickListener(this);
        findView(R.id.btnScView).setOnClickListener(this);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.aoeng.degu.ui.BaseUI#processLogic
     * ()
     */
    @Override
    protected void processLogic() {
        // TODO Auto-generated method
        // stub

    }

}
