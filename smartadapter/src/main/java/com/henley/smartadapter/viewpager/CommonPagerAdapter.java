package com.henley.smartadapter.viewpager;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 实现View在ViewPager中进行滑动切换的适配器
 *
 * @author Henley
 * @date 2017/8/1 17:59
 */
public class CommonPagerAdapter extends PagerAdapter {

    private List<? extends View> mList;
    private List<? extends CharSequence> mTitles;
    private SparseArray<View> mSparseViews;

    public CommonPagerAdapter(List<? extends View> list) {
        this.mList = list;
    }

    public CommonPagerAdapter(List<? extends View> list, CharSequence... titles) {
        this.mList = list;
        if (titles != null && titles.length > 0) {
            this.mTitles = Arrays.asList(titles);
        }
    }

    public CommonPagerAdapter(List<? extends View> mList, List<? extends CharSequence> titles) {
        this.mList = mList;
        this.mTitles = titles;
    }

    public void refresh(List<? extends View> list) {
        if (list == null) {
            list = new ArrayList<>(0);
        }
        if (mSparseViews != null) {
            mSparseViews.clear();
        }
        this.mList = list;
        this.notifyDataSetChanged();

    }

    public View getItem(int position) {
        return mList == null ? null : mList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles == null ? null : mTitles.get(position);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object; // 来判断显示的是否是同一View
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // 当要显示的View可以进行缓存的时候，会调用这个方法进行显示View的初始化，我们将要显示的View加入到ViewGroup中，然后作为返回值返回即可
        View view = null;
        if (mSparseViews == null) {
            mSparseViews = new SparseArray<>(getCount());
        } else {
            view = mSparseViews.get(position);
        }
        if (view == null) {
            view = mList.get(position);
            mSparseViews.put(position, view);
        }
        container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // PagerAdapter默认只缓存三个View，如果滑动的View超出了缓存的范围，就销毁掉
        View view = mList.get(position);
        container.removeView(view);
    }

}
