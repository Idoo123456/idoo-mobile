package com.example.fishbook.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fishbook.Home.pertemuan_10.TenthActivity
import com.example.fishbook.Home.pertemuan_11.EleventhActivity
import com.example.fishbook.Home.pertemuan_2.SecondActivity
import com.example.fishbook.Home.pertemuan_3.ThirdActivity
import com.example.fishbook.Home.pertemuan_4.FourthActivity
import com.example.fishbook.Home.pertemuan_5.FifthActivity
import com.example.fishbook.Home.pertemuan_7.SeventhActivity
import com.example.fishbook.Home.pertemuan_9.NinthActivity
import com.example.fishbook.Home.photo.PhotoAdapter
import com.example.fishbook.data.api.CatFactApiClient
import com.example.fishbook.data.api.PhotoApiClient
import com.example.fishbook.databinding.FragmentHomeMainBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Clicks
        binding.cardPertemuan2.setOnClickListener {
            startActivity(Intent(requireContext(), SecondActivity::class.java))
        }
        binding.cardPertemuan3.setOnClickListener {
            startActivity(Intent(requireContext(), ThirdActivity::class.java))
        }
        binding.cardPertemuan4.setOnClickListener {
            startActivity(Intent(requireContext(), FourthActivity::class.java))
        }
        binding.cardPertemuan5.setOnClickListener {
            startActivity(Intent(requireContext(), FifthActivity::class.java))
        }
        binding.cardPertemuan7.setOnClickListener {
            startActivity(Intent(requireContext(), SeventhActivity::class.java))
        }
        binding.cardPertemuan9.setOnClickListener {
            startActivity(Intent(requireContext(), NinthActivity::class.java))
        }
        binding.cardPertemuan10.setOnClickListener {
            startActivity(Intent(requireContext(), TenthActivity::class.java))
        }
        binding.cardPertemuan11.setOnClickListener {
            startActivity(Intent(requireContext(), EleventhActivity::class.java))
        }

        // Cat Fact Section
        loadCatFact()
        binding.btnRefresh.setOnClickListener {
            loadCatFact()
        }

        // Photo Gallery Section
        loadPhoto()
    }

    private fun loadCatFact() {
        lifecycleScope.launch {
            try {
                binding.tvCatFact.text = "Loading cat fact..."
                val response = CatFactApiClient.apiService.getCatFact()
                binding.tvCatFact.text = "\"${response.fact}\""
            } catch (e: Exception) {
                binding.tvCatFact.text = "Gagal mengambil fakta kucing."
            }
        }
    }

    private fun loadPhoto() {
        lifecycleScope.launch {
            try {
                val photos = PhotoApiClient.apiService.getPhotos()
                val adapter = PhotoAdapter(photos)
                binding.rvGallery.adapter = adapter
                binding.rvGallery.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Gagal memuat gambar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
