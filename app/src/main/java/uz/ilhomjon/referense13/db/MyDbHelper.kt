package uz.ilhomjon.referense13.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import uz.ilhomjon.referense13.models.Buyurtma
import uz.ilhomjon.referense13.models.Sotuvchi
import uz.ilhomjon.referense13.models.Xaridor

class MyDbHelper(val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION),
    MyDbInterface {
    companion object {
        const val DB_NAME = "buyurtma_db"
        const val DB_VERSION = 1
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val sotuvchiQuery =
            "create table sotuvchi_table (id integer not null primary key autoincrement unique, name text not null, number text not null)"
        val xaridorQuery =
            "create table xaridor_table (id integer not null primary key autoincrement unique, name text not null, number text not null, address text not null)"
        val buyurtmaQuery =
            "create table buyurtma_table (id integer not null primary key autoincrement unique, name text not null, date text not null, sotuvchi_id integer not null, xaridor_id integer not null, foreign key (sotuvchi_id) references sotuvchi_table (id), foreign key (xaridor_id) references xaridor_table (id) );"

        p0?.execSQL(sotuvchiQuery)
        p0?.execSQL(xaridorQuery)
        p0?.execSQL(buyurtmaQuery)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun addSotuvchi(sotuvchi: Sotuvchi) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", sotuvchi.name)
        contentValues.put("number", sotuvchi.number)
        database.insert("sotuvchi_table", null, contentValues)
        database.close()
    }

    override fun getAllSotuvchi(): List<Sotuvchi> {
        val database = this.readableDatabase
        val list = ArrayList<Sotuvchi>()
        val query = "select * from sotuvchi_table"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    Sotuvchi(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                    )
                )
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun addXaridor(xaridor: Xaridor) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", xaridor.name)
        contentValues.put("number", xaridor.number)
        contentValues.put("address", xaridor.address)
        database.insert("xaridor_table", null, contentValues)
        database.close()
    }

    override fun getAllXaridor(): List<Xaridor> {
        val database = this.readableDatabase
        val list = ArrayList<Xaridor>()
        val query = "select * from xaridor_table"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    Xaridor(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                    )
                )
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun addBuyurtma(buyurtma: Buyurtma) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", buyurtma.name)
        contentValues.put("date", buyurtma.date)
        contentValues.put("sotuvchi_id", buyurtma.sotuvchi.id)
        contentValues.put("xaridor_id", buyurtma.xaridor.id)
        database.insert("buyurtma_table", null, contentValues)
        database.close()
    }

    override fun getAllBuyurtma(): List<Buyurtma> {
        val list = ArrayList<Buyurtma>()
        val database = this.readableDatabase

        val query = "select * from buyurtma_table"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    Buyurtma(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        getSotuvchiById(cursor.getInt(3)),
                        getXaridorById(cursor.getInt(4))
                    )
                )
            } while (cursor.moveToNext())
        }

        return list
    }

    override fun getSotuvchiById(id: Int): Sotuvchi {
        val database = this.readableDatabase
        val cursor = database.query(
            "sotuvchi_table",
            arrayOf("id", "name", "number"),
            "id = ?",
            arrayOf(id.toString()),
            null, null, null
        )

        cursor.moveToFirst()
        val sotuvchi = Sotuvchi(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2)
        )
        return sotuvchi
    }

    override fun getXaridorById(id: Int): Xaridor {
        val database = this.readableDatabase
        val cursor = database.query(
            "xaridor_table",
            arrayOf("id", "name", "number", "address"),
            "id = ?",
            arrayOf(id.toString()),
            null, null, null
        )

        cursor.moveToFirst()
        val sotuvchi = Xaridor(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3)
        )
        return sotuvchi
    }
}