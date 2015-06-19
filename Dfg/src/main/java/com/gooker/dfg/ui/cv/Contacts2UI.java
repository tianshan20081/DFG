/**
 *
 */
package com.gooker.dfg.ui.cv;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.AlphabetIndexer;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gooker.dfg.R;
import com.gooker.dfg.adapter.ContactAdapter;
import com.gooker.dfg.domain.Contact;

import java.util.ArrayList;
import java.util.List;


/**
 * @author [ShaoCheng Zhang] Sep 3, 2013:1:25:10 PM
 * @Email [zhangshch2008@gmail.com]
 */
public class Contacts2UI extends Activity {
    /*
     * 分组的布局
     */
    private LinearLayout titleLayout;

    /*
     * 弹出分组的布局
     */
    private RelativeLayout sectionToastLayout;
    /*
     * 右侧可滑动字母表
     */
    private Button alphabetButton;
    /*
     * 分组显示的字母
     */
    private TextView title;
    /*
     * 弹出分组上的文字
     */
    private TextView sectionToastText;
    /*
     * 联系人 ListView
     */
    private ListView contactsListView;
    /*
     * 联系人列表适配器
     */
    private ContactAdapter adapter;
    /*
     * 用于进行字母表分组
     */
    private AlphabetIndexer indexer;
    /*
     * 存储所有联系人
     */
    private List<Contact> contacts = new ArrayList<Contact>();
    /*
     * 定义字母表的顺序
     */
    private String alphabet = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /*
     * 上次第一个可见元素，用于滚动时记录标识
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

        setContentView(R.layout.ui_cv_contacts2);
        adapter = new ContactAdapter(Contacts2UI.this, R.layout.ui_cv_contacts_item, contacts);
        titleLayout = (LinearLayout) findViewById(R.id.title_layout);
        sectionToastLayout = (RelativeLayout) findViewById(R.id.section_toast_layout);
        title = (TextView) findViewById(R.id.title);
        sectionToastText = (TextView) findViewById(R.id.section_toast_text);
        alphabetButton = (Button) findViewById(R.id.alphabetButton);
        contactsListView = (ListView) findViewById(R.id.contacts_list_view);
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, new String[]{"display_name", "sort_key"}, null, null, "sort_key");
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                String sortKey = getSortKey(cursor.getString(1));
                Contact contact = new Contact();
                contact.setName(name);
                contact.setSortKey(sortKey);
                contacts.add(contact);
            } while (cursor.moveToNext());
        }
        startManagingCursor(cursor);
        indexer = new AlphabetIndexer(cursor, 1, alphabet);
        adapter.setIndex(indexer);
        if (contacts.size() > 0) {
            setupContactListView();
            setAlpabetListener();
        }
    }

    /**
     * 设置字母表上的触摸事件，根据当前触摸的位置结合字母的高度，计算出当前触摸在那个字母上。
     * 当手指按在字母表上时，展示弹出式分组，手指离开时，将弹出式分组隐藏
     */
    private void setAlpabetListener() {
        // TODO Auto-generated method stub
        alphabetButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                float alphabetHeight = alphabetButton.getHeight();
                float y = event.getY();
                int sectionPosition = (int) ((y / alphabetHeight) / (1f / 27f));
                if (sectionPosition < 0) {
                    sectionPosition = 0;
                } else if (sectionPosition > 26) {
                    sectionPosition = 26;
                }
                String sectionLetter = String.valueOf(alphabet.charAt(sectionPosition));
                int position = indexer.getPositionForSection(sectionPosition);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        alphabetButton.setBackgroundResource(R.drawable.a_z_click);
                        sectionToastLayout.setVisibility(View.VISIBLE);
                        sectionToastText.setText(sectionLetter);
                        contactsListView.setSelection(position);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        sectionToastText.setText(sectionLetter);
                        contactsListView.setSelection(position);
                        break;
                    default:
                        alphabetButton.setBackgroundResource(R.drawable.a_z);
                        sectionToastLayout.setVisibility(View.GONE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     *
     */
    private void setupContactListView() {
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
                    if (childView == null) {
                        int titleHight = titleLayout.getHeight();
                        int bottom = titleLayout.getBottom();
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
     * 获取 sortkey 的首个字符,如果是英文字母就直接返回,否则返回 #
     *
     * @param sortKeyString 数据库中读取出来的 sortkey
     * @return 英文字母或者 #
     */
    private String getSortKey(String sortKeyString) {
        // TODO Auto-generated method stub
        alphabetButton.getHeight();
        String key = sortKeyString.substring(0, 1).toUpperCase();
        if (key.matches("[A-Z]")) {
            return key;
        }
        return "#";
    }
}
