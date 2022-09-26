package top.xherror.first_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class NormalActivity : BaseActivity() {
    private val tag="NormalActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag,"$taskId")
        setContentView(R.layout.activity_normal)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        ActivityCollector.finishAll()
        //android.os.Process.killProcess(android.os.Process.myPid())
        //finish()
    }
}