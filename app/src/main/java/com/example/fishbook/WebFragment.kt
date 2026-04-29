package com.example.fishbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.fishbook.databinding.FragmentWebBinding

class WebFragment : Fragment() {

    private var _binding: FragmentWebBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = "https://www.unipin.com/?utm_source=GSN&utm_medium=Paid%20Ads&utm_campaign=ID%20-%20General%20UniPin%20%7C%20GSN%20Lifetime%20(Arifin)&gad_source=1&gad_campaignid=20916923230&gbraid=0AAAAACf0nBdixdeVmjemLKsSuky94ODrL&gclid=Cj0KCQjw2MbPBhCSARIsAP3jP9x2C3RY6qLC_T1H1Brd1Jv2dnp2ZAw8MhvA8JGYfyse1vO7muFLRkYaAtGwEALw_wcB"

        binding.webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                    binding.progressBar.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    binding.progressBar.visibility = View.GONE
                }
            }
            loadUrl(url)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
