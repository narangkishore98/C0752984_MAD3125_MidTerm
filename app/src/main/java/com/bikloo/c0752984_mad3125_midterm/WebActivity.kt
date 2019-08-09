package com.bikloo.c0752984_mad3125_midterm

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        /*webView.settings.javaScriptEnabled = true
        webView.settings.setAppCacheEnabled(true)
        webView.settings.cacheMode = WebSettings.LOAD_DEFAULT
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (Uri.parse(url).host == intent.getStringExtra("weblink")) {
                    return false
                }
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url)
            }

        }


        */
        webView.webViewClient = MyWebClient()
        webView.settings.javaScriptEnabled = true
        webView.settings.loadsImagesAutomatically = true
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY


        webView.loadUrl(intent.getStringExtra("weblink").substring(15).trim())
        Toast.makeText(this@WebActivity, "Opening ${intent.getStringExtra("weblink").substring(15)}", Toast.LENGTH_LONG).show()



    }
    class MyWebClient:WebViewClient()
    {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {

            view!!.loadUrl(request!!.url.toString().trim())
            return  true
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view!!.loadUrl(url.trim())
            return true
        }
    }
}


