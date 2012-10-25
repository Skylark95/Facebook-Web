package com.skylark95.facebookweb.webview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.skylark95.facebookweb.R;

public class FacebookWebViewClient extends WebViewClient {
	
	private Activity activity;
	
	public FacebookWebViewClient(Activity activity) {
		this.activity = activity;
	}

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		if (Uri.parse(url).getHost().equals(activity.getString(R.string.facebook_host))) {
			return false;
		}

		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		activity.startActivity(intent);
		return true;
	}
	
}
