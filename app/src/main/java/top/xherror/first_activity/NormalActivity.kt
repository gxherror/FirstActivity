package top.xherror.first_activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import top.xherror.first_activity.databinding.ActivityNormalBinding
import top.xherror.first_activity.databinding.FirstLayoutBinding

class Temp{

}

class NormalActivity : BaseActivity() {
    private val chatList=ArrayList<Message>()
    private val tag="NormalActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        val tempData=savedInstanceState?.get("previousChatList")
        Log.d("debug",tempData.toString())
        super.onCreate(savedInstanceState)
        val binding = ActivityNormalBinding.inflate(layoutInflater) //FirstLayoutBinding bind to name
        setContentView(binding.root)
        val layoutManager=LinearLayoutManager(this)
        //layoutManager.orientation=LinearLayoutManager.HORIZONTAL
        binding.recyclerViewChat.layoutManager=layoutManager
        chatList.add(Message("TE\nST\nTE\nST\nTE\nST\nTE\nST","self"))
        chatList.add(Message("TESTESTEST","other"))
        val adapter= ChatAdapter(R.drawable.apple,R.drawable.banana,"Hinar","Herror",chatList)
        binding.recyclerViewChat.adapter=adapter
        binding.buttonSend.setOnClickListener {
            val sendMessage=binding.editTextSend.text.toString()
            if (sendMessage!=""){
                chatList.add(Message(sendMessage,"self"))
                adapter.notifyItemInserted(chatList.size-1)
                binding.recyclerViewChat.scrollToPosition(chatList.size-1)
                binding.editTextSend.setText("")
            }
        //    //binding.editTextSend.text="" as Editable
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        ActivityCollector.finishAll()
        //android.os.Process.killProcess(android.os.Process.myPid())
        //finish()
    }

    override fun onStop() {
        super.onStop()
    }


}