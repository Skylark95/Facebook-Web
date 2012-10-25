package com.skylark95.facebookweb.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.skylark95.facebookweb.R;
import com.skylark95.facebookweb.webview.FacebookWebChromeClient;
import com.skylark95.facebookweb.webview.FacebookWebViewClient;

public class PrimaryWebViewActivity extends Activity {
	
	private WebView webView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.activity_primary_web_view);
		webView = (WebView) findViewById(R.id.webview);
		loadWebView();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		applyWebViewSettings();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_primary_web_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_settings:
				showSettings();
				return true;
			case R.id.menu_refresh:
				webView.reload();
				return true;
			case R.id.menu_logout:
				showLogoutAlert();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	private void showLogoutAlert() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.alert_logout_title);
		builder.setMessage(R.string.alert_logout_message);
		
		builder.setPositiveButton(R.string.alert_yes_button, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				logout();				
			}
		});
		
		builder.setNegativeButton(R.string.alert_no_button, null);
		
		builder.show();
		
	}
	
	private void logout() {
		CookieManager.getInstance().removeAllCookie();
		webView.clearCache(true);
		webView.clearFormData();
		webView.clearHistory();
		loadWebView();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (webView.canGoBack()) {
				webView.goBack();
				return false;
			} else {
				finish();
			}			
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	
	private void showSettings() {
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);		
	}

	private void loadWebView() {
		applyWebViewSettings();
		
		webView.setWebViewClient(new FacebookWebViewClient(this));
		webView.setWebChromeClient(new FacebookWebChromeClient(this));
		
		webView.loadUrl(getString(R.string.facebook_home_url));
	}

	private void applyWebViewSettings() {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		boolean shareLocation = preferences.getBoolean(SettingsActivity.KEY_PREF_LOCATION, true);
		
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setGeolocationEnabled(shareLocation);
	}

}
