package com.example.android.tourguide;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Marcin on 2017-05-05.
 */

public class CategoryAdapter extends FragmentPagerAdapter {

    /** Context of the app */
    private Context mContext;

    /**
     * Create a new {@link CategoryAdapter} object.
     *
     * @param context is the context of the app
     * @param fm is the fragment manager that will keep each fragment's state in the adapter
     *           across swipes.
     */
    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new WielunFragment();
            case 1:
                return new WroclawFragment();
            case 2:
                return new PoznanFragment();
            case 3:
                return new CzestochowaFragment();
            default:
                return null;
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_Wielun);
        } else if (position == 1) {
            return mContext.getString(R.string.category_Wroclaw);
        } else if (position == 2) {
            return mContext.getString(R.string.category_Poznan);
        } else {
            return mContext.getString(R.string.category_Czestochowa);
        }
    }
}
