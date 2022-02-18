package com.android.samples.timber

import android.app.Application
import android.util.Log
import timber.log.Timber
import java.io.File

/**
 * Created by Prasada Rao on 17/02/22 4:58 PM
 **/
class TimberSampleApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    Timber.plant(LogFileWriterTree(getLogFile()))
  }

  fun getLogFile(): File {
    val file = File(getExternalFilesDir(null), "log_file.txt")
    Log.i("", "Log file path = ${file.absolutePath}")
    return file
  }
}