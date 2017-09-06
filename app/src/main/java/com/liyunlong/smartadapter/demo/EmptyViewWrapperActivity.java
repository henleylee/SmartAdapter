package com.liyunlong.smartadapter.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.liyunlong.smartadapter.demo.delegate.MessageCommonItemDelagate;
import com.liyunlong.smartadapter.recycleview.adapter.MultiItemTypeAdapter;
import com.liyunlong.smartadapter.recycleview.listener.OnItemClickListener;
import com.liyunlong.smartadapter.recycleview.listener.OnItemLongClickListener;
import com.liyunlong.smartadapter.recycleview.wrapper.EmptyViewWrapper;

/**
 * @author liyunlong
 * @date 2017/8/2 14:24
 */
public class EmptyViewWrapperActivity extends AppCompatActivity implements OnItemClickListener, OnItemLongClickListener {

    private RecyclerView mRecyclerView;
    private MultiItemTypeAdapter<ChatMessage> mAdapter;
    private Toast toast;
    private EmptyViewWrapper mEmptyViewWrapper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new MultiItemTypeAdapter<>(ChatMessage.MOCK_DATAS);
        mAdapter.addItemViewDelegate(new MessageCommonItemDelagate());
        mEmptyViewWrapper = new EmptyViewWrapper(mAdapter);
        mEmptyViewWrapper.setEmptyView(R.layout.layout_emptyview);
        mRecyclerView.setAdapter(mEmptyViewWrapper);

        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_emptyview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_show_data) {
            mAdapter.refresh(ChatMessage.MOCK_DATAS);
            mEmptyViewWrapper.notifyDataSetChanged();
            return true;
        } else if (itemId == R.id.action_show_emptyview) {
            mAdapter.refresh(null);
            mEmptyViewWrapper.notifyDataSetChanged();
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
