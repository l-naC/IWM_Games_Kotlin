package com.example.iwm_games_kotlin

import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.layout_activity_webview.*

class ActivityWebView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_webview)
        Log.d("TestView", "test")
        Log.d("TestWeb", intent.getStringExtra("link"))
        web.webViewClient = WebViewClient()
        web.loadUrl(intent.getStringExtra("link"))
    }
}
