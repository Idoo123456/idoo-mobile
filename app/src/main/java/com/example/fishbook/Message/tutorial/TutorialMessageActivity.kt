package com.example.fishbook.Message.tutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fishbook.databinding.ActivityTutorialMessageBinding

class TutorialMessageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragments = listOf(
            Tutorial1Fragment(),
            Tutorial2Fragment(),
            Tutorial3Fragment()
        )

        val adapter = TutorialFragmentAdapter(this, fragments)
        binding.tutorialMessageViewPager.adapter = adapter

        // Hubungkan DotsIndicator dengan ViewPager2
        binding.dotIndicator.attachTo(binding.tutorialMessageViewPager)
    }
}
