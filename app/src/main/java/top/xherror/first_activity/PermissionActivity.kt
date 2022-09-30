package top.xherror.first_activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import top.xherror.first_activity.databinding.ActivityPermissionBinding

class PermissionActivity : AppCompatActivity() {
    private val contactList=ArrayList<String>()
    lateinit var adapter:ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.activityPermissionCallPhone.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
            } else {
                call()
            }
        }

        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,contactList )
        binding.activityPermissionListView.adapter = adapter

        binding.activityPermissionShowContacts.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 2)
            } else {
                load()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            1->{
                if (grantResults.isEmpty()&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    call()
                }else{
                    Toast.makeText(this, "You denied the permission",
                        Toast.LENGTH_SHORT).show()

                }
            }
            2->{
                if (grantResults.isEmpty()&&grantResults[0]!= PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    protected fun call(){
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:10086")
        startActivity(intent)
    }

    protected fun  load(){
        contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null)?.apply {
            while (moveToNext()){
                contactList.add("${getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))}\n${getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))}")
                }
                adapter.notifyDataSetChanged()
                close()
        }
    }
}