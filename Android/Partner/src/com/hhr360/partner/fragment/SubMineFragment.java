package com.hhr360.partner.fragment;

import android.app.Fragment;

public class SubMineFragment extends SubBaseFragment {

	private static SubMineFragment mSubmineFragment = null;

	private SubMineFragment() {

	}

	public static Fragment getInstance() {

		if (mSubmineFragment == null) {
			mSubmineFragment = new SubMineFragment();
		}
		return mSubmineFragment;
	}
}
