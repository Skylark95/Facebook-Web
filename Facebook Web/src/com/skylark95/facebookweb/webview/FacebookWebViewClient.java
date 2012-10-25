package com.skylark95.facebookweb.webview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.skylark95.facebookweb.R;

public class FacebookWebViewClient extends WebViewClient {
	
	private Activity activity;
	private ProgressDialog progressDialog;
	
	public FacebookWebViewClient(Activity activity) {
		this.activity = activity;
		progressDialog = new ProgressDialog(activity);
		progressDialog.setMessage(activity.getString(R.string.progress_dialog_loading));
		progressDialog.setCancelable(false);
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
