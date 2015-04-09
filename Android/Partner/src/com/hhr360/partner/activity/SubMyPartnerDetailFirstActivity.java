package com.hhr360.partner.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.hhr360.partner.BaseActivity;
import com.hhr360.partner.PartnerApp;
import com.hhr360.partner.R;
import com.hhr360.partner.adapter.PartnerDetailAdapter;

public class SubMyPartnerDetailFirstActivity extends BaseActivity {

	private ListView mListView;
	private PartnerDetailAdapter mAdapter;
	private TextView mPartnersTV;
	private TextView mFirPartnerTV1;
	private TextView mSecPartnerTV1;
	private TextView mFirPartnerTV2;
	private TextView mSecPartnerTV2;
	private TextView mSecStockEndowTV;// 股票配资
	private TextView mSecStockCommTV;// 股票返佣
	private TextView mSecFutureEndowTV;// 期货配资
	private TextView mSecFutureCommTV;// 期货返佣

	private TextView mFirStockEndowTV;// 股票配资
	private TextView mFirStockCommTV;// 股票返佣
	private TextView mFirFutureEndowTV;// 期货配资
	private TextView mFirFutureCommTV;// 期货返佣

	private TextView mSecContriYouTV;// 次级合伙人对你贡献
	private TextView mFirContriYouTV;// 初级合伙人对你贡献

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sub_mypartner_detail_first);
		mListView = (ListView) findViewById(R.id.listview);
		View headView = LayoutInflater.from(this).inflate(
				R.layout.sub_mypartner_detail_first_head, null);
		mSecContriYouTV = (TextView) headView
				.findViewById(R.id.second_contribute_tv);
		mFirContriYouTV = (TextView) headView
				.findViewById(R.id.first_contribute_tv);

		mSecContriYouTV.setText(PartnerApp.PARTNER.getSecContrIncome() + "");
		mFirContriYouTV.setText(PartnerApp.PARTNER.getFirContrIncome() + "");

		mPartnersTV = (TextView) headView.findViewById(R.id.partner_num_tv);
		mSecPartnerTV1 = (TextView) headView
				.findViewById(R.id.second_partner_tv);
		mFirPartnerTV1 = (TextView) headView
				.findViewById(R.id.first_partner_tv);
		mSecPartnerTV2 = (TextView) headView
				.findViewById(R.id.second_people_num_tv);
		mFirPartnerTV2 = (TextView) headView
				.findViewById(R.id.first_people_num_tv);
		mSecStockEndowTV = (TextView) headView
				.findViewById(R.id.second_stock_peizi_tv);
		mSecStockCommTV = (TextView) headView
				.findViewById(R.id.second_stock_fanyong_tv);
		mSecFutureEndowTV = (TextView) headView
				.findViewById(R.id.second_future_peizi_tv);
		mSecFutureCommTV = (TextView) headView
				.findViewById(R.id.second_future_fanyong_tv);
		mFirStockEndowTV = (TextView) headView
				.findViewById(R.id.first_stock_peizi_tv);
		mFirStockCommTV = (TextView) headView
				.findViewById(R.id.first_stock_fanyong_tv);
		mFirFutureEndowTV = (TextView) headView
				.findViewById(R.id.first_future_peizi_tv);
		mFirFutureCommTV = (TextView) headView
				.findViewById(R.id.first_future_fanyong_tv);

		mPartnersTV.setText(Integer.parseInt(PartnerApp.USER
				.getFirstlyPartnerNum())
				+ Integer.parseInt(PartnerApp.USER.getSecondlyPartnerNum())
				+ "");
		mFirPartnerTV1.setText(PartnerApp.USER.getFirstlyPartnerNum() + "人");
		mSecPartnerTV1.setText(PartnerApp.USER.getSecondlyPartnerNum() + "人");
		mFirPartnerTV2.setText(PartnerApp.USER.getFirstlyPartnerNum());
		mSecPartnerTV2.setText(PartnerApp.USER.getSecondlyPartnerNum());

		mSecStockEndowTV.setText(PartnerApp.PARTNER.getSecStockEndow() + "");
		mSecStockCommTV.setText(PartnerApp.PARTNER.getSecStockComm() + "");
		mSecFutureEndowTV.setText(PartnerApp.PARTNER.getSecFutEndow() + "");
		mSecFutureCommTV.setText(PartnerApp.PARTNER.getSecFutComm() + "");
		mFirStockEndowTV.setText(PartnerApp.PARTNER.getFirStockEndow() + "");
		mFirStockCommTV.setText(PartnerApp.PARTNER.getFirStockComm() + "");
		mFirFutureEndowTV.setText(PartnerApp.PARTNER.getFirFutEndow() + "");
		mFirFutureCommTV.setText(PartnerApp.PARTNER.getFirFutComm() + "");
		mListView.addHeaderView(headView);
		mAdapter = new PartnerDetailAdapter(this,
				PartnerApp.PARTNER.mPartnerList);
		mListView.setAdapter(mAdapter);
	}
}
