package com.skylark95.facebookweb;

import com.skylark95.facebookweb.activity.MainWebViewActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launchWebView();
        finish();
    }
    
    private void launchWebView() {
    	Intent intent = new Intent(this, MainWebViewActivity.class);
    	startActivity(intent);
    }
    
   
}
