package xresume.k.com.xresume.views;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

import xresume.k.com.xresume.R;

public class ProgressView extends View implements View.OnClickListener {


	private String mTitleText = "";
	private Paint paint;
	private Paint textPaint;
	private Paint circlePaint;
	/**
	 * 文字绘制所在矩形
	 */
	private Rect textRect = new Rect();
	/**
	 * 根据view的大小设置成矩形
	 */
	private RectF rectf = new RectF();
	/**
	 * 圆角半径
	 */
	private int circleAngle;
	/**
	 * 默认两圆圆心之间的距离=需要移动的距离
	 */
	private int default_two_circle_distance;
	private int two_circle_distance;
	/**
	 * 是否开始绘制对勾
	 */
	private boolean startDrawOk = false;
	/**
	 * 路径--用来获取对勾的路径
	 */
	private Path path = new Path();
	/**
	 * 取路径的长度
	 */
	private PathMeasure pathMeasure;
	/**
	 * 对勾（√）画笔
	 */
	private Paint okPaint;
	private int circleRadius;

	interface AnimListner {
		void onStop();
	}

	private AnimListner animListner;

	void setAnimListner(AnimListner animListner) {
		this.animListner = animListner;
	}


	public ProgressView(Context context) {
		super(context);
		setOnClickListener(this);

		initPaint();
	}

	public ProgressView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	private void initPaint() {
		paint = new Paint();
		paint.setStrokeWidth(4);
		paint.setStyle(Paint.Style.FILL);
		paint.setAntiAlias(true);

		textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		textPaint.setTextAlign(Paint.Align.CENTER);
		textPaint.setTextSize(28);
		textPaint.setAntiAlias(true);

		okPaint = new Paint();
		okPaint.setStrokeWidth(5);
		okPaint.setStyle(Paint.Style.STROKE);
		okPaint.setAntiAlias(true);
		okPaint.setColor(Color.WHITE);

		circlePaint = new Paint();

		circlePaint.setAntiAlias(true);
		circlePaint.setColor(ContextCompat.getColor(getContext(), R.color.bluegreen));
		circlePaint.setStyle(Paint.Style.STROKE);//设置为空心
		circlePaint.setStrokeWidth(6);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		draw_oval_to_circle(canvas);
		drawText(canvas);
		if (startDrawOk) {
			canvas.drawPath(path, okPaint);
			canvas.drawCircle(getWidth() / 2, getHeight() / 2, circleRadius, circlePaint);
		}
	}

	/**
	 * 绘制对勾
	 */
	private void initOk() {
		//对勾的路径
		int height = getHeight();
		path.moveTo(default_two_circle_distance + height / 8 * 3, height / 2);
		path.lineTo(default_two_circle_distance + height / 2, height / 5 * 3);
		path.lineTo(default_two_circle_distance + height / 3 * 2, height / 5 * 2);

		pathMeasure = new PathMeasure(path, true);

	}

	/**
	 * 绘制长方形变成圆形
	 *
	 * @param canvas 画布
	 */
	private void draw_oval_to_circle(Canvas canvas) {

		rectf.left = two_circle_distance;
		rectf.top = 0;
		rectf.right = getWidth() - two_circle_distance;
		rectf.bottom = getHeight();

		//画圆角矩形
		canvas.drawRoundRect(rectf, circleAngle, circleAngle, paint);

	}

	/**
	 * 绘制文字
	 *
	 * @param canvas 画布
	 */
	private void drawText(Canvas canvas) {
		textRect.left = 0;
		textRect.top = 0;
		textRect.right = getWidth();
		textRect.bottom = getHeight();
		Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
		int baseline = (textRect.bottom + textRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
		//文字绘制到整个布局的中心位置
		canvas.drawText(mTitleText, textRect.centerX(), baseline, textPaint);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		default_two_circle_distance = (w - h) / 2;
		initOk();
	}

	@Override
	public void onClick(View v) {
		startDrawOk = false;
		ViewAnimator.animate(this)
				.custom(new AnimationListener.Update() {
					@Override
					public void update(View view, float value) {
						circleAngle = (int) value;
						invalidate();
					}
				}, 0, getHeight() / 2)
				.custom(new AnimationListener.Update() {
					@Override
					public void update(View view, float value) {
						two_circle_distance = (int) value;
						int alpha = 255 - (two_circle_distance * 255) / default_two_circle_distance;
						textPaint.setAlpha(alpha);
						invalidate();
					}
				}, 0, default_two_circle_distance)
				.duration(500)
				.onStop(new AnimationListener.Stop() {
					@Override
					public void onStop() {
						if (animListner != null) {
							animListner.onStop();
						}
					}
				})
				.start()
		;
	}

	public void setmTitleText(String mTitleText) {
		this.mTitleText = mTitleText;
	}

	public void setmTitleTextColor(int mTitleTextColor) {
		textPaint.setColor(mTitleTextColor);
	}

	public void setmTitleTextSize(int mTitleTextSize) {
		textPaint.setTextSize(mTitleTextSize);
	}

	public void setmBackColor(int mBackColor) {
		paint.setColor(mBackColor);
	}

	public void success() {
		ViewAnimator.animate(ProgressView.this)
				.custom(new AnimationListener.Update() {
					@Override
					public void update(View view, float value) {
						startDrawOk = true;
						DashPathEffect effect = new DashPathEffect(new float[]{pathMeasure.getLength(), pathMeasure.getLength()},
								value * pathMeasure.getLength());
						okPaint.setPathEffect(effect);

						circleRadius = (int) (getHeight() / 2 + (1 - value) * 20);
						circlePaint.setAlpha((int) (180 * value));
						invalidate();
					}
				}, 1, 0)
				.interpolator(new AccelerateInterpolator())
				.duration(300)
				.start();
	}
}
