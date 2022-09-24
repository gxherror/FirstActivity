package top.xherror.first_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import top.xherror.first_activity.databinding.SecondLayoutBinding

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = SecondLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extraData = intent.getStringExtra("extra_data")
        Log.d("SecondActivity", "extra data is $extraData")
        binding.button2.setOnClickListener {
            val intent = Intent()
            intent.putExtra("data_return", "Hello FirstActivity")
            setResult(RESULT_OK, intent)
            finish()
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