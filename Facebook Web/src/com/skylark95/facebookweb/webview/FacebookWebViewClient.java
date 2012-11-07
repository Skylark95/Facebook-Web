package com.skylark95.facebookweb.webview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.skylark95.facebookweb.R;

class FacebookWebViewClient extends WebViewClient {
	
	private Activity activity;
	private ProgressDialog progressDialog;
	
	FacebookWebViewClient(Activity activity, WebView webView) {
		this.activity = activity;
		createProgressDialog(activity, webView);
	}

	private void createProgressDialog(final Activity activity, final WebView webView) {
		progressDialog = new ProgressDialog(activity) {
			@Override
			public void onBackPressed() {
				Toast.makeText(activity, activity.getString(R.string.toast_stopping), Toast.LENGTH_SHORT).show();
				webView.stopLoading();
				super.onBackPressed();
			}
		};
		
		progressDialog.setMessage(activity.getString(R.string.progress_dialog_loading));
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

	@Override
	public void onPageFinished(WebView view, String url) {
		progressDialog.dismiss();
		super.onPageFinished(view, url);
	}

	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		progressDialog.show();
		super.onPageStarted(view, url, favicon);
	}
	
	
	
	
	
}
