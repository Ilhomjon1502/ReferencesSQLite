package uz.ilhomjon.referense13.models

data class Buyurtma(
    var id:Int,
    var name:String,
    var date:String,
    var sotuvchi: Sotuvchi,
    var xaridor: Xaridor
)
