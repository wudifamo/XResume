package xresume.k.com.xresume.fragments;


import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import xresume.k.com.xresume.R;
import xresume.k.com.xresume.adapter.FirstAdapter;
import xresume.k.com.xresume.bean.ExBean;

public class FirstFragment extends BaseFragment {

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_1);
		RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
		mRecyclerView.setLayoutManager(linearLayoutManager);
		((DefaultItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
		FirstAdapter fadapter = new FirstAdapter(generateDatas());
		mRecyclerView.setAdapter(fadapter);
	}

	private ArrayList<ExBean> generateDatas() {
		ArrayList<ExBean> exList = new ArrayList<>();
		exList.add(new ExBean(":ils", R.mipmap.ic_launcher, "2012-12-12", 0));
		exList.add(new ExBean(":ils", R.mipmap.ic_launcher, "2012-12-12", 1));
		exList.add(new ExBean(":ils", R.mipmap.ic_launcher, "2012-12-12", 0));
		exList.add(new ExBean(":ils", R.mipmap.ic_launcher, "2012-12-12", 1));
		exList.add(new ExBean(":ils", R.mipmap.ic_launcher, "2012-12-12", 0));
		exList.add(new ExBean(":ils", R.mipmap.ic_launcher, "2012-12-12", 1));
		exList.add(new ExBean(":ils", R.mipmap.ic_launcher, "2012-12-12", 0));
		exList.add(new ExBean(":ils", R.mipmap.ic_launcher, "2012-12-12", 1));
		exList.add(new ExBean(":ils", R.mipmap.ic_launcher, "2012-12-12", 0));
		exList.add(new ExBean(":ils", R.mipmap.ic_launcher, "2012-12-12", 1));
		exList.add(new ExBean(":ils", R.mipmap.ic_launcher, "2012-12-12", 0));
		return exList;
	}


}
