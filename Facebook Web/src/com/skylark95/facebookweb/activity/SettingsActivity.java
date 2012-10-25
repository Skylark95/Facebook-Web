package com.skylark95.facebookweb.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.skylark95.facebookweb.R;

public class SettingsActivity extends PreferenceActivity {
	
	public static final String KEY_PREF_LOCATION = "pref_location";
	public static final String KEY_PREF_HIDE_ACTION_BAR = "pref_hide_action_bar";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
