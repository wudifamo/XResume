package xresume.k.com.xresume;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;
import com.plattysoft.leonids.ParticleSystem;

import java.util.Random;

public class FireWorkActivity extends BaseActivity {

	private FrameLayout bubbleLayout;

	@Override
	protected int setLayoutId() {
		return R.layout.activity_fire_work;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		onFireWork();
	}


	public void onFireWork() {
		bubbleLayout = (FrameLayout) findViewById(R.id.animation_bubble);
		bubbleLayout.removeAllViews();
		fire();
	}

	private void fire() {
		final View view = new View(mContext);
		view.setBackgroundColor(Color.BLACK);
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(1, 20);
		lp.topMargin = mHeight - 80;
		lp.leftMargin = (int) (new Random().nextDouble() * (mWidth - 200) + 100);
		bubbleLayout.addView(view, lp);
		ViewAnimator.animate(view)
				.waitForHeight()
				.translationY(-800)
				.interpolator(new DecelerateInterpolator(1f))
				.duration(2000)
				.onStop(new AnimationListener.Stop() {
					@Override
					public void onStop() {
						ParticleSystem ps = new ParticleSystem(FireWorkActivity.this, 100, R.drawable.star_pink, 1000);
						ps.setScaleRange(0.7f, 1.3f);
						ps.setSpeedRange(0.01f, 0.1f);
						ps.setRotationSpeedRange(90, 180);
						ps.setFadeOut(200, new AccelerateInterpolator());
						ps.oneShot(view, 70);

						ParticleSystem ps2 = new ParticleSystem(FireWorkActivity.this, 100, R.drawable.main_firework, 1000);
						ps2.setScaleRange(0.7f, 1.3f);
						ps2.setSpeedRange(0.01f, 0.1f);
						ps2.setFadeOut(200, new AccelerateInterpolator());
						ps2.oneShot(view, 70);
						bubbleLayout.removeView(view);
						fire();
					}
				})
				.start();
	}
}
