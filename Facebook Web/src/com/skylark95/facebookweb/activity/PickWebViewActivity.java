package com.skylark95.facebookweb.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.skylark95.facebookweb.R;

public class PickWebViewActivity extends PreferenceActivity {
	
	public static final String KEY_PREF_LOCATION = "pref_location";
	public static final String KEY_PREF_HIDE_ACTION_BAR = "pref_hide_action_bar";
	public static final String KEY_PREF_TWITTER = "pref_twitter";
	public static final String KEY_PREF_TWITTER_SWIPE = "pref_twitter_swipe";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        addPreferencesFromResource(R.xml.preferences);
    }

}
