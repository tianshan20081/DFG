package com.gooker.recycleview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gooker.recycleview.adapter.RvAdapter;

import org.apache.http.conn.scheme.Scheme;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private RecyclerView rv;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        rv = (RecyclerView) findViewById(R.id.rv);
        mDatas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mDatas.add("" + i);
        }

//        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setLayoutManager(new GridLayoutManager(this, 3));

        rv.setAdapter(new RvAdapter(this, mDatas));

        rv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_grid:
                rv.setLayoutManager(new GridLayoutManager(this,4));
                break;
            case R.id.action_lineary:
                rv.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.action_staggered:
                rv.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.HORIZONTAL));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
