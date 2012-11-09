package com.skylark95.facebookweb;

import com.skylark95.facebookweb.activity.MainWebViewActivity;
import com.skylark95.facebookweb.activity.PickWebViewActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectActivity();
        finish();
    }
    
    private void selectActivity() {
 
	}

	private void launchWebView() {
    	Intent intent = new Intent(this, MainWebViewActivity.class);
    	startActivity(intent);
    }
    
    private void launchSocialPicker() {
    	Intent intent = new Intent(this, PickWebViewActivity.class);
    	startActivity(intent);
    }
    
   
}
