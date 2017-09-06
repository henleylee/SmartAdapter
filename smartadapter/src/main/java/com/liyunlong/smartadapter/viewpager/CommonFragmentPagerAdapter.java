package com.liyunlong.smartadapter.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

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
 * @author liyunlong
 * @date 2017/8/1 18:04
 */
public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final int UPDATE_POSITION_NONE = -1;
    private List<Fragment> mList;
    private FragmentManager fragmentManager;
    private int fragmentUpdatePosition = UPDATE_POSITION_NONE;

    public CommonFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
    }

    public CommonFragmentPagerAdapter(FragmentManager fragmentManager, List<Fragment> list) {
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
