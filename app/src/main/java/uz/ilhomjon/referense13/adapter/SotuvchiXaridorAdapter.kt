package uz.ilhomjon.referense13.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ilhomjon.referense13.databinding.ItemRvBinding
import uz.ilhomjon.referense13.models.Sotuvchi
import uz.ilhomjon.referense13.models.Xaridor
import java.lang.Exception

class SotuvchiXaridorAdapter<T>(var list:ArrayList<T> = ArrayList()): RecyclerView.Adapter<SotuvchiXaridorAdapter<T>.Vh>() {

         inner class Vh(val itemRvBinding: ItemRvBinding):RecyclerView.ViewHolder(itemRvBinding.root){
             fun onBind(myObject:T){
                 try {
                     val sotuvchi:Sotuvchi = myObject as Sotuvchi
                     itemRvBinding.tvName.text = sotuvchi.name
                     itemRvBinding.tvNumber.text = sotuvchi.number
                 }catch (e:Exception){
                     val xaridor:Xaridor = myObject as Xaridor
                     itemRvBinding.tvName.text = xaridor.name
                     itemRvBinding.tvNumber.text = xaridor.number
                     itemRvBinding.tvAddress.visibility = View.VISIBLE
                     itemRvBinding.tvAddress.text = xaridor.address
                 }
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