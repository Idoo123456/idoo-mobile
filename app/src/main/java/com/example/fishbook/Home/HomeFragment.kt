package com.example.fishbook.Home

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fishbook.Home.pertemuan_10.TenthActivity
import com.example.fishbook.Home.pertemuan_11.EleventhActivity
import com.example.fishbook.Home.pertemuan_13.ThirteenthActivity
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

    private var currentPhotoUri: Uri? = null

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            Toast.makeText(requireContext(), "Gambar berhasil disimpan", Toast.LENGTH_SHORT).show()
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            openCamera()
        } else {
            Toast.makeText(requireContext(), "Izin kamera ditolak", Toast.LENGTH_SHORT).show()
        }
    }

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
        binding.cardPertemuan13.setOnClickListener {
            startActivity(Intent(requireContext(), ThirteenthActivity::class.java))
        }

        // Camera FAB
        binding.fabCamera.setOnClickListener {
            if (hasCameraPermission()) {
                openCamera()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
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
        // Use viewLifecycleOwner.lifecycleScope to automatically cancel when view is destroyed
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                if (_binding == null) return@launch
                binding.tvCatFact.text = "Loading cat fact..."
                val response = CatFactApiClient.apiService.getCatFact()
                if (_binding == null) return@launch
                binding.tvCatFact.text = "\"${response.fact}\""
            } catch (e: Exception) {
                if (_binding != null) {
                    binding.tvCatFact.text = "Gagal mengambil fakta kucing."
                }
            }
        }
    }

    private fun loadPhoto() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val photos = PhotoApiClient.apiService.getPhotos()
                if (_binding == null) return@launch
                val adapter = PhotoAdapter(photos)
                binding.rvGallery.adapter = adapter
                binding.rvGallery.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            } catch (e: Exception) {
                if (isAdded) {
                    Toast.makeText(requireContext(), "Gagal memuat gambar", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun hasCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        currentPhotoUri = createGalleryPhotoUri()
        intent.putExtra(MediaStore.EXTRA_OUTPUT, currentPhotoUri)
        cameraLauncher.launch(intent)
    }

    private fun createGalleryPhotoUri(): Uri {
        val folderName = "TestCaptures"
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "IMG_${System.currentTimeMillis()}")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/$folderName")
        }
        return requireContext().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            ?: throw RuntimeException("Gagal membuat URI MediaStore")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
