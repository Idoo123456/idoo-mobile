package com.example.fishbook

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val webView = findViewById<WebView>(R.id.splashWebView)
        webView.settings.javaScriptEnabled = true
        
        // Memuat HTML animasi langsung ke dalam WebView (Branding FishBook)
        val htmlData = """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="utf-8"/>
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <style>
                body { margin: 0; padding: 0; background: #673AB7; overflow: hidden; height: 100vh; display: flex; align-items: center; justify-content: center; font-family: sans-serif; }
                .container { text-align: center; color: white; }
                .logo-box { width: 120px; height: 120px; background: rgba(255,255,255,0.15); border-radius: 30px; display: flex; align-items: center; justify-content: center; margin: 0 auto 24px; border: 2px solid rgba(255,255,255,0.3); animation: pulse 2.5s infinite ease-in-out; }
                .icon { font-size: 60px; }
                .title { font-size: 32px; font-weight: 900; margin: 0; letter-spacing: -1px; text-transform: uppercase; }
                .title span { color: #D1C4E9; }
                .loader { width: 40px; height: 40px; border: 3px solid rgba(255,255,255,0.3); border-radius: 50%; border-top-color: #fff; margin: 30px auto; animation: spin 1s ease-in-out infinite; }
                @keyframes spin { to { transform: rotate(360deg); } }
                @keyframes pulse { 0%, 100% { transform: scale(1); } 50% { transform: scale(1.05); } }
                .footer { position: absolute; bottom: 40px; width: 100%; text-align: center; color: rgba(255,255,255,0.6); font-size: 11px; letter-spacing: 2px; }
            </style>
        </head>
        <body>
            <div class="container">
                <div class="logo-box">
                    <div class="icon">🐟</div>
                </div>
                <h1 class="title">FISH<span>BOOK</span></h1>
                <div class="loader"></div>
            </div>
            <div class="footer">YOUR FISHING COMPANION</div>
        </body>
        </html>
        """.trimIndent()

        webView.loadDataWithBaseURL(null, htmlData, "text/html", "UTF-8", null)

        // Pindah ke AuthActivity setelah 3.5 detik
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }, 3500)
    }
}
