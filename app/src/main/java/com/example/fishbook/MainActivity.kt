package com.example.fishbook

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.fishbook.pertemuan_4.FourthActivity
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Inisialisasi View
        val ivPromoImage = findViewById<ImageView>(R.id.ivPromoImage)
        val btnToUniPin = findViewById<MaterialCardView>(R.id.btnToUniPin)
        val cardDashboard = findViewById<MaterialCardView>(R.id.cardDashboard)
        val btnShare = findViewById<MaterialCardView>(R.id.btnShare)
        val userActionArea = findViewById<View>(R.id.userActionArea)
        val tvUserGreeting = findViewById<TextView>(R.id.tvUserGreeting)

        // Ambil data nama dari Intent (AuthActivity)
        val userName = intent.getStringExtra("USER_NAME") ?: "Gamers"
        tvUserGreeting.text = "Halo, $userName!"

        // Fitur Logout via User Area (Pojok Kanan Atas)
        userActionArea?.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Akun Saya")
                .setMessage("Halo $userName, apakah Anda yakin ingin keluar?")
                .setPositiveButton("Logout") { _, _ ->
                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Batal", null)
                .show()
        }

        // Load Promo Image with Glide
        val promoUrl = "https://i.pinimg.com/1200x/a3/b2/9e/a3b29e8411d3ae14b253a3f3302da629.jpg"
        ivPromoImage?.let {
            Glide.with(this)
                .load(promoUrl)
                .centerCrop()
                .into(it)
        }

        // Klik Card UniPin
        btnToUniPin?.setOnClickListener {
            val url = "https://www.unipin.com/?irclickid=0192IVztLxyZRRJwoWywXULVUku3GtUXm3AmSo0&pid=6088583&irmpname=Bidkinetic%20Pte.%20Ltd.&irgwc=1&afsrc=1&gad_source=1&gad_campaignid=23656232296&gbraid=0AAAABBfjBw12IDq1C9hs-RhW_OntUVMLR&gclid=CjwKCAjw46HPBhAMEiwASZpLRGt8_BKq5z7cEzpJKixRXe3yLxiF2OTJsKtSEOqh_j5tzU5JIvNODRoCDFAQAvD_BwE"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        // Klik Card Dashboard untuk ke FourthActivity
        cardDashboard?.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        // Klik Card Share
        btnShare?.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Cek promo seru UniPin ini!")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}