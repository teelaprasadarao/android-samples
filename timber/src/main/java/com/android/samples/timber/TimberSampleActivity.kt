package com.android.samples.timber

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.samples.timber.databinding.ActivityTimberSampleBinding
import timber.log.Timber
import java.io.File

/**
 * Created by Prasada Rao on 17/02/22 6:16 PM
 **/
class TimberSampleActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    for (i in 1..100) {
      Timber.d("Log $i")
    }

    val binding: ActivityTimberSampleBinding =
      DataBindingUtil.setContentView(this, R.layout.activity_timber_sample)
    binding.button.setOnClickListener {
      sendLogFile((application as TimberSampleApplication).getLogFile())
    }
  }

  private fun sendLogFile(logFile: File) {
    val uri = Uri.fromFile(logFile)

    Intent(Intent.ACTION_SEND).apply {
      type = "vnd.android.cursor.dir/email"
      putExtra(Intent.EXTRA_STREAM, uri)
      putExtra(Intent.EXTRA_SUBJECT, "Log File")
    }.also {
      startActivity(Intent.createChooser(it, "Send email..."))
    }
  }
}