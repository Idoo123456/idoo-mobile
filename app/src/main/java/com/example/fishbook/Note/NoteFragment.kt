package com.example.fishbook.Note

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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fishbook.data.AppDatabase
import com.example.fishbook.databinding.FragmentNoteBinding
import kotlinx.coroutines.launch

class NoteFragment : Fragment() {
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase
    private lateinit var adapter: NoteAdapter

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
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.getInstance(requireContext())

        setupRecyclerView()

        binding.fabAddNote.setOnClickListener {
            startActivity(Intent(requireContext(), NoteFormActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        adapter = NoteAdapter(emptyList()) { note ->
            deleteNote(note)
        }
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@NoteFragment.adapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
    }

    private fun deleteNote(note: com.example.fishbook.data.entity.NoteEntity) {
        lifecycleScope.launch {
            db.noteDao().delete(note)
            fetchNotes()
        }
    }

    private fun fetchNotes() {
        lifecycleScope.launch {
            val notes = db.noteDao().getAll()
            adapter.updateData(notes)
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
        // generate alamat tempat penyimpanan dan nama foto
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

    override fun onResume() {
        super.onResume()
        fetchNotes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
