/**
 *
 */
package com.gooker.dfg.ui.cv;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.AlphabetIndexer;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.gooker.dfg.R;
import com.gooker.dfg.adapter.ContactAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author [ShaoCheng Zhang] Sep 3, 2013:11:38:55 AM
 * @Email [zhangshch2008@gmail.com]
 */
public class ContactsUI extends Activity {
    /*
     * 分组的布局
     */
    private LinearLayout titleLayout;
    /*
     * 分组显示的字母
     */
    private TextView title;
    /*
     * 联系人 ListView
     */
    private ListView contactsListView;
    /*
     * 联系人适配器
     */
    private ContactAdapter adapter;
    /*
     * 用于进行字母分组
     */
    private AlphabetIndexer indexer;
    /*
     * 存储所有手机中的联系人
     */
    private List<com.gooker.dfg.domain.Contact> contacts = new ArrayList<com.gooker.dfg.domain.Contact>();
    /*
     * 定义字母表的排序规则
     */
    private String alphabet = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /*
     * 上次第一个可见元素，用于 滚动式纪录标识
     */
    private int lastFirstVisiableItem = -1;

    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ui_cv_contacts);
        adapter = new ContactAdapter(this, R.layout.ui_cv_contacts_item, contacts);
        titleLayout = (LinearLayout) findViewById(R.id.title_layout);
        title = (TextView) findViewById(R.id.title);
        contactsListView = (ListView) findViewById(R.id.contacts_list_view);
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, new String[]{"display_name", "sort_key"}, null, null, "sort_key");
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                String sortKey = getSortKey(cursor.getString(1));
                com.gooker.dfg.domain.Contact contact = new com.gooker.dfg.domain.Contact();
                contact.setName(name);
                contact.setSortKey(sortKey);
                contacts.add(contact);
            } while (cursor.moveToNext());
        }
        startManagingCursor(cursor);
        indexer = new AlphabetIndexer(cursor, 1, alphabet);
        adapter.setIndex(indexer);
        if (contacts.size() > 0) {
            setupContactsListView();
        }
    }

    /**
     * 为联系人 ListView 设置监听事件，根据当前的滑动状态来改变分组的显示位置,从而实现挤压动画的效果
     */
    private void setupContactsListView() {
        // TODO Auto-generated method stub
        contactsListView.setAdapter(adapter);
        contactsListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                // TODO Auto-generated method stub
                int section = indexer.getSectionForPosition(firstVisibleItem);
                int nextSecPosition = indexer.getPositionForSection(section + 1);
                if (firstVisibleItem != lastFirstVisiableItem) {
                    MarginLayoutParams params = (MarginLayoutParams) titleLayout.getLayoutParams();
                    params.topMargin = 0;
                    titleLayout.setLayoutParams(params);
                    title.setText(String.valueOf(alphabet.charAt(section)));
                }
                if (nextSecPosition == firstVisibleItem + 1) {
                    View childView = view.getChildAt(0);
                    if (childView != null) {
                        int titleHight = titleLayout.getHeight();
                        int bottom = childView.getBottom();
                        MarginLayoutParams params = (MarginLayoutParams) titleLayout.getLayoutParams();
                        if (bottom < titleHight) {
                            float pushedDistance = bottom - titleHight;
                            params.topMargin = (int) pushedDistance;
                            titleLayout.setLayoutParams(params);
                        } else {
                            if (params.topMargin != 0) {
                                params.topMargin = 0;
                                titleLayout.setLayoutParams(params);
                            }
                        }
                    }
                }
                lastFirstVisiableItem = firstVisibleItem;
            }
        });
    }

    /**
     * 获取 sortKey 的首个字母，如果是英文字母就直接返回，否则返回 #
     *
     * @param sortKeyString 数据库中读取出来的 sort_Key
     * @return 英文字母 或者 #
     */
    private String getSortKey(String sortKeyString) {
        // TODO Auto-generated method stub
        String key = sortKeyString.substring(0, 1).toUpperCase();
        if (key.matches("[A-Z]")) {
            return key;
        }
        return "#";
    }

}
