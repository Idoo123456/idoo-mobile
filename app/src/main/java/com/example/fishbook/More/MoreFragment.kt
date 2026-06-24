package com.example.fishbook.More

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fishbook.AuthActivity
import com.example.fishbook.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {

    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    private val dataListWithDesc = listOf(
        mapOf("title" to "Kotlin", "desc" to "Bahasa untuk Android modern"),
        mapOf("title" to "Java", "desc" to "Bahasa OOP yang populer"),
        mapOf("title" to "Python", "desc" to "Bahasa yang mudah dipahami")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** Toolbar */
        binding.toolbar.title = "More"

        // Setup ListView
        val adapter = SimpleAdapter(
            requireContext(),
            dataListWithDesc,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        binding.listViewItems.adapter = adapter

        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataListWithDesc[position]
            val title = selectedItem["title"]
            val desc = selectedItem["desc"]
            Toast.makeText(requireContext(), "Kamu memilih: $title ($desc)", Toast.LENGTH_SHORT).show()
        }

        // Setup Logout Button
        binding.btnLogout.setOnClickListener {
            handleLogout()
        }
    }

    private fun handleLogout() {
        // Hapus status login dari SharedPreferences
        val sharedPref = requireActivity().getSharedPreferences("FishBookPref", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("isLoggedIn", false)
            putString("userName", "")
            apply()
        }

        Toast.makeText(requireContext(), "Berhasil Logout", Toast.LENGTH_SHORT).show()

        // Kembali ke AuthActivity
        val intent = Intent(requireContext(), AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
