package com.example.fishbook.Home.pertemuan_7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fishbook.databinding.ActivitySeventhBinding
import com.google.android.material.tabs.TabLayoutMediator

class SeventhActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeventhBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeventhBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        setupViewPager()
    }

    private fun setupViewPager() {
        val adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 3

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> GameFragment()
                    1 -> PlaceholderFragment.newInstance("History Transaksi")
                    else -> PlaceholderFragment.newInstance("Profil Master")
                }
            }
        }

        binding.viewPager.adapter = adapter

        // Hubungkan TabLayout dengan ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Store"
                1 -> "History"
                else -> "Profile"
            }
        }.attach()
    }
}
