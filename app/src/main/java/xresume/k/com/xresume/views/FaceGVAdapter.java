package xresume.k.com.xresume.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import xresume.k.com.xresume.R;


public class FaceGVAdapter extends BaseAdapter {
	private List<Integer> myNeedList;
	private Context mContext;

	public FaceGVAdapter(List<Integer> myNeedList, Context mContext) {
		super();
		this.myNeedList = myNeedList;
		this.mContext = mContext;
	}

	public void clear() {
		this.mContext = null;
	}


	public int getCount() {

		return myNeedList.size();
	}


	public Object getItem(int position) {

		return myNeedList.get(position);
	}


	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHodler hodler;
		if (convertView == null) {
			hodler = new ViewHodler();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.face_image, null);

			hodler.tv_emoji = (TextView) convertView.findViewById(R.id.tv_emoji);
			hodler.iv_emoji = (ImageView) convertView.findViewById(R.id.iv_emoji);
			convertView.setTag(hodler);
		} else {
			hodler = (ViewHodler) convertView.getTag();
		}

		hodler.tv_emoji.setText(getMyString(myNeedList.get(position)));
//		hodler.iv_emoji.setImageResource(myNeedList.get(position));

		return convertView;
	}

	private String getMyString(int emoji) {

		String emojiString = getEmojiStringByUnicode(emoji);
		return emojiString;
	}

	private String getEmojiStringByUnicode(int unicode) {
		return new String(Character.toChars(unicode));
	}

	class ViewHodler {
		TextView tv_emoji;
		ImageView iv_emoji;
	}
}
