package com.gooker.hacks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.gooker.hacks.R;

import java.util.List;

/**
 * Created by sczhang on 15/6/22. 下午3:18
 * Package Name com.gooker.hacks.adapter
 * Project Name DFG
 */
public class DelegateAdapter extends BaseAdapter {
    private List<Integer> mDatas;
    private Context mContext;
    private NumbersAdapterDelegate mDelegate;

    public DelegateAdapter(Context context, List<Integer> datas) {
        this.mContext = context ;
        this.mDatas = datas;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return mDatas.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Integer getItem(int position) {
        return mDatas.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (null != convertView) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.ui_delegate_item, null);
            holder.tvInfo = (TextView) convertView.findViewById(R.id.tvInfo);
            holder.btn = (Button) convertView.findViewById(R.id.btnDel);
            convertView.setTag(holder);
        }
        final Integer value = getItem(position);
        holder.tvInfo.setText(value.toString());
        holder.btn.setText("删除"+value.toString());
        holder.btn.setOnClickListener(new View.OnClickListener() {

            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                mDelegate.remove(value);
            }
        });
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    static class ViewHolder {
        TextView tvInfo;
        Button btn;
    }

    public void setDelegate(NumbersAdapterDelegate delegate) {
        this.mDelegate = delegate;
    }

    public interface NumbersAdapterDelegate {
        void remove(Integer value);
    }
}
