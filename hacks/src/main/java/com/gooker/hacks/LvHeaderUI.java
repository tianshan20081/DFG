package com.gooker.hacks;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ListView;

import com.gooker.hacks.adapter.GalleryAdapter;

import java.util.ArrayList;
import java.util.List;

public class LvHeaderUI extends AppCompatActivity {

    private ListView lvHeader;
    private Gallery mGallery;
    private List<Integer> mDatas ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_lv_header);

        mDatas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mDatas.add(new Integer(i));
        }
        
        lvHeader = (ListView)findViewById(R.id.lvHeader);

        LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View header = mLayoutInflater.inflate(R.layout.lv_header, lvHeader,false);
        mGallery = (Gallery) header.findViewById(R.id.gallery);
        ListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ListView.LayoutParams.FILL_PARENT,
                ListView.LayoutParams.WRAP_CONTENT);
        header.setLayoutParams(layoutParams);


        mGallery.setAdapter(new GalleryAdapter(this,mDatas));

        lvHeader.addHeaderView(header,null,false);

        lvHeader.setOnItemClickListener(new ListView.OnItemClickListener() {
            /**
             * Callback method to be invoked when an item in this AdapterView has
             * been clicked.
             * <p/>
             * Implementers can call getItemAtPosition(position) if they need
             * to access the data associated with the selected item.
             *
             * @param parent   The AdapterView where the click happened.
             * @param view     The view within the AdapterView that was clicked (this
             *                 will be a view provided by the adapter)
             * @param position The position of the view in the adapter.
             * @param id       The row id of the item that was clicked.
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mGallery.setSelection(position-1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lv_header_ui, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
