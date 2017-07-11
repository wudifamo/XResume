package xresume.k.com.xresume;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;

import xresume.k.com.xresume.utils.ViewUtils;

public abstract class BaseActivity extends AppCompatActivity {

	protected Context mContext;
	protected int mHeight,mWidth;
	protected Toolbar toolbar;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(setLayoutId());
		mContext = this;
		DisplayMetrics metrics = ViewUtils.displayMetrics(this);
		mWidth = metrics.widthPixels;
		mHeight = metrics.heightPixels;
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		if (toolbar != null) {
			setSupportActionBar(toolbar);
		}
	}

	protected abstract int setLayoutId();

}
