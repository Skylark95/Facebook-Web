package com.skylark95.facebookweb.webview;

import android.app.Activity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

class FacebookWebChromeClient extends WebChromeClient {
	
	private Activity activity;

	FacebookWebChromeClient(Activity activity) {
		this.activity = activity;
	}
	
	@Override
	public void onProgressChanged(WebView view, int progress) {
		activity.setProgress(progress * 100);
	}

}
