package com.example.fishbook

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

        // Load Profile Image into Toolbar
        Glide.with(this)
            .load("https://lh3.googleusercontent.com/aida-public/AB6AXuDh449HqjEa2ibEXy67ttyo4Jir4V7G7YesY2-4XV7at9kiTLZDt0d2apmJfsQuEB4mDQYzyppXYKN4DWbHxj0FQ_si_AWvocI9tOE13F47D-lXIQIVG50LEHBTRxUReXKARL_3H9hxxwrLXdXeSscIQknDY61DEcM4GePoOjHiMDUpSRn-mA1wCB9eR5r5312bINhoid2FoUDuYiE41N60mGLvHQ3mw2EQ2I1AeiHqiTyyBXRl0vMHY5LCEQ8poJ0V4I7UDtxhHPE")
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
            replaceFragment(MoreFragment(), "MORE")
        }

        binding.navProfile.setOnClickListener {
            showLogoutDialog()
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            binding.appBarLayout.setPadding(0, systemBars.top, 0, 0)
            insets
        }
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
        updateNavState(tag)
    }

    private fun updateNavState(activeTag: String) {
        val orange = ContextCompat.getColor(this, R.color.unipin_orange)
        val slate = ContextCompat.getColor(this, R.color.unipin_text_sub)

        // Reset all
        binding.ivNavHome.imageTintList = ColorStateList.valueOf(slate)
        binding.tvNavHome.setTextColor(slate)
        binding.ivNavStore.imageTintList = ColorStateList.valueOf(slate)
        binding.tvNavStore.setTextColor(slate)
        binding.ivNavProfile.imageTintList = ColorStateList.valueOf(slate)
        binding.tvNavProfile.setTextColor(slate)

        // Set Active
        when (activeTag) {
            "HOME" -> {
                binding.ivNavHome.imageTintList = ColorStateList.valueOf(orange)
                binding.tvNavHome.setTextColor(orange)
            }
            "MORE" -> {
                binding.ivNavStore.imageTintList = ColorStateList.valueOf(orange)
                binding.tvNavStore.setTextColor(orange)
            }
        }
    }

    private fun showLogoutDialog() {
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
