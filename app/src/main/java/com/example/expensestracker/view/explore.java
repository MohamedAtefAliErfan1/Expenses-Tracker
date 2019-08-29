package com.example.expensestracker.view;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.expensestracker.R;

import java.util.ArrayList;
import java.util.List;

public class explore extends AppCompatActivity implements statweekfrag.OnFragmentInteractionListener,statmonthfrag.OnFragmentInteractionListener,
        BlankFragment.OnFragmentInteractionListener, addfrag.OnFragmentInteractionListener,prevfrag.OnFragmentInteractionListener,statisfrag.OnFragmentInteractionListener {
boolean is_back =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Expenses Tracker");
        TabLayout tabLayout=findViewById(R.id.tablayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new addfrag(), "Add");
        adapter.addFragment(new prevfrag(), "Prev Expenses");
        adapter.addFragment(new statisfrag(), "Statistics");
       // adapter.addFragment(new BlankFragment(), "test");
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        if(is_back)
        {
            moveTaskToBack(true);
            finish();
        }
        else
        {
            Toast.makeText(this, "tap again to exit", Toast.LENGTH_SHORT).show();
            is_back=true;
        }
    }
}
