package com.hhr360.partner.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import com.hhr360.partner.BaseActivity;
import com.hhr360.partner.R;
import com.hhr360.partner.adapter.PartnerDetailAdapter;

public class SubMyPartnerDetailFirstActivity extends BaseActivity {

	private ListView mListView;
	private PartnerDetailAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sub_mypartner_detail_first);
		mListView = (ListView) findViewById(R.id.listview);
		mAdapter = new PartnerDetailAdapter(this);
		mListView.setAdapter(mAdapter);
		View headView = LayoutInflater.from(this).inflate(
				R.layout.sub_mypartner_detail_first_head, null);
		mListView.addHeaderView(headView);

	}
}
