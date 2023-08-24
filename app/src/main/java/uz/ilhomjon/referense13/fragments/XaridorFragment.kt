package uz.ilhomjon.referense13.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.ilhomjon.referense13.adapter.SotuvchiXaridorAdapter
import uz.ilhomjon.referense13.databinding.FragmentSotuvchiBinding
import uz.ilhomjon.referense13.db.MyDbHelper
import uz.ilhomjon.referense13.models.Xaridor
import java.util.ArrayList

class XaridorFragment : Fragment() {

    private val binding by lazy { FragmentSotuvchiBinding.inflate(layoutInflater) }
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var sotuvchiXaridorAdapter: SotuvchiXaridorAdapter<Xaridor>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.edtAddress.visibility = View.VISIBLE

        binding.btnSave.setOnClickListener {
            val xaridor = Xaridor(
                0,
                binding.edtName.text.toString().trim(),
                binding.edtNumber.text.toString().trim(),
                binding.edtAddress.text.toString().trim()
            )
            myDbHelper.addXaridor(xaridor)
            onResume()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.edtNumber.text.clear()
        binding.edtAddress.text.clear()
        binding.edtName.text.clear()
        myDbHelper = MyDbHelper(binding.root.context)
        sotuvchiXaridorAdapter = SotuvchiXaridorAdapter(myDbHelper.getAllXaridor() as ArrayList<Xaridor>)
        binding.rv.adapter = sotuvchiXaridorAdapter
    }
}