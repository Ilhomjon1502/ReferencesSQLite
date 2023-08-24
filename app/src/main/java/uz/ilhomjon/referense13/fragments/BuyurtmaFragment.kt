package uz.ilhomjon.referense13.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import uz.ilhomjon.referense13.adapter.BuyurtmaAdapter
import uz.ilhomjon.referense13.databinding.FragmentSotuvchiBinding
import uz.ilhomjon.referense13.db.MyDbHelper
import uz.ilhomjon.referense13.models.Buyurtma
import uz.ilhomjon.referense13.models.Sotuvchi
import uz.ilhomjon.referense13.models.Xaridor

class BuyurtmaFragment:Fragment() {

     private val binding by lazy { FragmentSotuvchiBinding.inflate(layoutInflater) }
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var buyurtmaAdapter: BuyurtmaAdapter
    private lateinit var sotuvchiList:ArrayList<Sotuvchi>
    private lateinit var xaridorList:ArrayList<Xaridor>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myDbHelper = MyDbHelper(binding.root.context)
        binding.edtNumber.hint = "Date"
        binding.spinnerSotuvchi.visibility = View.VISIBLE
        binding.spinnerXaridor.visibility = View.VISIBLE

        sotuvchiList = myDbHelper.getAllSotuvchi() as ArrayList<Sotuvchi>
        xaridorList = myDbHelper.getAllXaridor() as ArrayList<Xaridor>

        val sotuvchiNameList = ArrayList<String>()
        val xaridorNameList = ArrayList<String>()

        sotuvchiList.forEach {
            sotuvchiNameList.add(it.name)
        }
        xaridorList.forEach {
            xaridorNameList.add(it.name)
        }

        binding.spinnerXaridor.adapter = ArrayAdapter<String>(binding.root.context, android.R.layout.simple_list_item_1, xaridorNameList)
        binding.spinnerSotuvchi.adapter = ArrayAdapter<String>(binding.root.context, android.R.layout.simple_list_item_1, sotuvchiNameList)

        binding.btnSave.setOnClickListener {
            val buyurtma = Buyurtma(
                0,
                binding.edtName.text.toString().trim(),
                binding.edtNumber.text.toString().trim(),
                sotuvchiList[binding.spinnerSotuvchi.selectedItemPosition],
                xaridorList[binding.spinnerXaridor.selectedItemPosition]
            )
            myDbHelper.addBuyurtma(buyurtma)
            onResume()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        buyurtmaAdapter = BuyurtmaAdapter(myDbHelper.getAllBuyurtma() as ArrayList<Buyurtma>)
        binding.rv.adapter = buyurtmaAdapter
    }
}