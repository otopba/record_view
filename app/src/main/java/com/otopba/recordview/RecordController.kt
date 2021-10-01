package com.otopba.recordview

import android.content.Context
import android.media.MediaRecorder
import android.util.Log
import java.io.File
import java.lang.Exception

class RecordController(private val context: Context) {

    private var audioRecorder: MediaRecorder? = null

    fun start() {
        Log.d(TAG, "Start")
        audioRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(getAudioPath())
            prepare()
            start()
        }
    }

    private fun getAudioPath(): String {
        return "${context.cacheDir.absolutePath}${File.pathSeparator}${System.currentTimeMillis()}.wav"
    }

    fun stop() {
        audioRecorder?.let {
            Log.d(TAG, "Stop")
            it.stop()
            it.release()
        }
        audioRecorder = null
    }

    fun isAudioRecording() = audioRecorder != null

    fun getVolume() = audioRecorder?.maxAmplitude ?: 0

    private companion object {
        private val TAG = RecordController::class.java.name
    }
}