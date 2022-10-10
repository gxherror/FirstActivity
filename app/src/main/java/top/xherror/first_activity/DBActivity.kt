package top.xherror.first_activity

import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.accessibility.AccessibilityEventCompat.ContentChangeType
import top.xherror.first_activity.databinding.ActivityDbactivityBinding
import top.xherror.first_activity.databinding.FirstLayoutBinding
import java.io.IOException

class DBActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDbactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dbHelper=MyDataBaseHelper(this,"BookStore.db",2)
        binding.activityDBInsert.setOnClickListener {
            val db=dbHelper.writableDatabase.run {
                beginTransaction()
                try {
                    delete("Book",null,null)
                    insert("Book",null,ContentValues().apply {
                        put("name","QQQ")
                        put("author","xherror")
                        put("pages","122")
                        put("price",16.55) })
                    execSQL("INSERT INTO Book(name,author,pages,price) VALUES(?,?,?,?)", arrayOf("QWQ","xherror","654","12.45"))
                }catch (e:java.lang.Exception){
                    e.printStackTrace()
                }finally {
                    endTransaction()
                }

            }
            //val cursor=db.rawQuery("SELECT * FROM Book ORDER BY price",null)
            //Toast.makeText(this,cursor.columnNames.toString(),Toast.LENGTH_SHORT).show()
        }

        binding.activityDBQuery.setOnClickListener {
            val uri= Uri.parse("content://top.xherror.first_activity.provider/book")
            contentResolver.query(uri,null,null,null,null)?.let {
                if(it.moveToFirst()){
                    do {
                        val id=it.getString(it.getColumnIndex("id"))
                        val author=it.getString(it.getColumnIndex("author"))
                        Toast.makeText(this,"$id+$author",Toast.LENGTH_SHORT).show()
                    } while (it.moveToNext())
                }
            }

        }

    }
}