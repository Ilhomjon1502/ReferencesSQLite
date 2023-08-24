package uz.ilhomjon.referense13.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ilhomjon.referense13.databinding.ItemRvBinding
import uz.ilhomjon.referense13.models.Buyurtma

class BuyurtmaAdapter(var list:ArrayList<Buyurtma> = ArrayList()):RecyclerView.Adapter<BuyurtmaAdapter.Vh>() {

         inner class Vh(val itemRvBinding: ItemRvBinding): RecyclerView.ViewHolder(itemRvBinding.root){

             fun onBind(myContact: Buyurtma){
                 itemRvBinding.tvName.text = myContact.name
                 itemRvBinding.tvNumber.text=myContact.date
                 itemRvBinding.tvSotuvchi.visibility = View.VISIBLE
                 itemRvBinding.tvSotuvchi.text = myContact.sotuvchi.name
                 itemRvBinding.tvXaridor.visibility = View.VISIBLE
                 itemRvBinding.tvXaridor.text = myContact.xaridor.name
             }
         }

         override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
             return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent, false))
         }

         override fun getItemCount(): Int = list.size

         override fun onBindViewHolder(holder: Vh, position: Int) {
             holder.onBind(list[position])
         }
     }