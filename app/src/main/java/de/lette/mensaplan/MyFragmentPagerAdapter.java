package de.lette.mensaplan;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

// TODO: Adapter Beispiel
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[]{"MONTAG", "DIENSTAG", "MITTWOCH", "DONNERSTAG", "FREITAG"};
    final int PAGE_COUNT = tabTitles.length;
    @SuppressWarnings("unused")
    private Context context;
    private int woche;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context, int woche) {
        super(fm);
        this.context = context;
        this.woche = woche;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return SpeiseplanFragment.newInstance(position + 1, woche);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}