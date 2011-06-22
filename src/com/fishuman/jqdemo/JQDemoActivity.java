package com.fishuman.jqdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class JQDemoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        
        WebView webView = (WebView) findViewById(R.id.mainWebView);
        WebSettings webSettings = webView.getSettings();  
        webSettings.setJavaScriptEnabled(true); 
        webSettings.setPluginsEnabled(true);
        webView.addJavascriptInterface(new DemoInterface(this), "demo");
        
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebChromeClient(new WebChromeClient() {  
            @Override  
            public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result)  
            {  
            	new AlertDialog.Builder(JQDemoActivity.this)  
                    .setTitle(R.string.app_name)  
                    .setMessage(message)  
                    .setPositiveButton(android.R.string.ok,  
                            new AlertDialog.OnClickListener()  
                            {  
                                public void onClick(DialogInterface dialog, int which)  
                                {  
                                    result.confirm();  
                                }  
                            })  
                    .setCancelable(false)  
                    .create()  
                    .show();  
          
                return true;  
            };  
        });  
        
        
        webView.loadUrl("file:///android_asset/cindex.html"); 
        //webView.loadUrl("http://m.youtube.com/");
        
        /*setContentView(R.layout.main2);
        ListView listView = (ListView)findViewById(R.id.mainlist);
        listView.setAdapter(new MyAdapter());*/
    }
    
    class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 20;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			
			View myView;
			if(arg0 == 0) {
				myView = View.inflate(JQDemoActivity.this, R.layout.main, null);
				
				WebView webView = (WebView) myView.findViewById(R.id.mainWebView);
		        WebSettings webSettings = webView.getSettings();  
		        webSettings.setJavaScriptEnabled(true); 
		        webSettings.setPluginsEnabled(true);
		        
		        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		        webView.setWebChromeClient(new WebChromeClient() {  
		            @Override  
		            public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result)  
		            {  
		            	new AlertDialog.Builder(JQDemoActivity.this)  
		                    .setTitle(R.string.app_name)  
		                    .setMessage(message)  
		                    .setPositiveButton(android.R.string.ok,  
		                            new AlertDialog.OnClickListener()  
		                            {  
		                                public void onClick(DialogInterface dialog, int which)  
		                                {  
		                                    result.confirm();  
		                                }  
		                            })  
		                    .setCancelable(false)  
		                    .create()  
		                    .show();  
		          
		                return true;  
		            };  
		        });  
		        
		        
		        
		        webView.loadUrl("file:///android_asset/x.html"); 
			} else {
				myView = new TextView(JQDemoActivity.this);
				((TextView)myView).setText("item " + arg0);
			}
			
			return myView;
		}
    
    }
}