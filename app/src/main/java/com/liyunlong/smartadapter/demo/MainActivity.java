package com.liyunlong.smartadapter.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.listview_single_multi).setOnClickListener(this);
        findViewById(R.id.recycleview_single_multi).setOnClickListener(this);
        findViewById(R.id.recycleview_header_footer).setOnClickListener(this);
        findViewById(R.id.recycleview_emptyview).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.listview_single_multi:
                startActivity(new Intent(this, ListViewActivity.class));
                break;
            case R.id.recycleview_single_multi:
                startActivity(new Intent(this, RecycleViewActivity.class));
                break;
            case R.id.recycleview_header_footer:
                startActivity(new Intent(this, HeaderAndFooterWrapperActivity.class));
                break;
            case R.id.recycleview_emptyview:
                startActivity(new Intent(this, EmptyViewWrapperActivity.class));
                break;
        }
    }

}
