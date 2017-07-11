package xresume.k.com.xresume.bean;


import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ExBean implements MultiItemEntity {
	private String name;
	private int icon;
	private String date;
	private int type;

	public ExBean(String name, int icon, String date, int type) {
		this.name = name;
		this.icon = icon;
		this.date = date;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public int getItemType() {
		return type;
	}
}
