package com.example.fishbook.Message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fishbook.MainActivity
import com.example.fishbook.Message.tutorial.TutorialAdapter
import com.example.fishbook.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    private val messageList = listOf(
        MessageModel("Capt. Juna", "Gimana tarikannya hari ini? Strike terus?", "https://api.dicebear.com/9.x/avataaars/png?seed=Juna", "10:30 AM"),
        MessageModel("Angler Budi", "Besok spot waduk Gajah Mungkur oke gak?", "https://api.dicebear.com/9.x/avataaars/png?seed=Budi", "10:35 AM"),
        MessageModel("Citra Mancing", "Umpan softfrog warna hijau lagi gacor nih!", "https://api.dicebear.com/9.x/avataaars/png?seed=Citra", "11:00 AM"),
        MessageModel("Dika Bass", "Info spot Gabus dong om, lagi pengen casting.", "https://api.dicebear.com/9.x/avataaars/png?seed=Dika", "Yesterday"),
        MessageModel("Eka Shimano", "Reel Stradic-nya udah mendarat mas, mantap!", "https://api.dicebear.com/9.x/avataaars/png?seed=Eka", "Yesterday"),
        MessageModel("Fajar Casting", "Ikan Toman-nya beringas bener tadi pagi.", "https://api.dicebear.com/9.x/avataaars/png?seed=Fajar", "Monday"),
        MessageModel("Gita Angler", "Ada yang punya info jadwal pasang surut?", "https://api.dicebear.com/9.x/avataaars/png?seed=Gita", "Monday"),
        MessageModel("Hana Fishing", "Spot muara lagi banyak Kakap Putih nih.", "https://api.dicebear.com/9.x/avataaars/png?seed=Hana", "Sunday"),
        MessageModel("Irfan Jigging", "Minggu depan sewa kapal bareng yuk!", "https://api.dicebear.com/9.x/avataaars/png?seed=Irfan", "Sunday"),
        MessageModel("Joko Strike", "Rekor Barramundi 5kg pecah hari ini!", "https://api.dicebear.com/9.x/avataaars/png?seed=Joko", "Last Week")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupViewPager()
        setupMessageList()
        
        // Klik tombol info untuk memunculkan tutorial
        binding.btnTutorial.setOnClickListener {
            showTutorial()
        }
    }

    private fun setupViewPager() {
        val adapter = TutorialAdapter(this)
        binding.viewPagerTutorial.adapter = adapter
        
        // Hubungkan DotsIndicator dengan ViewPager2
        binding.dotsIndicator.setViewPager2(binding.viewPagerTutorial)
    }

    private fun setupMessageList() {
        // Inisialisasi Custom Adapter
        val adapter = MessageAdapter(requireContext(), messageList)

        // Hubungkan ListView dengan adapter
        binding.listViewMessages.adapter = adapter
    }

    private fun showTutorial() {
        binding.layoutTutorial.visibility = View.VISIBLE
        binding.layoutMainContent.visibility = View.GONE
        
        // Sembunyikan Navigasi di MainActivity agar full screen
        (activity as? MainActivity)?.setNavVisibility(false)
    }

    fun finishTutorial() {
        binding.layoutTutorial.visibility = View.GONE
        binding.layoutMainContent.visibility = View.VISIBLE
        
        // Tampilkan kembali Navigasi di MainActivity
        (activity as? MainActivity)?.setNavVisibility(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
