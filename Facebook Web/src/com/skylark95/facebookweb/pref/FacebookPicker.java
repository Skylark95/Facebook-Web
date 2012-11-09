package com.skylark95.facebookweb.pref;

import com.skylark95.facebookweb.activity.PickWebViewActivity;

import android.content.Context;
import android.content.Intent;
import android.preference.Preference;

public class FacebookPicker extends Preference {
	
	private Context context;

	public FacebookPicker(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	protected void onClick() {
		persistString(SocialPicks.FACEBOOK.toString());
		launchWebView();
	}
	
	private void launchWebView() {
		Intent intent = new Intent(context, PickWebViewActivity.class);
		context.startActivity(intent);
    }
	
	


}
