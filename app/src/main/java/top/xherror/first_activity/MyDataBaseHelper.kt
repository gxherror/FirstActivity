package top.xherror.first_activity

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDataBaseHelper(val context:Context,val name:String,val version:Int):SQLiteOpenHelper(context, name, null, version) {
    private val createBook=
        "CREATE table Book(id integer primary key autoincrement,author text,price real,pages integer,name text)"
    private val createCategory=
        "CREATE table Test (id integer primary key autoincrement,name string)"
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createBook)
        db?.execSQL(createCategory)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion<=1){db?.execSQL(createCategory)}
        if (oldVersion<=2){db?.execSQL("ALTER TABLE Book ADD COLUMN category_id integer")}
    }
}