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
            GameProduct("Goldfish", "Aquarium", "Rp 5.000", "https://images.unsplash.com/photo-1524704654690-b56c05c78a00?q=80&w=500&auto=format&fit=crop"),
            GameProduct("Betta Fish", "Freshwater", "Rp 25.000", "https://images.unsplash.com/photo-1534067783941-51c9c23ecefd?q=80&w=500&auto=format&fit=crop"),
            GameProduct("Koi Fish", "Pond", "Rp 150.000", "https://images.unsplash.com/photo-1522069169874-c58ec4b76be5?q=80&w=500&auto=format&fit=crop"),
            GameProduct("Guppy", "Nano Tank", "Rp 3.000", "https://images.unsplash.com/photo-1544551763-46a013bb70d5?q=80&w=500&auto=format&fit=crop"),
            GameProduct("Discus", "Amazon", "Rp 200.000", "https://images.unsplash.com/photo-1516233758813-a38d024919c5?q=80&w=500&auto=format&fit=crop"),
            GameProduct("Angelfish", "Tropical", "Rp 45.000", "https://images.unsplash.com/photo-1524704796725-9fc3044a58b2?q=80&w=500&auto=format&fit=crop"),
            GameProduct("Arowana", "Monster Fish", "Rp 1.500.000", "https://images.unsplash.com/photo-1510425514574-67250629237b?q=80&w=500&auto=format&fit=crop"),
            GameProduct("Neon Tetra", "Schooling", "Rp 2.000", "https://images.unsplash.com/photo-1571752726703-5e7d1f6a986d?q=80&w=500&auto=format&fit=crop")
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
