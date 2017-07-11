package xresume.k.com.xresume;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

public class KKeyboard extends BaseActivity {

	private EditText medit;
	private RelativeLayout mainrl;
	private int eTop, eLeft;

	@Override
	protected int setLayoutId() {
		return R.layout.activity_kkeyboard;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mainrl = (RelativeLayout) findViewById(R.id.kkb_mainrl);
		medit = (EditText) findViewById(R.id.kedittext);
		medit.addTextChangedListener(watcher);

	}


	private TextWatcher watcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			if (count > 0) {
				if (eTop == 0) {
					int[] loc = new int[2];
					medit.getLocationInWindow(loc);
					eLeft = loc[0];
					eTop = loc[1];
				}
				TextPaint mtextp = medit.getPaint();
				int contentWidth = medit.getWidth() - medit.getPaddingLeft() - medit.getPaddingRight();
				int textWidth = (int) mtextp.measureText(s.toString().substring(0, start));
				if (textWidth > contentWidth - 2) {
					int coun = contentWidth * (textWidth / contentWidth);
					textWidth -= coun;
				}
				String mt = "";
				if (s.length() > 0) {
					mt = s.toString().substring(start, start + 1);
				}
				final TextView mtv = new TextView(mContext);
				mtv.setTextColor(Color.WHITE);
				mtv.setText(mt);
				RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout
						.LayoutParams.WRAP_CONTENT);
				rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
				rlp.leftMargin = eLeft + textWidth;
				mainrl.addView(mtv, rlp);
				ViewAnimator.animate(mtv).waitForHeight().translationY(-mHeight + eTop + mtv.getHeight())
						.duration(400)
						.interpolator(new DecelerateInterpolator())
						.onStop(new AnimationListener.Stop() {
							@Override
							public void onStop() {
								mainrl.removeView(mtv);
							}
						}).start();
				ObjectAnimator animator = ObjectAnimator.ofFloat(mtv, "TextSize", 50);
				animator.setDuration(400);
				animator.start();
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
									  int after) {
			// TODO Auto-generated method stub
			if (after == 0) {
				int eh = eTop;
				int ep = medit.getLineHeight();
				int contentWidth = medit.getWidth() - medit.getPaddingLeft() - medit.getPaddingRight();
				TextPaint mtextp = medit.getPaint();
				int textWidth = (int) mtextp.measureText(s.toString().substring(0, start));
				if (textWidth > contentWidth - 2) {
					int coun = contentWidth * (textWidth / contentWidth);
					int ecoun = ep * (textWidth / contentWidth);
					textWidth -= coun;
					eh += ecoun;
				}
				String mt = "";
				if (s.length() > 0) {
					mt = s.toString().substring(start, start + 1);
				}
				final TextView mtv = new TextView(mContext);
				mtv.setTextColor(Color.WHITE);
				mtv.setText(mt);
				RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout
						.LayoutParams.WRAP_CONTENT);
				rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
				rlp.topMargin = eh;
				rlp.leftMargin = eLeft + textWidth;
				mainrl.addView(mtv, rlp);
				ObjectAnimator animator = ObjectAnimator.ofFloat(mtv, "TextSize", 50);
				animator.setDuration(400);
				animator.start();
				ViewAnimator.animate(mtv).waitForHeight().translationY(mHeight)
						.duration(400)
						.interpolator(new AccelerateInterpolator())
						.onStop(new AnimationListener.Stop() {
							@Override
							public void onStop() {
								mainrl.removeView(mtv);
							}
						}).start();
			}
		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	};

}
