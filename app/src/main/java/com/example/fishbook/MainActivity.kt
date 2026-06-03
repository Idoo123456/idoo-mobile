package com.example.fishbook

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.fishbook.Home.HomeFragment
import com.example.fishbook.Message.MessageFragment
import com.example.fishbook.More.MoreFragment
import com.example.fishbook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load Generic Profile Image
        Glide.with(this)
            .load("https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?q=80&w=500&auto=format&fit=crop")
            .circleCrop()
            .into(binding.ivProfile)

        // Initial Fragment
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment(), "HOME")
        }

        // Setup Navigation Clicks
        binding.navHome.setOnClickListener {
            replaceFragment(HomeFragment(), "HOME")
        }

        binding.navStore.setOnClickListener {
            replaceFragment(MessageFragment(), "MESSAGE")
        }

        binding.navMore.setOnClickListener {
            replaceFragment(MoreFragment(), "MORE")
        }

        // Handle Window Insets for Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            
            // Padding atas untuk AppBar
            binding.appBarLayout.setPadding(0, systemBars.top, 0, 0)
            
            // Margin bawah untuk Floating Nav agar tidak tertutup navigation bar sistem
            binding.bottomNavCard.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = systemBars.bottom + (24 * resources.displayMetrics.density).toInt()
            }

            insets
        }
    }

    fun setNavVisibility(visible: Boolean) {
        val visibility = if (visible) View.VISIBLE else View.GONE
        binding.appBarLayout.visibility = visibility
        binding.bottomNavCard.visibility = visibility
        
        // Adjust container constraints to fill screen when bars are hidden
        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.root as ConstraintLayout)
        if (visible) {
            constraintSet.connect(R.id.main_container, ConstraintSet.TOP, R.id.appBarLayout, ConstraintSet.BOTTOM)
        } else {
            constraintSet.connect(R.id.main_container, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        }
        constraintSet.applyTo(binding.root as ConstraintLayout)
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        // Ensure nav is visible when switching fragments
        setNavVisibility(true)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
        updateNavState(tag)
    }

    private fun updateNavState(activeTag: String) {
        val purple = ContextCompat.getColor(this, R.color.purple_primary)
        val grey = ContextCompat.getColor(this, R.color.text_grey)

        // Reset all
        binding.ivNavHome.imageTintList = ColorStateList.valueOf(grey)
        binding.tvNavHome.setTextColor(grey)
        binding.ivNavStore.imageTintList = ColorStateList.valueOf(grey)
        binding.tvNavStore.setTextColor(grey)
        binding.ivNavMore.imageTintList = ColorStateList.valueOf(grey)
        binding.tvNavMore.setTextColor(grey)

        // Set Active
        when (activeTag) {
            "HOME" -> {
                binding.ivNavHome.imageTintList = ColorStateList.valueOf(purple)
                binding.tvNavHome.setTextColor(purple)
            }
            "MESSAGE" -> {
                binding.ivNavStore.imageTintList = ColorStateList.valueOf(purple)
                binding.tvNavStore.setTextColor(purple)
            }
            "MORE" -> {
                binding.ivNavMore.imageTintList = ColorStateList.valueOf(purple)
                binding.tvNavMore.setTextColor(purple)
            }
        }
    }

    fun logout() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes") { _, _ ->
                val intent = Intent(this, AuthActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            .setNegativeButton("No", null)
            .show()
    }
}
