package com.example.fishbook.Home.pertemuan_7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fishbook.databinding.FragmentHomeBinding

class GameFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val listGame = mutableListOf(
            GameProduct("Mobile Legends", "Moonton", "Rp 3.000", "https://i.pinimg.com/736x/8a/9e/9e/8a9e9e6f3d1e6d3d3d3d3d3d3d3d3d3d.jpg"),
            GameProduct("Free Fire", "Garena", "Rp 1.000", "https://i.pinimg.com/736x/0a/6a/9a/0a6a9a6f3d1e6d3d3d3d3d3d3d3d3d3d.jpg"),
            GameProduct("PUBG Mobile", "Tencent", "Rp 15.000", "https://i.pinimg.com/736x/b2/b2/b2/b2b2b26f3d1e6d3d3d3d3d3d3d3d3d3d.jpg"),
            GameProduct("Genshin Impact", "HoYoverse", "Rp 16.000", "https://i.pinimg.com/736x/c3/c3/c3/c3c3c36f3d1e6d3d3d3d3d3d3d3d3d3d.jpg"),
            GameProduct("Point Blank", "Zepetto", "Rp 10.000", "https://i.pinimg.com/736x/d4/d4/d4/d4d4d46f3d1e6d3d3d3d3d3d3d3d3d3d.jpg"),
            GameProduct("Valorant", "Riot Games", "Rp 50.000", "https://i.pinimg.com/736x/e5/e5/e5/e5e5e56f3d1e6d3d3d3d3d3d3d3d3d3d.jpg"),
            GameProduct("Roblox", "Roblox Corp", "Rp 15.000", "https://i.pinimg.com/736x/f6/f6/f6/f6f6f66f3d1e6d3d3d3d3d3d3d3d3d3d.jpg"),
            GameProduct("Call of Duty", "Activision", "Rp 12.000", "https://i.pinimg.com/736x/a1/a1/a1/a1a1a16f3d1e6d3d3d3d3d3d3d3d3d3d.jpg")
        )

        val adapter = GameAdapter(listGame) { position ->
            listGame.removeAt(position)
            binding.rvGame.adapter?.notifyItemRemoved(position)
            binding.rvGame.adapter?.notifyItemRangeChanged(position, listGame.size)
        }
        binding.rvGame.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvGame.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
