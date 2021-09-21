package com.stephenbain.mediarecorderdemo

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Recorder @Inject constructor(@ApplicationContext private val context: Context) {

    private val recorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        MediaRecorder(context)
    } else {
        MediaRecorder()
    }

    fun startRecording() {
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        // aac example
        recorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS)
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        // mp4 example
//        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
//        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT)
        recorder.setOutputFile("${context.externalCacheDir?.absolutePath}/sample.aac")
        recorder.prepare()
        recorder.start()
    }

    fun stopRecording() {
        recorder.stop()
    }

    fun release() = recorder.release()
}