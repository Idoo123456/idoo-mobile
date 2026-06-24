package com.example.fishbook

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val webView = findViewById<WebView>(R.id.splashWebView)
        webView.settings.javaScriptEnabled = true
        
        val htmlData = """
        <!DOCTYPE html>
        <html lang="en"><head>
        <meta charset="utf-8"/>
        <meta content="width=device-width, initial-scale=1.0, viewport-fit=cover" name="viewport"/>
        <style>
            body { margin: 0; padding: 0; background: #FFFFFF; overflow: hidden; height: 100vh; display: flex; align-items: center; justify-content: center; font-family: 'sans-serif', sans-serif; }
            .container { text-align: center; color: #673AB7; }
            .logo-box { width: 100px; height: 100px; background: #F5F5F5; border-radius: 50%; display: flex; align-items: center; justify-content: center; margin: 0 auto 24px; border: 2px solid #673AB7; box-shadow: 0 4px 15px rgba(103, 58, 183, 0.2); animation: pulse 2s infinite; }
            .icon { font-size: 50px; color: #673AB7; }
            .title { font-size: 36px; font-weight: 800; margin: 0; letter-spacing: -1px; color: #000000; }
            .title span { color: #673AB7; }
            .loading-bar { width: 200px; height: 4px; background: #E0E0E0; border-radius: 2px; margin: 40px auto 10px; overflow: hidden; position: relative; }
            .progress { width: 60px; height: 100%; background: #673AB7; border-radius: 2px; position: absolute; animation: move 1.5s infinite ease-in-out; }
            .status { font-size: 10px; color: #757575; letter-spacing: 2px; font-weight: bold; }
            .footer { position: absolute; bottom: 30px; width: 100%; text-align: center; color: #BDBDBD; font-size: 9px; letter-spacing: 1px; }
            @keyframes move { 0% { left: -60px; } 100% { left: 200px; } }
            @keyframes pulse { 0% { transform: scale(1); opacity: 0.8; } 50% { transform: scale(1.05); opacity: 1; } 100% { transform: scale(1); opacity: 0.8; } }
        </style>
        </head>
        <body>
            <div class="container">
                <div class="logo-box">
                    <div class="icon">🐟</div>
                </div>
                <h1 class="title">Fish<span>Book</span></h1>
                <div class="loading-bar">
                    <div class="progress"></div>
                </div>
                <div class="status">INITIALIZING...</div>
            </div>
            <div class="footer">YOUR FISHING COMPANION</div>
        </body></html>
        """.trimIndent()

        webView.loadDataWithBaseURL(null, htmlData, "text/html", "UTF-8", null)

        Handler(Looper.getMainLooper()).postDelayed({
            checkLoginStatus()
        }, 3000)
    }

    private fun checkLoginStatus() {
        val sharedPref = getSharedPreferences("FishBookPref", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)
        val userName = sharedPref.getString("userName", "")

        if (isLoggedIn) {
            // Jika sudah login, langsung ke MainActivity
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("USER_NAME", userName)
            startActivity(intent)
        } else {
            // Jika belum login, ke AuthActivity
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
        finish()
    }
}
