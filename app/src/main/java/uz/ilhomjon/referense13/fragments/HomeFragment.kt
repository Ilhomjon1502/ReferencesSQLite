package uz.ilhomjon.referense13.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.ilhomjon.referense13.R
import uz.ilhomjon.referense13.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.apply {
            btnSotuvchi.setOnClickListener {
                findNavController().navigate(R.id.sotuvchiFragment)
            }
            btnXaridor.setOnClickListener {
                findNavController().navigate(R.id.xaridorFragment)
            }
            btnBuyurtmalar.setOnClickListener {
                findNavController().navigate(R.id.buyurtmaFragment)
            }
        }

        return binding.root
    }

}