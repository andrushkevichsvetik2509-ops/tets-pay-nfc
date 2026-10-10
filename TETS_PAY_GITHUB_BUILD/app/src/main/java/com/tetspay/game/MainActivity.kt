package com.tetspay.game

import android.app.Activity
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

class MainActivity : Activity(), NfcAdapter.ReaderCallback {
    private var web: WebView? = null
    private var adapter: NfcAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = NfcAdapter.getDefaultAdapter(this)
        web = WebView(this).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            webChromeClient = WebChromeClient()
            webViewClient = WebViewClient()
            addJavascriptInterface(Bridge(), "AndroidNfc")
            loadUrl("file:///android_asset/index.html")
        }
        setContentView(web)
        if (adapter == null) Toast.makeText(this, "На этом телефоне нет NFC", Toast.LENGTH_LONG).show()
    }
    override fun onResume() {
        super.onResume()
        adapter?.enableReaderMode(this, this, NfcAdapter.FLAG_READER_NFC_A or NfcAdapter.FLAG_READER_NFC_B or NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK, null)
    }
    override fun onPause() {
        adapter?.disableReaderMode(this)
        super.onPause()
    }
    override fun onTagDiscovered(tag: Tag) {
        runOnUiThread { web?.evaluateJavascript("window.onNfcDetected && window.onNfcDetected()", null) }
    }
    inner class Bridge {
        @JavascriptInterface fun startScan() { runOnUiThread { if (adapter == null) Toast.makeText(this@MainActivity, "NFC не поддерживается", Toast.LENGTH_SHORT).show() } }
        @JavascriptInterface fun setPayerMode(enabled: Boolean) { /* game UI handles role selection */ }
    }
}
