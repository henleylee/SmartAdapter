package com.henley.smartadapter.demo;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.henley.smartadapter.demo.delegate.MessageCommonItemDelagate;
import com.henley.smartadapter.demo.delegate.MessageReceiveItemDelagate;
import com.henley.smartadapter.demo.delegate.MessageSendItemDelagate;
import com.henley.smartadapter.recycleview.adapter.MultiItemTypeAdapter;
import com.henley.smartadapter.recycleview.listener.OnItemClickListener;
import com.henley.smartadapter.recycleview.listener.OnItemLongClickListener;

/**
 * @author Henley
 * @date 2017/8/2 14:24
 */
public class RecycleViewActivity extends AppCompatActivity implements OnItemClickListener, OnItemLongClickListener {

    private RecyclerView recyclerView;
    private MultiItemTypeAdapter<ChatMessage> singleAdapter;
    private MultiItemTypeAdapter<ChatMessage> multiAdapter;
    private Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        singleAdapter = new RecycleViewSingleAdapter(ChatMessage.MOCK_DATAS);
//        multiAdapter = new RecycleViewMultiAdapter(ChatMessage.MOCK_DATAS);
        singleAdapter = new MultiItemTypeAdapter<>(ChatMessage.MOCK_DATAS);
        singleAdapter.addItemViewDelegate(new MessageCommonItemDelagate());
        multiAdapter = new MultiItemTypeAdapter<>(ChatMessage.MOCK_DATAS);
        multiAdapter.addItemViewDelegate(new MessageSendItemDelagate());
        multiAdapter.addItemViewDelegate(new MessageReceiveItemDelagate());
        recyclerView.setAdapter(multiAdapter);
        singleAdapter.setOnItemClickListener(this);
        singleAdapter.setOnItemLongClickListener(this);
        multiAdapter.setOnItemClickListener(this);
        multiAdapter.setOnItemLongClickListener(this);
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
            recyclerView.setAdapter(singleAdapter);
            return true;
        } else if (itemId == R.id.action_multi) {
            recyclerView.setAdapter(multiAdapter);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        showToast("onItemClick--->" + position);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
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
