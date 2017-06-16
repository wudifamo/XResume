package xresume.k.com.xresume.fragments;


import android.os.Bundle;

import xresume.k.com.xresume.R;

public class FirstFragment extends BaseFragment {

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_1);
	}
}
