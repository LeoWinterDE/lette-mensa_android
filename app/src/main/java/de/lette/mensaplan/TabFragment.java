package de.lette.mensaplan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.samples.apps.iosched.ui.widget.SlidingTabLayout;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class TabFragment extends Fragment {
    public static final String ARG_WOCHE = "ARG_WOCHE";
    private int woche;

    public static TabFragment newInstance(int woche) {
        Bundle args = new Bundle();
        args.putInt(ARG_WOCHE, woche + 1);
        TabFragment tabFragment = new TabFragment();
        tabFragment.setArguments(args);
        return tabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        woche = getArguments().getInt(ARG_WOCHE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_tabs, container, false);

        // Get the ViewPager and set it's PagerAdapter so that it can display
        // items
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), getActivity(), woche));

        // Give the SlidingTabLayout the ViewPager
        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        // Center the tabs in the layout
        slidingTabLayout.setDistributeEvenly(true);
        // Customize tab color
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.myPrimaryColor);
            }
        });

        slidingTabLayout.setViewPager(viewPager);
        slidingTabLayout.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        Calendar calendar = new GregorianCalendar(Locale.getDefault());
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        int today = calendar.get(Calendar.DAY_OF_WEEK);

        if (today >= 2 && today <= 6) {
            viewPager.setCurrentItem(today - 2);
        } else {
            viewPager.setCurrentItem(0);
        }
        viewPager.refreshDrawableState();
        return view;
    }
}