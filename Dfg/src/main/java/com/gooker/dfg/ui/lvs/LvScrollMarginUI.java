/**
 *
 */
package com.gooker.dfg.ui.lvs;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gooker.dfg.R;
import com.gooker.dfg.ui.BaseUI;


/**
 * Jun 14, 2014 12:26:17 PM
 */
public class LvScrollMarginUI extends BaseUI implements OnScrollListener {

    private ListView lv;

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
        setContentView(R.layout.ui_lvs_scroll_margin);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#findViewById()
     */
    @Override
    protected void findViewById() {
        // TODO Auto-generated method stub
        lv = (ListView) this.findViewById(R.id.lvScrollMargin);

        lv.setAdapter(new ScrollMarginAdapter());
    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#setListener()
     */
    @Override
    protected void setListener() {
        // TODO Auto-generated method stub
        lv.setOnScrollListener(this);
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
     * @see android.widget.AbsListView.OnScrollListener#onScrollStateChanged(android.widget.AbsListView, int)
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // TODO Auto-generated method stub
//		log("lv.getFirstVisiblePosition()" + lv.getFirstVisiblePosition() + 1);
//		int position = lv.getFirstVisiblePosition() ;
//		if (position == 0) {
//			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//			params.setMargins(15, 20, 15, 20);
//			lv.setLayoutParams(params);
//		} else {
//			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//			params.setMargins(15, 0, 15, 20);
//			lv.setLayoutParams(params);
//
//		}
    }

    /*
     * (non-Javadoc)
     *
     * @see android.widget.AbsListView.OnScrollListener#onScroll(android.widget.AbsListView, int, int, int)
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // TODO Auto-generated method stub
        log("lv.getFirstVisiblePosition()" + lv.getFirstVisiblePosition() + 1);
        int position = lv.getFirstVisiblePosition();
        if (position == 0) {
//			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//			params.setMargins(15, 20, 15, 20);
//			lv.setLayoutParams(params);
            lv.setPadding(20, 20, 20, 0);
        } else {
            // LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            // params.setMargins(15, 0, 15, 20);
            // lv.setLayoutParams(params);

            // LayoutTransition transition = new LayoutTransition();
            // lv.setLayoutTransition(transition);
            lv.setPadding(20, 0, 20, 0);
        }
    }

    private class ScrollMarginAdapter extends BaseAdapter {

        /*
         * (non-Javadoc)
         *
         * @see android.widget.Adapter#getCount()
         */
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 20;
        }

        /*
         * (non-Javadoc)
         *
         * @see android.widget.Adapter#getItem(int)
         */
        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        /*
         * (non-Javadoc)
         *
         * @see android.widget.Adapter#getItemId(int)
         */
        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        /*
         * (non-Javadoc)
         *
         * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            ViewHolder holder;
            if (null != convertView) {
                holder = (ViewHolder) convertView.getTag();
            } else {
                holder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.ui_lvs_scroll_margin_item, null);
                holder.im = (ImageView) convertView.findViewById(R.id.im);
                holder.tv = (TextView) convertView.findViewById(R.id.tv);
                holder.tvNo = (TextView) convertView.findViewById(R.id.tvNo);

                convertView.setTag(holder);
            }
            holder.tvNo.setText("Item " + (position + 1));
            return convertView;
        }

    }

    static class ViewHolder {
        ImageView im;
        TextView tv;
        TextView tvNo;
    }
}
