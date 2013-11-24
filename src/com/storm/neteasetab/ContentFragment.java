/*
 * Copyright 2013 Storm Zhang.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.storm.neteasetab;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ContentFragment extends Fragment implements OnRefreshListener {

	static final String TAG = ContentFragment.class.getSimpleName();

	private static final int DEFAULT_MSG = 0x01;

	private ProgressBar mProgressBar;
	private TextView mContentText;
	private String mContent;

	/*
	 * 用来标记是否强制初始化
	 */
	private boolean forceVisible = false;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DEFAULT_MSG:
				mProgressBar.setVisibility(View.GONE);
				mContentText.setVisibility(View.VISIBLE);
				mContentText.setText(mContent);
				break;
			}
		}
	};

	public static ContentFragment newInstance(String content) {
		ContentFragment fragment = new ContentFragment();
		fragment.mContent = content;
		return fragment;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_content, null);
	}

	public void onResume() {
		super.onResume();
		if (forceVisible) {
			initUI();
		}
	}

	private void initUI() {
		mProgressBar = (ProgressBar) getView().findViewById(R.id.pb_content);
		mContentText = (TextView) getView().findViewById(R.id.tv_content);
		mProgressBar.setVisibility(View.VISIBLE);
		mContentText.setVisibility(View.GONE);
		new Thread(r).start();
	}

	Runnable r = new Runnable() {
		@Override
		public void run() {
			try {
				Thread.sleep(2000);
				mHandler.sendEmptyMessage(DEFAULT_MSG);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};

	public void setVisible(boolean visible) {
		forceVisible = visible;
	}

	@Override
	public void onRefresh() {
		initUI();
	}
}
