package com.example.fishbook.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.fishbook.R
import com.example.fishbook.databinding.FragmentHomeMainBinding
import com.example.fishbook.Home.pertemuan_2.SecondActivity
import com.example.fishbook.Home.pertemuan_3.ThirdActivity
import com.example.fishbook.Home.pertemuan_4.FourthActivity
import com.example.fishbook.Home.pertemuan_5.FifthActivity
import com.example.fishbook.Home.pertemuan_7.SeventhActivity

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

        // Load Hero Image
        Glide.with(this)
            .load("https://lh3.googleusercontent.com/aida-public/AB6AXuByeqx95nxHZ2VwjIIyrI5BXE0y1pmLD2O-SlqnF_gMv-tdboWT7isA25nk8n7ig4lvsuIjDX-xLSZWhkKgLt0mfL5IQoaqItGGcYIGKxry-U2QgCFY85ZBr1ONRmnT-OSFvANKuJBCoZn46VuARa95pIRcWikhey5O4r0ap-6ZJoqBCl9YAlUtuFfpwEfd4N_TfWYpVxGhSXxV4TroBVZnBJiGcdBPDC-6L9RxBCf0REl2yo14R0FvvxkaBq9kN99xMynnGpfSOrk")
            .centerCrop()
            .into(binding.ivHeroImage)

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
