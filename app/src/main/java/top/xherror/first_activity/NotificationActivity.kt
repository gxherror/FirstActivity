package top.xherror.first_activity

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.xherror.first_activity.databinding.ActivityNotificationBinding
import top.xherror.first_activity.databinding.FirstLayoutBinding

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val channelId="0"
        val channelName="一般通知"
        val importance= NotificationManager.IMPORTANCE_HIGH
        val manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(NotificationChannel(channelId,channelName,importance))
        val intent=Intent(this,NormalActivity::class.java)
        val notification=Notification.Builder(this,channelId)
            .setContentIntent(PendingIntent.getActivity(this,0, intent,PendingIntent.FLAG_IMMUTABLE))
            .setContentTitle("Title")
            .setContentText("TextTextText")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
            .setStyle(Notification.BigPictureStyle().bigPicture(
                BitmapFactory.decodeResource(resources,R.drawable.banana)))
            .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.apple))
            .build()
        manager.notify(0,notification)
        //manager.cancel(0)
    }
}