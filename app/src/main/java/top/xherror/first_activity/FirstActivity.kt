package top.xherror.first_activity

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import top.xherror.first_activity.databinding.FirstLayoutBinding

class FirstActivity : BaseActivity(),View.OnClickListener{
    private val tag="MainActivity"
    private val fruitlist=ArrayList<Fruit>()
    lateinit var timeChangeReceiver: TimeChangeReceiver
    private fun initFruits(){
        fruitlist.add(Fruit("Apple",R.drawable.apple))
        fruitlist.add(Fruit("Banana",R.drawable.banana))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TTT",loadStringFromSP("test"))
        Log.d(tag,this.toString())
        Log.d(tag,"$taskId")
        val intentFilter =IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceiver=TimeChangeReceiver()
        registerReceiver(timeChangeReceiver,intentFilter)
        //setContentView(R.layout.first_layout)
        val binding = FirstLayoutBinding.inflate(layoutInflater) //FirstLayoutBinding bind to name
        setContentView(binding.root)
        //binding.textView.text = "Hello"
        //val button1:Button=findViewById(R.id.button1)
        if (savedInstanceState!=null){
            val tempData=savedInstanceState.getString("data_key")
            Log.d("tag","tempData is $tempData")
        }

        Log.d(tag,R.id.button1.toString())
        Log.d(tag,binding.button1.toString())
        //val adapter=FruitAdapter(this,R.layout.fruit_item,fruitlist)
        //binding.listView1.adapter=adapter
        //binding.listView1.adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,data)

        val toSecondActivity= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            when(it.resultCode){
                RESULT_OK -> {
                    val data=it.data?.getStringExtra("data_return")
                    Log.d("FirstActivity","return data is $data")
                }
            }
        }

        binding.listView1.setOnItemClickListener { _, _, position, _ ->
            val fruit=fruitlist[position]
            Toast.makeText(this,"This is ${fruit.name}",Toast.LENGTH_SHORT).show()
            val intent:Intent= Intent(Intent.ACTION_VIEW)
            intent.data= Uri.parse("https://en.wikipedia.org/wiki/${fruit.name}")
            startActivity(intent)

        }

        binding.button1.setOnClickListener(){
            val editText1=binding.editText1.text.toString()
            binding.imageView1.setImageResource(R.drawable.ic_launcher_foreground)
            binding.progressBar01.visibility=View.VISIBLE
            binding.progressBar01.progress=binding.progressBar01.progress+10
            Toast.makeText(this,editText1,Toast.LENGTH_SHORT).show()
            AlertDialog.Builder(this).run {
                setTitle("This is a alert dialog!")
                setMessage("FBI WARNING!")
                setCancelable(false)
                setPositiveButton("OK"){
                        dialog,which->
                }
                setNegativeButton("Cancel"){
                        dialog,which->
                }
                show()
            }
            //val intent=Intent("android.intent.action.ACTION_START")
            //val intent=Intent(Intent.ACTION_VIEW)
            //val intent=Intent(Intent.ACTION_DIAL)
            //intent.data = Uri.parse("tel:10086")
            //intent.data = Uri.parse("https://www.baidu.com")
            //intent.addCategory("android.intent.category.MY_CATEGORY")
            //val intent=)//SecondActivity::class.java==SecondActivity.class in java
            val intent=Intent(this,SecondActivity::class.java)
            SecondActivity.actionStart(this,"data1","data2")
            toSecondActivity.launch(intent)
            //startActivity(intent)
            //finish()
        }

        binding.firstLayoutButtonToBroadSender.setOnClickListener {
            startActivity(Intent(this,SignInActivity::class.java))
        }

        binding.firstLayoutButtonToDB.setOnClickListener {
            startActivity(Intent(this,DBActivity::class.java))
        }

        binding.button3.setOnClickListener {
            val intent=Intent(this,FragmentActivity::class.java)
            startActivity(intent)
        }

        val toNormalActivity= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            when(it.resultCode){
                RESULT_OK -> {
                    Log.d("data_return",it.data.toString())
                    val data=it.data?.getStringExtra("data_return")
                    Log.d("data_return","return data is $data")
                }
                else->{Log.d("data_return",it.resultCode.toString())}
            }
        }
        binding.startNormalActivity.setOnClickListener {
            val intent=Intent(this,NormalActivity::class.java)
            intent.putExtra("extra_data","Hello NormalActivity")
            toNormalActivity.launch(intent)
        }

        val toDialogActivity= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            when(it.resultCode){
                RESULT_OK -> {
                    val data=it.data?.getStringExtra("data_return")
                    Log.d("DialogActivity","return data is $data")
                }
            }
        }
        binding.startDialogActivity.setOnClickListener {
            val intent=Intent(this,DialogActivity::class.java)
            intent.putExtra("extra_data","Hello DialogActivity")
            toDialogActivity.launch(intent)
        }
    }

    override fun onClick(v1 :View?){
        Toast.makeText(this,"click button1",Toast.LENGTH_SHORT).show()
        //val intent=Intent("android.intent.action.ACTION_START")
        //val intent=Intent(Intent.ACTION_VIEW)
        //val intent=Intent(Intent.ACTION_DIAL)
        //intent.data = Uri.parse("tel:10086")
        //intent.data = Uri.parse("https://www.baidu.com")
        //intent.addCategory("android.intent.category.MY_CATEGORY")
        //val intent=)//SecondActivity::class.java==SecondActivity.class in java
        val intent=Intent(this,SecondActivity::class.java)
        SecondActivity.actionStart(this,"data1","data2")
        //toSecondActivity.launch(intent)
        //startActivity(intent)
        //finish()
    }

    inner class TimeChangeReceiver:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context,"Time has changed",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag,"onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag,"onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag,"onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag,"onDestroy")
        unregisterReceiver(timeChangeReceiver)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag,"onRestart")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("data_key","files you just modified")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_item -> Toast.makeText(this,"You clicked Add",Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this,"You clicked Remove",Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}

