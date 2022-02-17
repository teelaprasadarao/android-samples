package com.android.samples.timber

import android.util.Log
import timber.log.Timber
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

/**
 * Created by Prasada Rao on 17/02/22 4:43 PM
 **/
class LogFileWriterTree(
  private val logFile: File
) : Timber.Tree() {

  private companion object {
    private const val DEFAULT_LOG_DELIMITER = " "
  }

  override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
    writeToFile(priority, tag, message)
  }

  private fun writeToFile(priority: Int, tag: String?, message: String) {
    BufferedWriter(FileWriter(logFile)).use { writer ->
      writer.append("Android")
        .append(DEFAULT_LOG_DELIMITER)
        .append(getLogLevel(priority).toString())
        .append(DEFAULT_LOG_DELIMITER)
        .append(tag)
        .append(DEFAULT_LOG_DELIMITER)
        .append(message)
        .append("\n")
        .flush()
    }
  }

  private fun getLogLevel(priority: Int): LogLevel {
    return when (priority) {
      Log.VERBOSE -> LogLevel.VERBOSE
      Log.INFO -> LogLevel.INFO
      Log.WARN -> LogLevel.WARNING
      Log.ERROR -> LogLevel.ERROR
      else -> LogLevel.DEBUG
    }
  }

  private enum class LogLevel {
    VERBOSE,
    INFO,
    WARNING,
    ERROR,
    DEBUG;

    override fun toString(): String = name
  }
}