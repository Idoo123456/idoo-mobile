package com.example.fishbook

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.fishbook.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        val webView: WebView = binding.authWebView
        val webSettings: WebSettings = webView.settings
        
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        
        webView.webViewClient = WebViewClient()
        
        // Menambahkan interface untuk komunikasi JS ke Native
        webView.addJavascriptInterface(WebAppInterface(), "Android")
        
        webView.loadUrl("file:///android_asset/auth.html")
    }

    inner class WebAppInterface {
        @JavascriptInterface
        fun login(email: String, pass: String) {
            runOnUiThread {
                handleLogin(email, pass)
            }
        }
    }

    private fun handleLogin(email: String, pass: String) {
        val username = email.trim()
        val password = pass.trim()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }

        // Logic login: username harus sama dengan password (sesuai kode sebelumnya)
        // Atau jika menggunakan email, kita ambil bagian sebelum @ sebagai username
        val nameToDisplay = if (username.contains("@")) username.split("@")[0] else username

        if (username == password) {
            Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@AuthActivity, MainActivity::class.java)
            intent.putExtra("USER_NAME", nameToDisplay)
            startActivity(intent)
            finish()
        } else {
            AlertDialog.Builder(this)
                .setTitle("Gagal Masuk")
                .setMessage("Silahkan coba lagi.\n(Pastikan Email dan Password SAMA untuk demo ini)")
                .setPositiveButton("Coba Lagi", null)
                .show()
        }
    }
}
