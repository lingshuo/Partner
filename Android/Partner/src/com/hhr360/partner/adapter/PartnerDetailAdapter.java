package com.hhr360.partner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hhr360.partner.R;

public class PartnerDetailAdapter extends BaseAdapter {
	public Context mContext;

	public PartnerDetailAdapter(Context context) {
		mContext = context;
	}

	@Override
	public int getCount() {
		return 10;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// ViewHolder holder;
		// if (convertView == null) {
		// holder = new ViewHolder();
		convertView = LayoutInflater.from(mContext).inflate(
				R.layout.listview_partner_item, null);
		// convertView = holder.nameTv = (TextView) convertView
		// .findViewById(R.id.name_tv);
		// // convertV
		// } else {
		// holder = (ViewHolder) convertView.getTag();
		// }
		return convertView;
	}

	class ViewHolder {
		TextView nameTv;
		TextView contributeTv;
		TextView partnerNumTv;
		TextView monthProfitTv;
		TextView monthPeiziTv;
	}

}
