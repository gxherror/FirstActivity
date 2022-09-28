package top.xherror.first_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import top.xherror.first_activity.databinding.ActivityFragmentBinding

 class FragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val tag="FragmentActivityDebug"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        //val fragmentNewsTitle = supportFragmentManager.findFragmentById(R.id.fragmentNewsTitle)
        //val fragmentNewsContent=supportFragmentManager.findFragmentById(R.id.fragmentNewsContent)
        //val binding =ActivityFragmentBinding.inflate(layoutInflater)
        //val fragment = supportFragmentManager.findFragmentById(R.id.fragmentLeft) as LeftFragment
        //val button:Button?=fragment?.view?.findViewById(R.id.fragmentLeftButton)
        //Log.d(tag,fragment.toString())
        //Log.d(tag,fragment?.view.toString())
        //setContentView(binding.root)
        //button?.setOnClickListener {
        //    replaceFragment(AnotherFragment())
        //}
        //replaceFragment(RightFragment())
    }

    //private fun replaceFragment(fragment: Fragment){
    //    val fragmentmanager=supportFragmentManager
    //    val transaction=fragmentmanager.beginTransaction()
    //    transaction.replace(R.id.fragmentFrameLayout,fragment)
    //    transaction.addToBackStack(null)
    //    transaction.commit()
    //}

}