package br.com.thiago.twittersearchtest.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thiago on 21/11/2015.
 */
public class TabsAdapter extends android.support.v4.app.FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    final int PAGE_COUNT = 3;
    private final Context context;


    public TabsAdapter(FragmentManager fm, Context ctx) {
        super(fm);
        this.context = ctx;
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);

        Log.i("LOG","addFragment :" + mFragmentList.size());
    }

    @Override
    public Fragment getItem(int position) {
        Bundle b = new Bundle();
        b.putInt("position", position);
        mFragmentList.get(position).setArguments(b);
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
