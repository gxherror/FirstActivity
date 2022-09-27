package top.xherror.first_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import top.xherror.first_activity.databinding.ActivityFragmentBinding

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =ActivityFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentmanager=supportFragmentManager
        val transaction=fragmentmanager.beginTransaction()
        transaction.replace(R.id.fragmentFrameLayout,fragment)
    }

}