package xresume.k.com.xresume.adapter;


import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import xresume.k.com.xresume.R;
import xresume.k.com.xresume.bean.ExBean;

public class FirstAdapter extends BaseMultiItemQuickAdapter<ExBean, BaseViewHolder> {
	public FirstAdapter(List<ExBean> data) {
		super(data);
		addItemType(0, R.layout.item_first_left);
		addItemType(1, R.layout.item_first_right);
	}

	@Override
	protected void convert(BaseViewHolder baseViewHolder, ExBean exBean) {
		baseViewHolder.setText(R.id.name, exBean.getName())
				.setImageResource(R.id.img, exBean.getIcon())
		;
	}
}
