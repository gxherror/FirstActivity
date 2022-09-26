package top.xherror.first_activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import top.xherror.first_activity.databinding.SecondLayoutBinding

class SecondActivity : BaseActivity() {
    private val tag="SecondActivity"
    private val fruitlist=ArrayList<Fruit>()
    private fun initFruits(){
        fruitlist.add(Fruit("Apple",R.drawable.apple))
        fruitlist.add(Fruit("Banana",R.drawable.banana))
        fruitlist.add(Fruit("Orange",R.drawable.orange))
        fruitlist.add(Fruit("Peach",R.drawable.peach))
        fruitlist.add(Fruit("Kiwi",R.drawable.kiwi))
    }

    companion object {
        fun actionStart(context:Context,data1:String,data2:String){
            val intent = Intent(context,SecondActivity::class.java)
            intent.putExtra("param1",data1)
            intent.putExtra("param2",data2)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag,this.toString())
        Log.d(tag,"$taskId")
        val binding = SecondLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extraData = intent.getStringExtra("extra_data")
        Log.d("SecondActivity", "extra data is $extraData")
        supportActionBar?.hide()

        initFruits()
        //val layoutManager= LinearLayoutManager(this)
        val layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        //layoutManager.orientation=LinearLayoutManager.HORIZONTAL
        binding.recyclerView1.layoutManager=layoutManager
        binding.recyclerView1.adapter=FruitAdapter(fruitlist)
        binding.button2.setOnClickListener {

            //val intent = Intent(this,NormalActivity::class.java)
            //intent.putExtra("data_return", "Hello FirstActivity")
            //startActivity(intent)
            //setResult(RESULT_OK, intent)
            //finish()
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent()
        intent.putExtra("data_return", "Hello FirstActivity")
        setResult(RESULT_OK, intent)
        finish()
    }

}