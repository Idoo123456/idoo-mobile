package com.example.fishbook.pertemuan_5

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
        supportActionBar?.title = "UniPin Portal"
        
        // 2. Load BG Image (UniPin Theme)
        val portalBgUrl = "https://wallpaperaccess.com/full/2051333.jpg"
        Glide.with(this)
            .load(portalBgUrl)
            .centerCrop()
            .into(binding.collapsingImage)
            
        // 3. WebView Setup (Arahkan ke UniPin)
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
            loadUrl("https://www.unipin.com/")
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
                showToast("UniPin Portal v1.0")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}