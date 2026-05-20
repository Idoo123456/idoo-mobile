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
import com.example.fishbook.Home.pertemuan_9.NinthActivity
import com.example.fishbook.Home.pertemuan_10.TenthActivity

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

        // Load Generic Fishing Hero Image
        Glide.with(this)
            .load("https://images.unsplash.com/photo-1544551763-46a013bb70d5?q=80&w=2070&auto=format&fit=crop")
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
        binding.cardPertemuan9.setOnClickListener {
            startActivity(Intent(requireContext(), NinthActivity::class.java))
        }
        binding.cardPertemuan10.setOnClickListener {
            startActivity(Intent(requireContext(), TenthActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
