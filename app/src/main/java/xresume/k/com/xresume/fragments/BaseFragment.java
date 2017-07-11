package xresume.k.com.xresume.fragments;


import android.content.Context;
import android.os.Bundle;

import com.shizhefei.fragment.LazyFragment;

public class BaseFragment extends LazyFragment {
	protected Context mContext;

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		mContext = getActivity();
	}
}
