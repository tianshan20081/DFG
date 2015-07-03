package com.gooker.hacks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gooker.hacks.adapter.DelegateAdapter;

import java.util.ArrayList;
import java.util.List;

public class DelegateUI extends AppCompatActivity implements DelegateAdapter.NumbersAdapterDelegate, AdapterView.OnItemClickListener {

    private ListView lvDelegate;
    private DelegateAdapter mAdapter;
    private List<Integer> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_delegate);
        lvDelegate = (ListView) findViewById(R.id.lvDelegate);
        datas = new ArrayList<>();
        for (int i = 100; i >= 0; i--) {
            datas.add(new Integer(i));
        }
        mAdapter = new DelegateAdapter(this, datas);
        lvDelegate.setAdapter(mAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.setDelegate(this);
        lvDelegate.setOnItemClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAdapter.setDelegate(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delegate_ui, menu);
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

    @Override
    public void remove(Integer value) {
        Toast.makeText(this, "delete item " + value.toString(), Toast.LENGTH_SHORT).show();
        datas.remove(value);
        mAdapter.notifyDataSetChanged();
    }

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
        Toast.makeText(this, "Item onClick value is " + datas.get(position).toString(), Toast.LENGTH_SHORT).show();
    }
}
