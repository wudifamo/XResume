package xresume.k.com.xresume;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class BaseActivity extends AppCompatActivity {

	protected Context mContext;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext=this;
		setTranslateStatusBar();
	}

	private void setTranslateStatusBar() {
		//透明状态栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		//透明导航栏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
	}

}
