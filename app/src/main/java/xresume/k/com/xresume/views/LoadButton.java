package xresume.k.com.xresume.views;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import xresume.k.com.xresume.R;

public class LoadButton extends FrameLayout implements ProgressView.AnimListner {

	private Context mContext;
	MaterialProgressDrawable mProgress;
	private ProgressView progressView;

	public LoadButton(@NonNull Context context) {
		super(context);
		init(context, null);
	}

	public LoadButton(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {
		mContext = context;
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LoadButton);
		progressView = new ProgressView(mContext);
		progressView.setAnimListner(this);
		int n = a.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
				case R.styleable.LoadButton_android_text:
					progressView.setmTitleText(a.getString(attr));
					break;
				case R.styleable.LoadButton_android_textColor:
					// 默认颜色设置为黑色
					progressView.setmTitleTextColor(a.getColor(attr, Color.BLACK));
					break;
				case R.styleable.LoadButton_android_textSize:
					// 默认设置为16sp，TypeValue也可以把sp转化为px
					progressView.setmTitleTextSize(a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
							TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics())));
					break;
				case R.styleable.LoadButton_backcolor:
					progressView.setmBackColor(a.getColor(attr, Color.GREEN));
					break;

			}

		}
		a.recycle();
		LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		addView(progressView, lp);

		ImageView imageView = new ImageView(mContext);
		LayoutParams lp1 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		addView(imageView, lp1);
		mProgress = new MaterialProgressDrawable(mContext, imageView);
		//圈圈颜色,可以是多种颜色
		mProgress.setColorSchemeColors(Color.WHITE);
		//设置圈圈的各种大小
		mProgress.updateSizes(MaterialProgressDrawable.LARGE);
		imageView.setImageDrawable(mProgress);
		//透明度，0-255
		mProgress.setAlpha((255));
		imageView.setVisibility(INVISIBLE);
	}

	@Override
	public void onStop() {
		if (!mProgress.isRunning()) {
			mProgress.start();
		}
		new CountDownTimer(2000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {

			}

			@Override
			public void onFinish() {
				mProgress.stop();
				progressView.success();
			}
		}.start();
	}
}
