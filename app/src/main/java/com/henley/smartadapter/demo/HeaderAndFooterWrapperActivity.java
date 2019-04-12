package com.henley.smartadapter.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.henley.smartadapter.demo.delegate.MessageCommonItemDelagate;
import com.henley.smartadapter.recycleview.adapter.MultiItemTypeAdapter;
import com.henley.smartadapter.recycleview.listener.OnItemClickListener;
import com.henley.smartadapter.recycleview.listener.OnItemLongClickListener;
import com.henley.smartadapter.recycleview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Henley
 * @date 2017/8/2 14:24
 */
public class HeaderAndFooterWrapperActivity extends AppCompatActivity implements OnItemClickListener, OnItemLongClickListener, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private MultiItemTypeAdapter<ChatMessage> mAdapter;
    private Toast toast;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private List<View> mHeaderViews = new ArrayList<>();
    private List<View> mFooterViews = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);

        findViewById(R.id.action_container).setVisibility(View.VISIBLE);
        findViewById(R.id.add_header).setOnClickListener(this);
        findViewById(R.id.remove_header).setOnClickListener(this);
        findViewById(R.id.add_footer).setOnClickListener(this);
        findViewById(R.id.remove_footer).setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new MultiItemTypeAdapter<>(ChatMessage.MOCK_DATAS);
        mAdapter.addItemViewDelegate(new MessageCommonItemDelagate());
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mAdapter);

        mRecyclerView.setAdapter(mHeaderAndFooterWrapper);

        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layoutmanager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_linear) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(mHeaderAndFooterWrapper);
            return true;
        } else if (itemId == R.id.action_grid) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            mRecyclerView.setAdapter(mHeaderAndFooterWrapper);
            return true;
        } else if (itemId == R.id.action_staggered) {
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            mRecyclerView.setAdapter(mHeaderAndFooterWrapper);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        View headerView;
        View footerView;
        int removeToIndex;
        switch (v.getId()) {
            case R.id.add_header:
                headerView = getTextView("Header ---> " + new Random().nextInt(100));
                mHeaderAndFooterWrapper.addHeaderView(headerView);
                mHeaderViews.add(headerView);
                if (mHeaderViews.size() == 1) {
                    mRecyclerView.scrollToPosition(0);
                }
                break;
            case R.id.remove_header:
                if (mHeaderViews.size() > 0) {
                    removeToIndex = new Random().nextInt(mHeaderViews.size());
                    headerView = mHeaderViews.get(removeToIndex);
                    mHeaderAndFooterWrapper.removeHeaderView(headerView);
                    mHeaderViews.remove(removeToIndex);
                }
                break;
            case R.id.add_footer:
                footerView = getTextView("Footer ---> " + new Random().nextInt(100));
                mHeaderAndFooterWrapper.addFooterView(footerView);
                mFooterViews.add(footerView);
                break;
            case R.id.remove_footer:
                if (mFooterViews.size() > 0) {
                    removeToIndex = new Random().nextInt(mFooterViews.size());
                    headerView = mFooterViews.get(removeToIndex);
                    mHeaderAndFooterWrapper.removeFooterView(headerView);
                    mFooterViews.remove(removeToIndex);
                }
                break;
        }
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

    private View getTextView(CharSequence text){
        View view = getLayoutInflater().inflate(R.layout.layout_textview, null);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(text);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return view;
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
