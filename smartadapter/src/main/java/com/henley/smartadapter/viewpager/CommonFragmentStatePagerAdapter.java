package com.henley.smartadapter.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import java.util.List;

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

    private static final int UPDATE_POSITION_NONE = -1;
    private List<Fragment> mList;
    private FragmentManager fragmentManager;
    private int fragmentUpdatePosition = UPDATE_POSITION_NONE;


    public CommonFragmentStatePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
    }

    public CommonFragmentStatePagerAdapter(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager);
        this.mList = list;
        this.fragmentManager = fragmentManager;
    }

    /**
     * 设置要刷新的Fragmnet的索引
     */
    public void setUpdateFragmentPosition(int position) {
        this.fragmentUpdatePosition = position;
        this.notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return mList == null ? null : mList.get(position);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position); // 得到缓存的fragment
        if (fragmentUpdatePosition == position) {
            String fragmentTag = fragment.getTag(); // 得到tag
            FragmentTransaction transaction = fragmentManager.beginTransaction(); // 如果这个fragment需要更新
            transaction.remove(fragment); // 移除旧的fragment
            fragment = mList.get(position);// 换成新的fragment
            transaction.add(container.getId(), fragment, fragmentTag); // 添加新fragment时必须用前面获得的tag
            transaction.attach(fragment);
            transaction.commit();
            fragmentUpdatePosition = UPDATE_POSITION_NONE; // 复位更新标志
        }
        return fragment;
    }

}
