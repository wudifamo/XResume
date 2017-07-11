package xresume.k.com.xresume.adapter;


import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import xresume.k.com.xresume.R;
import xresume.k.com.xresume.bean.ChatBean;

public class ChatAdapter extends BaseMultiItemQuickAdapter<ChatBean, BaseViewHolder> {

	public ChatAdapter(List data) {
		super(data);
		addItemType(ChatBean.CHAT_LEFT, R.layout.item_chat_left);
		addItemType(ChatBean.CHAT_RIGHT, R.layout.item_chat_right);
	}

	@Override
	protected void convert(BaseViewHolder helper, ChatBean item) {
		helper.setText(R.id.content, item.getContent());
	}

}
