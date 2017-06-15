package xresume.k.com.xresume;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;

import com.github.florent37.viewanimator.ViewAnimator;


public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initAnims();
	}

	private void initAnims() {
		//左下紫色
		ViewAnimator.animate(findViewById(R.id.main_circle4)).translationX(-50)
				.translationY(50)
				.interpolator(new LinearInterpolator())
				.duration(2000)
				.repeatMode(ValueAnimator.REVERSE)
				.repeatCount(ValueAnimator.INFINITE)
				.start();
		//右下
		ViewAnimator.animate(findViewById(R.id.main_circle5)).translationX(50)
				.translationY(80)
				.interpolator(new LinearInterpolator())
				.duration(2500)
				.repeatMode(ValueAnimator.REVERSE)
				.repeatCount(ValueAnimator.INFINITE)
				.start();
		//右蓝色
		ViewAnimator.animate(findViewById(R.id.main_circle3)).translationX(50)
				.interpolator(new LinearInterpolator())
				.duration(4000)
				.repeatMode(ValueAnimator.REVERSE)
				.repeatCount(ValueAnimator.INFINITE)
				.start();
		//右上绿色
		ViewAnimator.animate(findViewById(R.id.main_circle2)).translationX(20)
				.translationY(-30)
				.interpolator(new LinearInterpolator())
				.duration(3000)
				.repeatMode(ValueAnimator.REVERSE)
				.repeatCount(ValueAnimator.INFINITE)
				.start();
		//左上
		ViewAnimator.animate(findViewById(R.id.main_circle0)).translationX(-50)
				.translationY(-30)
				.interpolator(new LinearInterpolator())
				.duration(3000)
				.repeatMode(ValueAnimator.REVERSE)
				.repeatCount(ValueAnimator.INFINITE)
				.start();
		//背黄色
		ViewAnimator.animate(findViewById(R.id.main_circle1)).translationX(100)
				.interpolator(new LinearInterpolator())
				.duration(3000)
				.repeatMode(ValueAnimator.REVERSE)
				.repeatCount(ValueAnimator.INFINITE)
				.start();
	}


}
