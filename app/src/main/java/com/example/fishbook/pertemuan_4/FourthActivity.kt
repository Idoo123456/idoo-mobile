package com.example.fishbook.pertemuan_4

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.fishbook.databinding.ActivityFourthBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class FourthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFourthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Mengambil data dari Intent
        val name = intent.getStringExtra("name")
        val from = intent.getStringExtra("from")
        val age = intent.getIntExtra("age", 0)

        // 2. Tampilkan di UI Dashboard yang baru
        binding.tvName.text = name ?: "Guest"
        binding.tvDetails.text = "${from ?: "-"} | ${if (age != 0) age else "-"} Tahun"

        binding.btnShowSnackbar.setOnClickListener {
            Snackbar.make(binding.root, "Dashboard diperbarui!", Snackbar.LENGTH_SHORT).show()
        }

        binding.btnShowAlertDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin keluar dari Dashboard?")
                .setPositiveButton("Ya") { _, _ -> finish() }
                .setNegativeButton("Tidak", null)
                .show()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}