package xresume.k.com.xresume.views;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Selection;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.dreamtobe.kpswitch.util.KPSwitchConflictUtil;
import cn.dreamtobe.kpswitch.util.KeyboardUtil;
import cn.dreamtobe.kpswitch.widget.KPSwitchPanelLinearLayout;
import xresume.k.com.xresume.R;

public class MEmojiView extends LinearLayout {
	public KPSwitchPanelLinearLayout chat_face_container;
	private ViewPager mViewPager;
	private LinearLayout mDotsLayout;
	private List<View> views = new ArrayList<View>();
	private Context mContext;
	private List<Integer> staticFacesList;
	private List<Integer> myNeedList;
	private int myPosition;
	// 7列3行
	private int columns = 6;
	private int rows = 4;
	private EditText input;
	private int cp = 0;

	private int[] arrEmoji = {0x1F602, 0x1F603, 0x1F604, 0x1F605, 0x1F606, 0x1F609, 0x1F60A, 0x1F60B, 0x1F60C, 0x1F60D, 0x1F60F, 0x1F612, 0x1F613,
			0x1F614, 0x1F616, 0x1F618, 0x1F61A, 0x1F61C, 0x1F61D, 0x1F61E, 0x1F620, 0x1F621, 0x1F622, 0x1F623, 0x1F624, 0x1F625, 0x1F628, 0x1F629,
			0x1F62A, 0x1F62B, 0x1F62D, 0x1F630, 0x1F631, 0x1F632, 0x1F633, 0x1F635, 0x1F637, 0x1F638, 0x1F639, 0x1F63A, 0x1F63B, 0x1F63C, 0x1F63D,
			0x1F63E, 0x1F63F, 0x1F640, 0x1F645, 0x1F646, 0x1F647, 0x1F648, 0x1F649, 0x1F64A, 0x1F64B, 0x1F64C, 0x1F64D, 0x1F64E, 0x1F64F};

