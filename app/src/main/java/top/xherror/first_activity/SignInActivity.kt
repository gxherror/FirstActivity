package top.xherror.first_activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import top.xherror.first_activity.databinding.ActivitySignInBinding
import top.xherror.first_activity.databinding.FirstLayoutBinding

class SignInActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val sp=getSharedPreferences("global", MODE_PRIVATE)
        var isCorrection=sp.getBoolean("rememberPassword",false)
        super.onCreate(savedInstanceState)
        val binding = ActivitySignInBinding.inflate(layoutInflater) //FirstLayoutBinding bind to name
        setContentView(binding.root)
        binding.activitySignInAccountEdit.setText(sp.getString("account",""))
        if (isCorrection){
            binding.activitySignInPasswordEdit.setText(sp.getString(sp.getString("account",""),""))
        }
        binding.activitySignInButton.setOnClickListener {
            if (!isCorrection){
                val requireSecret=sp.getString(binding.activitySignInAccountEdit.text.toString(),"")
                val inputSecret=
                    CommomUtils.createSignature(binding.activitySignInPasswordEdit.text.toString(), KEY)
                if (requireSecret==inputSecret) {
                    if (binding.activitySignInCheckBox.isChecked) {
                        isCorrection = true
                        sp.save { putBoolean("rememberPassword",true) }
                    }
                    startActivity(Intent(this, SenderActivity::class.java))
                }else{
                    Toast.makeText(this,"ERROR",Toast.LENGTH_SHORT).show()
                }
            }else{startActivity(Intent(this, SenderActivity::class.java))}
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }

}