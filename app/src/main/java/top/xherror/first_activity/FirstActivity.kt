package top.xherror.first_activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import top.xherror.first_activity.databinding.FirstLayoutBinding

class FirstActivity : AppCompatActivity() {
    val tag="MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.first_layout)
        val binding = FirstLayoutBinding.inflate(layoutInflater) //FirstLayoutBinding bind to name
        setContentView(binding.root)
        //binding.textView.text = "Hello"
        //val button1:Button=findViewById(R.id.button1)

        val toSecondActivity= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            when(it.resultCode){
                RESULT_OK -> {
                    val data=it.data?.getStringExtra("data_return")
                    Log.d("FirstActivity","return data is $data")
                }
            }
        }
        binding.button1.setOnClickListener{
            Toast.makeText(this,"click button1",Toast.LENGTH_SHORT).show()
            //val intent=Intent("android.intent.action.ACTION_START")
            //val intent=Intent(Intent.ACTION_VIEW)
            //val intent=Intent(Intent.ACTION_DIAL)
            //intent.data = Uri.parse("tel:10086")
            //intent.data = Uri.parse("https://www.baidu.com")
            //intent.addCategory("android.intent.category.MY_CATEGORY")
            //val intent=)//SecondActivity::class.java==SecondActivity.class in java
            val intent=Intent(this,SecondActivity::class.java)
            intent.putExtra("extra_data","Hello SecondActivity")
            toSecondActivity.launch(intent)
            //startActivity(intent)
            //finish()
        }

        val toNormalActivity= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            when(it.resultCode){
                RESULT_OK -> {
                    val data=it.data?.getStringExtra("data_return")
                    Log.d("NormalActivity","return data is $data")
                }
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
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag,"onRestart")
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