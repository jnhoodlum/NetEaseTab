package com.storm.neteasetab;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.util.Log;

import com.viewpagerindicator.TabPageIndicator;

public class MainActivity extends FragmentActivity {

	static final String TAG = MainActivity.class.getSimpleName();

	private TabPageIndicator mIndicator;
	private ViewPager mViewPager;
	private ContentAdapter mAdapter;
	private FragmentManager fm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initUI();
	}

	private void initUI() {
		fm = getSupportFragmentManager();
		fm.executePendingTransactions();
		mAdapter = new ContentAdapter(fm, getTitles(), getFragments());
		mIndicator = (TabPageIndicator) findViewById(R.id.indicator);
		mViewPager = (ViewPager) findViewById(R.id.vp_content);
		mViewPager.setAdapter(mAdapter);

		// 默认调用第一个Fragment的onShow()
		mIndicator.setViewPager(mViewPager, 0);
		mIndicator.setOnPageChangeListener(pageChangeListener);
		mAdapter.getItem(0).setVisible(true);
	}

	SimpleOnPageChangeListener pageChangeListener = new SimpleOnPageChangeListener() {
		@Override
		public void onPageSelected(int arg0) {
			Log.e("MainActivity", arg0 + "");
			mAdapter.getItem(arg0).onRefresh();
		}
	};

	private ArrayList<ContentFragment> getFragments() {
		ArrayList<ContentFragment> fragments = new ArrayList<ContentFragment>();
		for (String title : getTitles()) {
			fragments.add(ContentFragment.newInstance(title));
		}
		return fragments;
	}

	private String[] getTitles() {
		return getResources().getStringArray(R.array.main_tab_titles);
	}
}
