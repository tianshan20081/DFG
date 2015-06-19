/**
 *
 */
package com.gooker.dfg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.gooker.dfg.R;
import com.gooker.dfg.domain.Contact;

import java.util.List;

/**
 * @author [ShaoCheng Zhang] Sep 3, 2013:11:42:35 AM
 * @Email [zhangshch2008@gmail.com]
 */
public class ContactAdapter extends ArrayAdapter<Contact> {
    /*
     * 需要渲染的 item 布局文件
     */
    private int resource;
    /*
     * 字母表分组工具
     */
    private SectionIndexer mIndexer;

    /**
     * @param context
     * @param textViewResourceId
     * @param objects
     */
    public ContactAdapter(Context context, int textViewResourceId, List<Contact> objects) {
        super(context, textViewResourceId, objects);
        // TODO Auto-generated constructor stub
        resource = textViewResourceId;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.widget.ArrayAdapter#getView(int, android.view.View,
     * android.view.ViewGroup)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Contact contact = getItem(position);
        LinearLayout layout = null;
        if (convertView != null) {
            layout = (LinearLayout) convertView;
        } else {
            layout = (LinearLayout) LayoutInflater.from(getContext()).inflate(resource, null);
        }
        TextView name = (TextView) layout.findViewById(R.id.name);
        LinearLayout sortKeyLayout = (LinearLayout) layout.findViewById(R.id.sort_key_layout);
        TextView sortKey = (TextView) layout.findViewById(R.id.sort_key);
        name.setText(contact.getName());
        int section = mIndexer.getSectionForPosition(position);
        if (position == mIndexer.getPositionForSection(section)) {
            sortKey.setText(contact.getSortKey());
            sortKeyLayout.setVisibility(View.VISIBLE);
        } else {
            sortKeyLayout.setVisibility(View.GONE);
        }
        return layout;
    }

    /*
     * 给当前适配器传入一个分组工具
     */
    public void setIndex(SectionIndexer indexer) {
        mIndexer = indexer;
    }

}
