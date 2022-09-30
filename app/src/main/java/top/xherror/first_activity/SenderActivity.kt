package top.xherror.first_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SenderActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sender)
        val button=findViewById<View>(R.id.activitySenderButton)
        button.setOnClickListener {
            val intent=Intent("top.xherror.first_activity.SIGN_IN")
            intent.setPackage(packageName)
            sendBroadcast(intent)
        }
    }
}