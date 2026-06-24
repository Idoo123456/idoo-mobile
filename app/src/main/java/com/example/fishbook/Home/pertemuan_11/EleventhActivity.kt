package com.example.fishbook.Home.pertemuan_11

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fishbook.data.api.ApiConfig
import com.example.fishbook.data.model.PhotoResponse
import com.example.fishbook.databinding.ActivityEleventhBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EleventhActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEleventhBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEleventhBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        setupRecyclerView()
        getPhotos()
    }

    private fun setupRecyclerView() {
        binding.rvPhotos.layoutManager = LinearLayoutManager(this)
        binding.rvPhotos.setHasFixedSize(true)
    }

    private fun getPhotos() {
        binding.progressBar.visibility = View.VISIBLE
        val client = ApiConfig.getPicsumApiService().getPhotos()
        client.enqueue(object : Callback<List<PhotoResponse>> {
            override fun onResponse(
                call: Call<List<PhotoResponse>>,
                response: Response<List<PhotoResponse>>
            ) {
                binding.progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setPhotoData(responseBody)
                    }
                } else {
                    Toast.makeText(this@EleventhActivity, "onFailure: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<PhotoResponse>>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this@EleventhActivity, "onFailure: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setPhotoData(photos: List<PhotoResponse>) {
        val adapter = PhotoAdapter(photos)
        binding.rvPhotos.adapter = adapter
    }
}
