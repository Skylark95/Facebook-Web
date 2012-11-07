package com.skylark95.facebookweb.webview;

import android.app.Activity;
import android.content.SharedPreferences;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.skylark95.facebookweb.activity.SettingsActivity;

public class WebViewFactory {
	
	private WebViewFactory() {		
	}
	
	public static void build(Activity activity, SharedPreferences preferences, WebView webView) {
		applySettings(preferences, webView);
		webView.setWebViewClient(new FacebookWebViewClient(activity, webView));
		webView.setWebChromeClient(new FacebookWebChromeClient(activity));
	}
	
	public static void applySettings(SharedPreferences preferences, WebView webView) {
		boolean shareLocation = preferences.getBoolean(SettingsActivity.KEY_PREF_LOCATION, true);
		
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setGeolocationEnabled(shareLocation);
	}
}
