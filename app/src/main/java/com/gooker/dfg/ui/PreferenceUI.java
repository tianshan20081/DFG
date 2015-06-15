package com.gooker.dfg.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import cn.jpush.android.api.JPushInterface;

import com.aoeng.degu.R;

public class PreferenceUI extends PreferenceActivity implements OnPreferenceChangeListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		getPreferenceManager().setSharedPreferencesName("setting");

		addPreferencesFromResource(R.xml.preference_setting);

		Preference inDividualName = findPreference("individual_name");
		Preference mobilePreference = findPreference("mobile");
		SharedPreferences preferences = inDividualName.getSharedPreferences();
		inDividualName.setSummary(preferences.getString("individual_name", ""));

		inDividualName.setEnabled(preferences.getBoolean("yesno_save_individual_info", false));

		inDividualName.setOnPreferenceChangeListener(this);
		mobilePreference.setOnPreferenceChangeListener(this);
	}

	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
		// TODO Auto-generated method stub
		if ("yesno_save_individual_info".equals(preference.getKey())) {
			findPreference("individual_name").setEnabled(!findPreference("individual_name").isEnabled());
		}

		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		// TODO Auto-generated method stub

		if ("individual_name".equals(preference.getKey())) {
			preference.setSummary(String.valueOf(newValue));
		} else if ("mobile".equals(preference.getKey())) {
			preference.setSummary(String.valueOf(newValue));
		}
		return true;
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		JPushInterface.onResume(this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		JPushInterface.onPause(this);
	}
}
