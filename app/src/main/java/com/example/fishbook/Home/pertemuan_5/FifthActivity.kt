package com.example.fishbook.Home.pertemuan_5

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.bumptech.glide.Glide
import com.example.fishbook.R
import com.example.fishbook.databinding.ActivityFifthBinding
import com.google.android.material.snackbar.Snackbar

class FifthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFifthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFifthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Toolbar & Setup
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "FishBook Discovery"
        
        // 2. Load BG Image
        val portalBgUrl = "https://images.unsplash.com/photo-1524704654690-b56c05c78a00?q=80&w=2069&auto=format&fit=crop"
        Glide.with(this)
            .load(portalBgUrl)
            .centerCrop()
            .into(binding.collapsingImage)
            
        // 3. WebView Setup
        setupWebView()

        // 4. Scroll Logic
        setupScrollLogic()
    }

    private fun setupWebView() {
        binding.webview.apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.webProgress.visibility = View.GONE
                }
                
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    url?.let { view?.loadUrl(it) }
                    return true
                }
            }
            
            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    if (newProgress < 100) {
                        binding.webProgress.visibility = View.VISIBLE
                        binding.webProgress.progress = newProgress
                    } else {
                        binding.webProgress.visibility = View.GONE
                    }
                }
            }
            loadUrl("https://en.wikipedia.org/wiki/Fish")
        }
    }

    private fun setupScrollLogic() {
        binding.nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > 600) {
                if (scrollY > oldScrollY) {
                    binding.fabScrollTop.hide()
                } else {
                    binding.fabScrollTop.show()
                }
            } else {
                binding.fabScrollTop.hide()
            }
        })

        binding.fabScrollTop.setOnClickListener {
            binding.nestedScrollView.smoothScrollTo(0, 0)
            binding.appBar.setExpanded(true, true)
            Snackbar.make(binding.root, "Page Refreshed", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_fifth, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            R.id.action_settings -> {
                showToast("Settings")
                true
            }
            R.id.action_about -> {
                showToast("FishBook v1.0")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
