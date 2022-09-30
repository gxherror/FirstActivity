package top.xherror.first_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class BroadSenderActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broad_sender)
        val button:Button=findViewById(R.id.activityBroadSenderButtonSend)
        button.setOnClickListener {
            val intent = Intent("top.xherror.firstactivity.MY_BROADCAST")
            intent.setPackage(packageName)
            //sendBroadcast(intent)
            sendOrderedBroadcast(intent,null)
        }
    }
}