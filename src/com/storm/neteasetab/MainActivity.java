package com.storm.neteasetab;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.TabPageIndicator;

public class MainActivity extends FragmentActivity {

	private TabPageIndicator mIndicator;
	private ViewPager mViewPager;
	private ContentAdapter mAdapter;
	private String[] mTitles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initUI();
	}

	private void initUI() {
		mIndicator = (TabPageIndicator) findViewById(R.id.indicator);
		mViewPager = (ViewPager) findViewById(R.id.vp_content);
		mAdapter = new ContentAdapter(getSupportFragmentManager(), getFragments(), mTitles);
		mViewPager.setAdapter(mAdapter);
		mIndicator.setViewPager(mViewPager);
	}

	private ArrayList<ContentFragment> getFragments() {
		ArrayList<ContentFragment> fragments = new ArrayList<ContentFragment>();
		mTitles = getResources().getStringArray(R.array.main_tab_titles);
		for (String title : mTitles) {
			fragments.add(ContentFragment.newInstance(title));
		}
		return fragments;
	}
}
