package com.hhr360.partner.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hhr360.partner.PartnerApp;
import com.hhr360.partner.R;
import com.hhr360.partner.bean.Partner;
import com.hhr360.partner.bean.User;

public class PartnerDetailAdapter extends BaseAdapter {
	public Context mContext;
	public ArrayList<User> mList;

	public PartnerDetailAdapter(Context context, ArrayList<User> list) {
		mContext = context;
		mList = list;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.listview_partner_item, null);
			holder.nameTv = (TextView) convertView.findViewById(R.id.name_tv);
			holder.monthPeiziTv = (TextView) convertView
					.findViewById(R.id.month_peizi_tv);
			holder.partnerNumTv = (TextView) convertView
					.findViewById(R.id.partner_num_tv);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		User user = mList.get(position);
		holder.nameTv.setText(user.getAccountName());
		holder.monthPeiziTv.setText("本月配资:" + user.getMonthlyIncome());
		holder.partnerNumTv.setText("合伙人数:"
				+ (user.getFirstlyPartnerNum() + user.getSecondlyPartnerNum()));
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