	private List<Integer> getList(int[] arrEmoji) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < arrEmoji.length; i++) {
			list.add(arrEmoji[i]);
		}
		return list;
	}

	public MEmojiView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MEmojiView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		if (!isInEditMode()) {
			mContext = context;
			View meView = LayoutInflater.from(context).inflate(R.layout.chat_face_container, null);
			chat_face_container = (KPSwitchPanelLinearLayout) meView.findViewById(R.id.mej_mainll);
			// 表情布局
			mViewPager = (ViewPager) meView.findViewById(R.id.face_viewpager);
			mViewPager.addOnPageChangeListener(new PageChange());
			mDotsLayout = (LinearLayout) meView.findViewById(R.id.face_dots_container);
			addView(meView);
			myNeedList = getList(arrEmoji);
			initStaticFaces();
			InitViewPager();
		}
	}

	public MEmojiView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public void setEditText(Activity activity, EditText et, ImageView iv) {
		input = et;
		KeyboardUtil.attach(activity, chat_face_container);
		KPSwitchConflictUtil.attach(chat_face_container, iv, input);
	}

	/*
	 * 初始表情 *
	 */
	private void InitViewPager() {
		// 获取页数
		for (int i = 0; i < getPagerCount(); i++) {
			views.add(viewPagerItem(i));
			LayoutParams params = new LayoutParams(16, 16);
			mDotsLayout.addView(dotsItem(i), params);
		}
		FaceVPAdapter mVpAdapter = new FaceVPAdapter(views);
		mViewPager.setAdapter(mVpAdapter);
		mDotsLayout.getChildAt(0).setSelected(true);
	}

	private ImageView dotsItem(int position) {
		View layout = LayoutInflater.from(mContext).inflate(R.layout.dot_image, null);
		ImageView iv = (ImageView) layout.findViewById(R.id.face_dot);
		iv.setId(position);
		return iv;
	}

	/**
	 * 根据表情数量以及GridView设置的行数和列数计算Pager数量
	 *
	 * @return
	 */
	private int getPagerCount() {
		int count = arrEmoji.length;
		return count % (columns * rows - 1) == 0 ? count / (columns * rows - 1) : count / (columns * rows - 1) + 1;
	}

	/**
	 * 表情页改变时，dots效果也要跟着改变
	 */
	class PageChange implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int arg0) {
			for (int i = 0; i < mDotsLayout.getChildCount(); i++) {
				mDotsLayout.getChildAt(i).setSelected(false);
			}
			mDotsLayout.getChildAt(arg0).setSelected(true);
			cp = arg0;
		}

	}

	private View viewPagerItem(int position) {
		View layout = LayoutInflater.from(mContext).inflate(R.layout.face_gridview, null);// 表情布局
		GridView gridview = (GridView) layout.findViewById(R.id.chart_face_gv);
		/**
		 * 注：因为每一页末尾都有一个删除图标，所以每一页的实际表情columns *　rows　－　1; 空出最后一个位置给删除图标
		 * */
//		List<Integer> subList = new ArrayList<Integer>();
//		subList.addAll(staticFacesList.subList(position * (columns * rows),
//				(columns * rows) * (position + 1) > staticFacesList.size() ? staticFacesList.size() : (columns * rows) * (position + 1)));
		/**
		 * 末尾添加删除图标
		 * */

		FaceGVAdapter mGvAdapter = new FaceGVAdapter(staticFacesList, mContext);
		gridview.setAdapter(mGvAdapter);
		gridview.setNumColumns(columns);
		// 单击表情执行的操作
		gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				myPosition = position + ((columns * rows) * cp);

				String png = ((RelativeLayout) view).getChildAt(0).toString();
				if (!png.contains("emotion_del_normal")) {// 如果不是删除图标
					insert(new String(Character.toChars((arrEmoji[myPosition]))));
				} else {
					delete();
				}

			}
		});

		return gridview;
	}

	/**
	 * 向输入框里添加表情
	 */
	private void insert(CharSequence text) {

		int iCursorStart = Selection.getSelectionStart((input.getText()));
		int iCursorEnd = Selection.getSelectionEnd((input.getText()));
		if (iCursorStart != iCursorEnd) {
			input.getText().replace(iCursorStart, iCursorEnd, "");
		}
		int iCursor = Selection.getSelectionEnd((input.getText()));
		input.getText().insert(iCursor, text);
	}

	/**
	 * 删除图标执行事件
	 * 注：如果删除的是表情，在删除时实际删除的是tempText即图片占位的字符串，所以必需一次性删除掉tempText，才能将图片删除
	 */
	private void delete() {
		if (input.getText().length() != 0) {
			int iCursorEnd = Selection.getSelectionEnd(input.getText());
			int iCursorStart = Selection.getSelectionStart(input.getText());
			if (iCursorEnd > 0) {
				if (iCursorEnd == iCursorStart) {
					if (isDeletePng(iCursorEnd)) {
						String st = "#[face/png/f_static_000.png]#";
						input.getText().delete(iCursorEnd - st.length(), iCursorEnd);
					} else {
						input.getText().delete(iCursorEnd - 1, iCursorEnd);
					}
				} else {
					input.getText().delete(iCursorStart, iCursorEnd);
				}
			}
		}
	}

	/**
	 * 初始化表情列表staticFacesList
	 */
	private void initStaticFaces() {
		try {
			staticFacesList = new ArrayList<Integer>();
			// String[] faces = getAssets().list("face/png");
			// 将Assets中的表情名称转为字符串一一添加进staticFacesList
			for (int i = 0; i < myNeedList.size(); i++) {
				staticFacesList.add(myNeedList.get(i));
			}
			// 去掉删除图片
			staticFacesList.remove("emotion_del_normal.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断即将删除的字符串是否是图片占位字符串tempText 如果是：则讲删除整个tempText
	 **/
	private boolean isDeletePng(int cursor) {
		String st = "#[face/png/f_static_000.png]#";
		String content = input.getText().toString().substring(0, cursor);
		if (content.length() >= st.length()) {
			String checkStr = content.substring(content.length() - st.length(), content.length());
			String regex = "(\\#\\[face/png/f_static_)\\d{3}(.png\\]\\#)";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(checkStr);
			return m.matches();
		}
		return false;
	}

//	private SpannableStringBuilder getFace(int png) {
//
//		SpannableStringBuilder sb = new SpannableStringBuilder();
//		try {
//			/**
//			 * 经过测试，虽然这里tempText被替换为png显示，但是但我单击发送按钮时，获取到輸入框的内容是tempText的值而不是png
//			 * 所以这里对这个tempText值做特殊处理
//			 * 格式：#[face/png/f_static_000.png]#，以方便判斷當前圖片是哪一個
//			 * */
//
//			String tempText = "0x" + png;
//			sb.append(getEmojiStringByUnicode(arrEmoji[myPosition]));
//			sb.setSpan(getEmojiStringByUnicode((arrEmoji[myPosition])), sb.length() - tempText.length(), sb.length(),
//					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return sb;
//	}

//	private String getEmojiStringByUnicode(int unicode) {
//		return new String(Character.toChars(unicode));
//	}

	public void hideKeyboard() {
		KPSwitchConflictUtil.hidePanelAndKeyboard(chat_face_container);
	}

	public void showKeyboard() {
		KPSwitchConflictUtil.showKeyboard(chat_face_container, input);
		input.requestFocus();
	}

}
