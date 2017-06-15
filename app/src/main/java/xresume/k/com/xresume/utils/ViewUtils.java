package xresume.k.com.xresume.utils;


import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class ViewUtils {

	public static int dip2px(Context context, double d) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (d * scale + 0.5f);
	}

	public static DisplayMetrics displayMetrics(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(dm);
		//LogUtils.debug("screen width=" + dm.widthPixels + "px, screen height=" + dm.heightPixels
		//        + "px, densityDpi=" + dm.densityDpi + ", density=" + dm.density);
		return dm;
	}
}
