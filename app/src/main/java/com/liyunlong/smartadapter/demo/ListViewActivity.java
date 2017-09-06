package com.liyunlong.smartadapter.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.liyunlong.smartadapter.abslistview.adapter.MultiItemTypeAdapter;
import com.liyunlong.smartadapter.demo.delegate.MessageCommonItemDelagate;
import com.liyunlong.smartadapter.demo.delegate.MessageReceiveItemDelagate;
import com.liyunlong.smartadapter.demo.delegate.MessageSendItemDelagate;

/**
 * @author liyunlong
 * @date 2017/8/2 14:24
 */
public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ListView listView;
    private MultiItemTypeAdapter<ChatMessage> singleAdapter;
    private MultiItemTypeAdapter<ChatMessage> multiAdapter;
    private Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

//        singleAdapter = new ListViewSingleAdapter(ChatMessage.MOCK_DATAS);
        singleAdapter = new MultiItemTypeAdapter<>(ChatMessage.MOCK_DATAS);
        singleAdapter.addItemViewDelegate(new MessageCommonItemDelagate());
        multiAdapter = new MultiItemTypeAdapter<>(ChatMessage.MOCK_DATAS);
        multiAdapter.addItemViewDelegate(new MessageSendItemDelagate());
        multiAdapter.addItemViewDelegate(new MessageReceiveItemDelagate());
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(multiAdapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_single) {
            listView.setAdapter(singleAdapter);
            return true;
        } else if (itemId == R.id.action_multi) {
            listView.setAdapter(multiAdapter);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showToast("onItemClick--->" + position);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        showToast("onItemLongClick--->" + position);
        return true;
    }

    private void showToast(CharSequence message) {
        if (toast == null) {
            toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }
}
