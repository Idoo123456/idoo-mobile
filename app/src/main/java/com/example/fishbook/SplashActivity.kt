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
        
        // Memuat HTML animasi langsung ke dalam WebView
        val htmlData = """
        <!DOCTYPE html>
        <html class="dark" lang="en"><head>
        <meta charset="utf-8"/>
        <meta content="width=device-width, initial-scale=1.0, viewport-fit=cover" name="viewport"/>
        <style>
            body { margin: 0; padding: 0; background: #121414; overflow: hidden; height: 100vh; display: flex; align-items: center; justify-content: center; font-family: 'Space Grotesk', sans-serif; }
            .container { text-align: center; color: white; }
            .logo-box { width: 100px; height: 100px; background: #1F1F1F; border-radius: 24px; display: flex; align-items: center; justify-content: center; margin: 0 auto 24px; border: 1px solid rgba(255, 150, 45, 0.2); box-shadow: 0 0 40px rgba(255, 150, 45, 0.1); animation: pulse 2s infinite; }
            .icon { font-size: 50px; color: #FF962D; }
            .title { font-size: 36px; font-weight: 800; margin: 0; letter-spacing: -1px; }
            .title span { color: #FF962D; }
            .loading-bar { width: 200px; height: 4px; background: rgba(255,255,255,0.1); border-radius: 2px; margin: 40px auto 10px; overflow: hidden; position: relative; }
            .progress { width: 60px; height: 100%; background: #FF962D; border-radius: 2px; position: absolute; animation: move 1.5s infinite ease-in-out; }
            .status { font-size: 10px; color: #555; letter-spacing: 2px; font-weight: bold; }
            .footer { position: absolute; bottom: 30px; width: 100%; text-align: center; color: rgba(255,255,255,0.4); font-size: 9px; letter-spacing: 1px; }
            @keyframes move { 0% { left: -60px; } 100% { left: 200px; } }
            @keyframes pulse { 0% { transform: scale(1); opacity: 0.8; } 50% { transform: scale(1.05); opacity: 1; } 100% { transform: scale(1); opacity: 0.8; } }
        </style>
        </head>
        <body>
            <div class="container">
                <div class="logo-box">
                    <div class="icon">🎮</div>
                </div>
                <h1 class="title"><span>Uni</span>Pin</h1>
                <div class="loading-bar">
                    <div class="progress"></div>
                </div>
                <div class="status">INITIALIZING SYSTEM</div>
            </div>
            <div class="footer">LEADING DIGITAL CONTENT ENABLER</div>
        </body></html>
        """.trimIndent()

        webView.loadDataWithBaseURL(null, htmlData, "text/html", "UTF-8", null)

        // Pindah ke AuthActivity setelah 4 detik agar animasi terlihat
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000)
    }
}