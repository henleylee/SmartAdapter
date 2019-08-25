package com.henley.smartadapter.viewpager;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

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

    private List<? extends View> mViews;
    private List<? extends CharSequence> mTitles;
    private SparseArray<View> mSparseViews;

    public CommonPagerAdapter(List<? extends View> views) {
        this.mViews = views;
    }

    public CommonPagerAdapter(List<? extends View> views, CharSequence... titles) {
        this.mViews = views;
        if (titles != null && titles.length > 0) {
            this.mTitles = Arrays.asList(titles);
        }
    }

    public CommonPagerAdapter(List<? extends View> views, List<? extends CharSequence> titles) {
        this.mViews = views;
        this.mTitles = titles;
    }

    public void refresh(List<? extends View> list) {
        if (list == null) {
            list = new ArrayList<>(0);
        }
        if (mSparseViews != null) {
            mSparseViews.clear();
        }
        this.mViews = list;
        this.notifyDataSetChanged();

    }

    public View getItem(int position) {
        return mViews == null ? null : mViews.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles == null ? null : mTitles.get(position);
    }

    @Override
    public int getCount() {
        return mViews == null ? 0 : mViews.size();
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
            view = mViews.get(position);
            mSparseViews.put(position, view);
        }
        container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // PagerAdapter默认只缓存三个View，如果滑动的View超出了缓存的范围，就销毁掉
        View view = mViews.get(position);
        container.removeView(view);
    }

}
