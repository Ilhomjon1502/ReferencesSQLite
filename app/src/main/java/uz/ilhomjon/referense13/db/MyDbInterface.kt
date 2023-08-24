package uz.ilhomjon.referense13.db

import uz.ilhomjon.referense13.models.Buyurtma
import uz.ilhomjon.referense13.models.Sotuvchi
import uz.ilhomjon.referense13.models.Xaridor

interface MyDbInterface {
    fun addSotuvchi(sotuvchi: Sotuvchi)
    fun getAllSotuvchi():List<Sotuvchi>

    fun addXaridor(xaridor: Xaridor)
    fun getAllXaridor():List<Xaridor>

    fun addBuyurtma(buyurtma: Buyurtma)
    fun getAllBuyurtma():List<Buyurtma>

    fun getSotuvchiById(id:Int):Sotuvchi
    fun getXaridorById(id:Int):Xaridor

}