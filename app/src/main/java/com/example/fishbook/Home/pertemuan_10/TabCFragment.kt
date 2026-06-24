package com.example.fishbook.Home.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fishbook.databinding.FragmentTabCBinding

class TabCFragment : Fragment() {

    private var _binding: FragmentTabCBinding? = null
    private val binding get() = _binding!!

    private val productList = listOf(
        // REELS
        ProductModel("Shimano Stradic FL", "Rp 2.850.000", "https://images.unsplash.com/photo-1611095773767-11477a667931?q=80&w=400"),
        ProductModel("Daiwa Exist LT", "Rp 11.500.000", "https://images.unsplash.com/photo-1544551763-46a013bb70d5?q=80&w=400"),
        ProductModel("Penn Battle III", "Rp 1.450.000", "https://images.unsplash.com/photo-1593106410288-caf65eca7c9d?q=80&w=400"),
        ProductModel("Abu Garcia Revo Beast", "Rp 3.200.000", "https://images.unsplash.com/photo-1541565732739-1bc48f0bb902?q=80&w=400"),
        
        // RODS
        ProductModel("Joran Shimano Lesath", "Rp 6.700.000", "https://images.unsplash.com/photo-1583209814683-c023dd293cc6?q=80&w=400"),
        ProductModel("Joran Maguro Extreme", "Rp 450.000", "https://images.unsplash.com/photo-1516233758813-a38d024919c5?q=80&w=400"),
        ProductModel("Daikaiju Slow Jigging", "Rp 1.850.000", "https://images.unsplash.com/photo-1524704654690-b56c05c78a00?q=80&w=400"),
        
        // LURES
        ProductModel("Lure Rapala Magnum", "Rp 185.000", "https://images.unsplash.com/photo-1532509170117-109033306563?q=80&w=400"),
        ProductModel("Softfrog Gacor", "Rp 35.000", "https://images.unsplash.com/photo-1541565732739-1bc48f0bb902?q=80&w=400"),
        ProductModel("Jig Head 10g", "Rp 15.000", "https://images.unsplash.com/photo-1622322062624-91925695034c?q=80&w=400"),
        
        // FISH (Trophy Gallery)
        ProductModel("Ikan Arwana Super Red", "Rp 15.000.000", "https://images.unsplash.com/photo-1522061347061-3250c55d07a1?q=80&w=400"),
        ProductModel("Ikan Mas Koi Grade A", "Rp 2.500.000", "https://images.unsplash.com/photo-1524704654690-b56c05c78a00?q=80&w=400"),
        ProductModel("Blue Marlin Trophy", "Collectible", "https://images.unsplash.com/photo-1544551763-46a013bb70d5?q=80&w=400"),
        
        // AKSESORIS
        ProductModel("Senar PE Duraking", "Rp 120.000", "https://images.unsplash.com/photo-1622322062624-91925695034c?q=80&w=400"),
        ProductModel("Kotak Pancing Meiho", "Rp 650.000", "https://images.unsplash.com/photo-1593106410288-caf65eca7c9d?q=80&w=400"),
        ProductModel("Topi Mancing Camo", "Rp 85.000", "https://images.unsplash.com/photo-1588850567047-1849a4445e9d?q=80&w=400"),
        ProductModel("Jaket Pelampung", "Rp 350.000", "https://images.unsplash.com/photo-1618274147772-2c069255e37a?q=80&w=400"),
        ProductModel("Kacamata Polarized", "Rp 275.000", "https://images.unsplash.com/photo-1572635196237-14b3f281503f?q=80&w=400"),
        ProductModel("Timbangan Digital 50kg", "Rp 95.000", "https://images.unsplash.com/photo-1585846416120-3a7354ed7d39?q=80&w=400"),
        ProductModel("Hook Mustad 101", "Rp 45.000", "https://images.unsplash.com/photo-1622322062624-91925695034c?q=80&w=400"),
        
        // MORE ITEMS
        ProductModel("Tas Joran Hardcase", "Rp 420.000", "https://images.unsplash.com/photo-1593106410288-caf65eca7c9d?q=80&w=400"),
        ProductModel("Senter Kepala LED", "Rp 130.000", "https://images.unsplash.com/photo-1513694203232-719a280e022f?q=80&w=400"),
        ProductModel("Cooler Box 35L", "Rp 1.100.000", "https://images.unsplash.com/photo-1623940123014-41d3bc0a6473?q=80&w=400"),
        ProductModel("Fishing Kayak Single", "Rp 8.500.000", "https://images.unsplash.com/photo-1544551763-46a013bb70d5?q=80&w=400"),
        ProductModel("Fish Finder Garmin", "Rp 4.750.000", "https://images.unsplash.com/photo-1624397648292-3a7ec9a02271?q=80&w=400"),
        ProductModel("Glove Mancing Shimano", "Rp 210.000", "https://images.unsplash.com/photo-1542385151-efd9000785a0?q=80&w=400"),
        ProductModel("Vests Fishing Jacket", "Rp 450.000", "https://images.unsplash.com/photo-1516259762381-22954d7d3ad2?q=80&w=400"),
        ProductModel("Umpan Udang Tiruan", "Rp 25.000", "https://images.unsplash.com/photo-1541565732739-1bc48f0bb902?q=80&w=400"),
        ProductModel("Reel Spinning Ryobi", "Rp 580.000", "https://images.unsplash.com/photo-1593106410288-caf65eca7c9d?q=80&w=400"),
        ProductModel("Senar Fluorocarbon", "Rp 165.000", "https://images.unsplash.com/photo-1622322062624-91925695034c?q=80&w=400"),
        
        ProductModel("Jaring Ikan 5m", "Rp 175.000", "https://images.unsplash.com/photo-1516738901171-8eb4fc13bd20?q=80&w=400"),
        ProductModel("Tang Mancing Stainless", "Rp 85.000", "https://images.unsplash.com/photo-1593106410288-caf65eca7c9d?q=80&w=400"),
        ProductModel("Baitcasting Reel Daido", "Rp 320.000", "https://images.unsplash.com/photo-1541565732739-1bc48f0bb902?q=80&w=400"),
        ProductModel("Kursi Lipat Mancing", "Rp 240.000", "https://images.unsplash.com/photo-1505843490538-5133c6c7d0e1?q=80&w=400"),
        ProductModel("Lampu Celup Malam", "Rp 155.000", "https://images.unsplash.com/photo-1513694203232-719a280e022f?q=80&w=400"),
        ProductModel("Snap Swivel Box", "Rp 45.000", "https://images.unsplash.com/photo-1622322062624-91925695034c?q=80&w=400"),
        ProductModel("Pelampung Gede", "Rp 10.000", "https://images.unsplash.com/photo-1541565732739-1bc48f0bb902?q=80&w=400"),
        ProductModel("Tas Pinggang Lure", "Rp 180.000", "https://images.unsplash.com/photo-1593106410288-caf65eca7c9d?q=80&w=400"),
        ProductModel("Spatula Ikan (Net)", "Rp 120.000", "https://images.unsplash.com/photo-1516738901171-8eb4fc13bd20?q=80&w=400"),
        ProductModel("Boat Rod Holder", "Rp 280.000", "https://images.unsplash.com/photo-1583209814683-c023dd293cc6?q=80&w=400"),
        
        ProductModel("Reel Pancing Mini", "Rp 95.000", "https://images.unsplash.com/photo-1541565732739-1bc48f0bb902?q=80&w=400"),
        ProductModel("Umpan Kodok Karet", "Rp 15.000", "https://images.unsplash.com/photo-1541565732739-1bc48f0bb902?q=80&w=400"),
        ProductModel("Peralatan Fly Fishing", "Rp 3.500.000", "https://images.unsplash.com/photo-1544551763-46a013bb70d5?q=80&w=400"),
        ProductModel("Rod Berkley Cherry", "Rp 1.150.000", "https://images.unsplash.com/photo-1583209814683-c023dd293cc6?q=80&w=400"),
        ProductModel("Senar Blood Big Game", "Rp 220.000", "https://images.unsplash.com/photo-1622322062624-91925695034c?q=80&w=400"),
        ProductModel("Lip Grip Aluminum", "Rp 145.000", "https://images.unsplash.com/photo-1593106410288-caf65eca7c9d?q=80&w=400"),
        ProductModel("Joran Tegek Carbon", "Rp 450.000", "https://images.unsplash.com/photo-1516233758813-a38d024919c5?q=80&w=400"),
        ProductModel("Wader Pants waterproof", "Rp 750.000", "https://images.unsplash.com/photo-1618274147772-2c069255e37a?q=80&w=400"),
        ProductModel("Umpan Pelet Gacor", "Rp 25.000", "https://images.unsplash.com/photo-1541565732739-1bc48f0bb902?q=80&w=400"),
        ProductModel("Trophy Catch Record", "Master", "https://images.unsplash.com/photo-1544551763-46a013bb70d5?q=80&w=400")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProductAdapter(productList) { selectedItem ->
            Toast.makeText(requireContext(), "Pilih: ${selectedItem.name}", Toast.LENGTH_SHORT).show()
        }

        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
