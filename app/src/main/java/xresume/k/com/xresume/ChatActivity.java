package xresume.k.com.xresume;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import xresume.k.com.xresume.adapter.ChatAdapter;
import xresume.k.com.xresume.bean.ChatBean;
import xresume.k.com.xresume.views.MEmojiView;

public class ChatActivity extends BaseActivity {
	protected BaseQuickAdapter mAdapter;
	private ArrayList<ChatBean> chatList = new ArrayList<>();
	private EditText etComment;
	private MEmojiView emojiView;

	@Override
	protected int setLayoutId() {
		return R.layout.activity_chat;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		toolbar.setTitle("ChatActivity");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		emojiView = (MEmojiView) findViewById(R.id.memoji);
		etComment = (EditText) findViewById(R.id.chat_et);
		emojiView.setEditText(ChatActivity.this, etComment, (ImageView) findViewById(R.id.smile));
		RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.swipe_target);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
		linearLayoutManager.setReverseLayout(true);
		mRecyclerView.setLayoutManager(linearLayoutManager);
		((DefaultItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
		for (int i = 0; i < 30; i++) {
			ChatBean chatBean = new ChatBean();
			if (i % 2 == 0) {
				chatBean.setItemType(2);
			} else {
				chatBean.setItemType(1);
			}
			chatBean.setContent(i + "" + i + i + i + i + i + i + i + i + i + i + i + i + i + i + i + i + i + i + i + i + i + i + i + i + i + i + i +
					i + i + i + i);
			chatList.add(chatBean);
		}
		mAdapter = new ChatAdapter(chatList);
		mRecyclerView.setAdapter(mAdapter);
		mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				emojiView.hideKeyboard();
				return false;
			}
		});
	}

	@Override
	public void onBackPressed() {
		if (emojiView.chat_face_container.getVisibility() == View.VISIBLE) {
			emojiView.hideKeyboard();
		} else {
			super.onBackPressed();
		}
	}
}
