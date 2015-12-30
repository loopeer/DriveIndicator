package com.loopeer.android.librarys.app;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.loopeer.android.librarys.dropindicator.DrivePagerIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        final List<View> views = new ArrayList<>();
        views.add(getLayoutInflater().inflate(R.layout.layout_pager, null));
        views.add(getLayoutInflater().inflate(R.layout.layout_pager, null));
        views.add(getLayoutInflater().inflate(R.layout.layout_pager, null));

        final DrivePagerIndicator driveIndicator = (DrivePagerIndicator) findViewById(R.id.drive_page_indicator_view);
        driveIndicator.setCount(3);
        int height = driveIndicator.getLayoutParams().height;
        int width = driveIndicator.getLayoutParams().width;
        driveIndicator.setRadius(0.15F * height);
        driveIndicator.setCircleX(width / 4);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                driveIndicator.setPositionAndOffset(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        viewPager.setAdapter(new PagerAdapter() {

            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(views.get(position));
            }


            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(views.get(position), 0);
                return views.get(position);
            }
        });
    }
}
