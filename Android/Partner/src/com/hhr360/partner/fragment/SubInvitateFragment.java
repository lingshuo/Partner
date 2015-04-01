package com.hhr360.partner.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.hhr360.partner.R;

public class SubInvitateFragment extends SubBaseFragment implements
		TextWatcher, OnClickListener {

	private static SubInvitateFragment mSubInvitateFragment = null;

	private SubInvitateFragment() {

	}

	public static Fragment getInstance() {

		if (mSubInvitateFragment == null) {
			mSubInvitateFragment = new SubInvitateFragment();
		}
		return mSubInvitateFragment;
	}

	private View mRootView;
	private EditText mInviteEditText;
	private Button mSetBtn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.sub_fragment_invite, container,
				false);
		mInviteEditText = (EditText) mRootView
				.findViewById(R.id.invite_edittext);
		mInviteEditText.addTextChangedListener(this);
		mSetBtn = (Button) mRootView.findViewById(R.id.invite_btn);
		mSetBtn.setOnClickListener(this);
		return mRootView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 点击设置邀请码
		case R.id.invite_btn:
			break;

		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (!TextUtils.isEmpty(mInviteEditText.getText())
				&& mInviteEditText.getText().length() >= 4) {
			mSetBtn.setEnabled(true);
		} else {
			mSetBtn.setEnabled(false);
		}
	}

	@Override
	public void afterTextChanged(Editable s) {

	}
}
