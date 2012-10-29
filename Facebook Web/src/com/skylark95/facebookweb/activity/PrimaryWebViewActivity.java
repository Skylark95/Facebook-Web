package com.skylark95.facebookweb.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		showOrHideActionBar(preferences);
		applyWebViewSettings(preferences);
	}

	@TargetApi(11)
	private void showOrHideActionBar(SharedPreferences preferences) {
		boolean hideActionBar = preferences.getBoolean(SettingsActivity.KEY_PREF_HIDE_ACTION_BAR, false);
		ActionBar actionBar = getActionBar();
		
		if (hideActionBar && actionBar.isShowing()) {
			actionBar.hide();
			registerForContextMenu(webView);
		} else if (!hideActionBar && !actionBar.isShowing()) {
			actionBar.show();
			unregisterForContextMenu(webView);
		}
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.activity_primary_web_view, menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_primary_web_view, menu);
		return true;
	}
	

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		boolean retVal = onMenuItemSelected(item); 
		
		if (retVal) {
			return retVal;
		} else {
			return super.onContextItemSelected(item);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean retVal = onMenuItemSelected(item); 
		
		if (retVal) {
			return retVal;
		} else {
			return super.onOptionsItemSelected(item);
		}
		 
	}
	
	private boolean onMenuItemSelected(MenuItem item) {
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
				return false;
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
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		applyWebViewSettings(preferences);
		
		webView.setWebViewClient(new FacebookWebViewClient(this, webView));
		webView.setWebChromeClient(new FacebookWebChromeClient(this));
		
		webView.loadUrl(getString(R.string.facebook_home_url));
	}

	private void applyWebViewSettings(SharedPreferences preferences) {
		boolean shareLocation = preferences.getBoolean(SettingsActivity.KEY_PREF_LOCATION, true);
		
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setGeolocationEnabled(shareLocation);
	}

}
