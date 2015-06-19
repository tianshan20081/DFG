/**
 *
 */
package com.gooker.dfg.adapter.swipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.gooker.dfg.R;
import com.gooker.dfg.views.swipe.SwipeListView;

import java.util.List;

/**
 * Jun 30, 2014 4:23:49 PM
 */
public class SwipeAdapter extends ArrayAdapter<String> {
    private LayoutInflater mInflater;
    private List<String> objects;
    private SwipeListView mSwipeListView;

    public SwipeAdapter(Context context, int textViewResourceId, List<String> objects, SwipeListView mSwipeListView) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
        this.mSwipeListView = mSwipeListView;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.package_row, parent, false);
            holder = new ViewHolder();
            holder.mFrontText = (TextView) convertView.findViewById(R.id.example_row_tv_title);
            holder.mBackEdit = (Button) convertView.findViewById(R.id.example_row_b_action_3);
            holder.mBackDelete = (Button) convertView.findViewById(R.id.example_row_b_action_2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 点击删除按钮
        holder.mBackDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除动画
                mSwipeListView.closeAnimate(position);
                //刪除 item
                mSwipeListView.dismiss(position);
            }
        });
        String item = getItem(position);
        holder.mFrontText.setText(item);
        return convertView;
    }

    class ViewHolder {
        TextView mFrontText;
        Button mBackEdit, mBackDelete;
    }
}
