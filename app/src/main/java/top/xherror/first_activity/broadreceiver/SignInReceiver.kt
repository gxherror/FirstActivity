package top.xherror.first_activity.broadreceiver

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.view.WindowManager
import android.widget.Toast
import top.xherror.first_activity.BaseActivity
import top.xherror.first_activity.SignInActivity

class SignInReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

        val builder= AlertDialog.Builder(context)
        builder.run {
            setTitle("Warning")
            setMessage("You are forced to offline,please sign again")
            setCancelable(false)
            setPositiveButton("OK"){dialog,which->
                BaseActivity.finishAll()
                val intent =Intent(context,SignInActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent);
            }
        }
        val alterDialog = builder.create()
        alterDialog.window?.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
        alterDialog.show()

    }
}