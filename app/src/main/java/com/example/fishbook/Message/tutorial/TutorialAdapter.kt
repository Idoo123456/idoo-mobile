package com.example.fishbook.Message.tutorial

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TutorialAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Tutorial1Fragment()
            1 -> Tutorial2Fragment()
            2 -> Tutorial3Fragment()
            else -> Tutorial1Fragment()
        }
    }
}