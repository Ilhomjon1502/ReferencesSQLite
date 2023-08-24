package uz.ilhomjon.referense13.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.ilhomjon.referense13.R
import uz.ilhomjon.referense13.adapter.SotuvchiXaridorAdapter
import uz.ilhomjon.referense13.databinding.FragmentSotuvchiBinding
import uz.ilhomjon.referense13.db.MyDbHelper
import uz.ilhomjon.referense13.models.Sotuvchi
import java.util.ArrayList

class SotuvchiFragment : Fragment() {

    private val binding by lazy { FragmentSotuvchiBinding.inflate(layoutInflater) }
    private lateinit var sotuvchiXaridorAdapter: SotuvchiXaridorAdapter<Sotuvchi>
    private lateinit var myDbHelper: MyDbHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.btnSave.setOnClickListener {
            val sotuvchi = Sotuvchi(
                0,
                binding.edtName.text.toString().trim(),
                binding.edtNumber.text.toString().trim()
            )
            myDbHelper.addSotuvchi(sotuvchi)
            onResume()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.edtNumber.text.clear()
        binding.edtName.text.clear()
        myDbHelper = MyDbHelper(binding.root.context)
        sotuvchiXaridorAdapter =
            SotuvchiXaridorAdapter(myDbHelper.getAllSotuvchi() as ArrayList<Sotuvchi>)
        binding.rv.adapter = sotuvchiXaridorAdapter
    }

}