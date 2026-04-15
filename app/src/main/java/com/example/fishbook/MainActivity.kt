package com.example.fishbook

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.fishbook.pertemuan_4.FourthActivity
import com.example.fishbook.pertemuan_5.FifthActivity
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val cardFifth = findViewById<MaterialCardView>(R.id.cardFifth)
        val cardFourth = findViewById<MaterialCardView>(R.id.cardFourth)
        val bannerImage = findViewById<ImageView>(R.id.bannerImage)

        // Load Premium Image for Banner using Glide (Link Baru)
        val imageUrl = getString(R.string.img_main_url)
        bannerImage?.let {
            Glide.with(this)
                .load(imageUrl)
                .centerCrop()
                .into(it)
        }

        cardFifth?.setOnClickListener {
            val intent = Intent(this, FifthActivity::class.java)
            startActivity(intent)
        }

        cardFourth?.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        val mainView = findViewById<android.view.View>(android.R.id.content)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}