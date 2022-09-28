package top.xherror.first_activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import top.xherror.first_activity.databinding.ActivityNewsContentBinding

class NewsContentActivity : AppCompatActivity() {

    companion object {
        fun actionStart(context: Context, newsTitle:String, newsContent:String){
            val intent = Intent(context,NewsContentActivity::class.java)
            intent.putExtra("newsTitle",newsTitle)
            intent.putExtra("newsContent",newsContent)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewsContentBinding.inflate(layoutInflater) //FirstLayoutBinding bind to name
        setContentView(binding.root)
        val newsTitle=intent.getStringExtra("newsTitle")
        val newsContent=intent.getStringExtra("newsContent")
        val fragment = supportFragmentManager.findFragmentById(R.id.activityNewsContentFragment)
        val fragmentNewsContentNewsTitle: TextView?=fragment?.view?.findViewById(R.id.fragmentNewsContentNewsTitle)
        val fragmentNewsContentNewsContent:TextView?=fragment?.view?.findViewById(R.id.fragmentNewsContentNewsContent)
        fragmentNewsContentNewsTitle?.text=newsTitle
        fragmentNewsContentNewsContent?.text=newsContent

    }
}