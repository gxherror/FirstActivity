package top.xherror.first_activity

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Video
import android.widget.VideoView
import top.xherror.first_activity.databinding.ActivityMediaBinding

class MediaActivity : AppCompatActivity() {
    private val mediaPlayer=MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityMediaBinding.inflate(layoutInflater)
        val videoView=binding.activityMediaVideoView
        setContentView(binding.root)
        initMediaPlayer()

        binding.activityMediaButtonPlay.setOnClickListener {
            if (!videoView.isPlaying) {
                videoView.start()
            }
            //mediaPlayer.start()
        }
        binding.activityMediaButtonPause.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
            }
            //mediaPlayer.pause()
        }
        binding.activityMediaButtonStop.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.resume()
            }
            //mediaPlayer.reset()
            //initMediaPlayer()
        }

        videoView.setVideoURI(Uri.parse("android.resource://$packageName/${R.raw.load}"))
    }

    private fun initMediaPlayer(){
        val assetManager=assets
        val fd=assetManager.openFd("asking-for-a-date.mp3")
        mediaPlayer.setDataSource(fd.fileDescriptor,fd.startOffset,fd.length)
        mediaPlayer.prepare()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
        val videoView= findViewById<VideoView>(R.id.activityMediaVideoView)
        videoView.suspend()
    }

}