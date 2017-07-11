package xresume.k.com.xresume;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.animation.LinearInterpolator;

import com.github.florent37.viewanimator.ViewAnimator;

import xresume.k.com.xresume.fragments.FirstFragment;


public class MainActivity extends BaseActivity {

	@Override
	protected int setLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initAnims();
		initViewPager();
	}


	private void initViewPager() {
		ViewPager wowo = (ViewPager) findViewById(R.id.wowo_viewpager);
		wowo.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		wowo.setAdapter(new PagerAdapter(getSupportFragmentManager()));
	}

	private class PagerAdapter extends FragmentStatePagerAdapter {

		PagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return 4;
		}

		@Override
		public Fragment getItem(int position) {
			return new FirstFragment();
		}

	}

	private void initAnims() {
		//左下紫色
		ViewAnimator.animate(findViewById(R.id.main_circle4)).translationX(-50)
				.translationY(50)
				.interpolator(new LinearInterpolator())
				.duration(1500)
				.repeatMode(ValueAnimator.REVERSE)
				.repeatCount(ValueAnimator.INFINITE)
				.start();
		//右下
		ViewAnimator.animate(findViewById(R.id.main_circle5)).translationX(50)
				.translationY(80)
				.interpolator(new LinearInterpolator())
				.duration(2000)
				.repeatMode(ValueAnimator.REVERSE)
				.repeatCount(ValueAnimator.INFINITE)
				.start();
		//右蓝色
		ViewAnimator.animate(findViewById(R.id.main_circle3)).translationX(50)
				.interpolator(new LinearInterpolator())
				.duration(3000)
				.repeatMode(ValueAnimator.REVERSE)
				.repeatCount(ValueAnimator.INFINITE)
				.start();
		//右上绿色
		ViewAnimator.animate(findViewById(R.id.main_circle2)).translationX(20)
				.translationY(-30)
				.interpolator(new LinearInterpolator())
				.duration(2000)
				.repeatMode(ValueAnimator.REVERSE)
				.repeatCount(ValueAnimator.INFINITE)
				.start();
		//左上
		ViewAnimator.animate(findViewById(R.id.main_circle0)).translationX(-50)
				.translationY(-30)
				.interpolator(new LinearInterpolator())
				.duration(2000)
				.repeatMode(ValueAnimator.REVERSE)
				.repeatCount(ValueAnimator.INFINITE)
				.start();
		//背黄色
		ViewAnimator.animate(findViewById(R.id.main_circle1)).translationX(100)
				.interpolator(new LinearInterpolator())
				.duration(2000)
				.repeatMode(ValueAnimator.REVERSE)
				.repeatCount(ValueAnimator.INFINITE)
				.start();
	}


}
