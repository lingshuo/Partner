package com.hhr360.partner.fragment;

import com.hhr360.partner.activity.PartnerManagerActivity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

public class SubBaseFragment extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";

	public static Fragment newInstance(int sectionNumber) {
		Fragment fragment = null;
		switch (sectionNumber) {
		case 0:
			fragment = SubMineFragment.getInstance();
			break;
		case 1:
			fragment = SubPartnerFragment.getInstance();
			break;
		case 2:
			fragment = SubInvitateFragment.getInstance();
			break;
		}
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		if (fragment.getArguments() == null) {
			fragment.setArguments(args);
		} else {
			fragment.getArguments().putAll(args);
		}
		return fragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((PartnerManagerActivity) activity).onSectionAttached(getArguments()
				.getInt(ARG_SECTION_NUMBER));
	}
}
