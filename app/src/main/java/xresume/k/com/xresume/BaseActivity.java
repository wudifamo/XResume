package xresume.k.com.xresume;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import xresume.k.com.xresume.utils.ViewUtils;

public class BaseActivity extends AppCompatActivity {

	protected Context mContext;
	protected int mHeight,mWidth;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		DisplayMetrics metrics = ViewUtils.displayMetrics(this);
		mWidth = metrics.widthPixels;
		mHeight = metrics.heightPixels;
		setTranslateStatusBar();
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		if (toolbar != null) {
			setSupportActionBar(toolbar);
		}
	}

	private void setTranslateStatusBar() {
		//透明状态栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		//透明导航栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
	}

}
