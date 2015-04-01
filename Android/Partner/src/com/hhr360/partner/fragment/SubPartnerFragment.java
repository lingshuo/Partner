package com.hhr360.partner.fragment;

import android.app.Fragment;

public class SubPartnerFragment extends SubBaseFragment {
	private static SubPartnerFragment mSubPartnerFragment = null;

	private SubPartnerFragment() {

	}

	public static Fragment getInstance() {

		if (mSubPartnerFragment == null) {
			mSubPartnerFragment = new SubPartnerFragment();
		}
		return mSubPartnerFragment;
	}
}
