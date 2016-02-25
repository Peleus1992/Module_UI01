package edu.gatech.wguo64.module_ui01;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;


/**
 * Created by guoweidong on 10/24/15.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    SparseArray<android.support.v4.app.Fragment> registeredFragments = new SparseArray<android.support.v4.app.Fragment>();

    public ViewPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        switch (position) {
            case 0:
                MyFragment fragment1 = new MyFragment();
                return fragment1;
            case 1:
                MyFragment fragment2 = new MyFragment();
                return fragment2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        android.support.v4.app.Fragment fragment = (android.support.v4.app.Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public android.support.v4.app.Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}
