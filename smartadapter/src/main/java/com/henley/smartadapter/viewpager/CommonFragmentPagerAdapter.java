package com.henley.smartadapter.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 实现Fragment在ViewPager中进行滑动切换的适配器
 * <ul>
 * <strong>注意：</strong>
 * <li>当使用FragmentPagerAdapter的时候，它的宿主ViewPager必须有一个id。
 * <li>FragmentPagerAdapter会对浏览过的Fragment进行缓存，保存这些界面的临时状态，这样当左右滑动的时候，界面切换更加的流畅。
 * <li>FragmentPagerAdapter最适合用来做固定的较少数量的场合，因为会缓存浏览过的Fragment，这样会增加程序占用的内存。
 * <li>FragmentPagerAdapter可能不经意间会造成内存未正常回收，严重导致内存溢出，比如图片资源没有释放，资源引用问题。
 * </ul>
 *
 * @author Henley
 * @date 2017/8/1 18:04
 */
public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<? extends Fragment> mFragments;
    private List<? extends CharSequence> mTitles;

    public CommonFragmentPagerAdapter(FragmentManager fm, List<? extends Fragment> fragments) {
        this(fm, fragments, null);
    }

    public CommonFragmentPagerAdapter(FragmentManager fm, List<? extends Fragment> fragments, List<? extends CharSequence> titles) {
        super(fm);
        this.mFragments = fragments;
        this.mTitles = titles;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles == null ? null : mTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments == null ? null : mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }


}
