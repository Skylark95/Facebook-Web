package com.skylark95.facebookweb.webview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class FacebookWebChromeClient extends WebChromeClient {
	
	private Activity activity;

	public FacebookWebChromeClient(Activity activity) {
		this.activity = activity;
	}
	
	@Override
	public void onProgressChanged(WebView view, int progress) {
		activity.setProgress(progress * 100);
	}

}
