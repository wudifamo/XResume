package xresume.k.com.xresume.bean;


import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ChatBean implements MultiItemEntity {
	public static final int CHAT_LEFT = 1;
	public static final int CHAT_RIGHT = 2;
	private int itemType;
	private String content;

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int getItemType() {
		return itemType;
	}
}
