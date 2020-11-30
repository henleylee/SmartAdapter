package com.henley.smartadapter.viewpager;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * 实现Fragment在ViewPager中进行滑动切换的适配器
 * <ul>
 * <strong>注意：</strong>
 * <li>当使用FragmentStatePagerAdapter的时候，它的宿主ViewPager必须有一个id。
 * <li>FragmentStatePagerAdapter拥有自己的缓存策略，当和ViewPager配合使用的时候，默认会缓存当前Fragment以及左边一个、右边一个，一共三个Fragment对象。
 * <li>当Fragment对用户不可见的时候，整个Fragment会被销毁，只会保存Fragment的保存状态。
 * <li>FragmentStatePagerAdapter适合用于很多界面之间的转换，而且消耗更少的内存资源。
 * </ul>
 *
 * @author Henley
 * @date 2017/8/1 18:07
 */
public class CommonFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    private final List<? extends Fragment> mFragments;
    private final List<? extends CharSequence> mTitles;

    public CommonFragmentStatePagerAdapter(@NonNull FragmentManager fm, @NonNull List<? extends Fragment> fragments) {
        this(fm, fragments, null);
    }

    public CommonFragmentStatePagerAdapter(@NonNull FragmentManager fm, @NonNull List<? extends Fragment> fragments, List<? extends CharSequence> titles) {
        this(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments, titles);
    }

    public CommonFragmentStatePagerAdapter(@NonNull FragmentManager fm, int behavior, @NonNull List<? extends Fragment> fragments) {
        this(fm, behavior, fragments, null);
    }

    public CommonFragmentStatePagerAdapter(@NonNull FragmentManager fm, int behavior, @NonNull List<? extends Fragment> fragments, List<? extends CharSequence> titles) {
        super(fm, behavior);
        this.mFragments = fragments;
        this.mTitles = titles;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles == null ? null : mTitles.get(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
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
