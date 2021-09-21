package com.stephenbain.mediarecorderdemo

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val recorder: Recorder) : ViewModel() {

    private val _state = MutableStateFlow(RecordingState(false))

    val state: Flow<RecordingState>
        get() = _state

    fun toggleRecording() {
        updateRecordingState(!_state.value.recording)
    }

    private fun updateRecordingState(recording: Boolean) {
        _state.value = _state.value.copy(recording = recording)
        if (recording) recorder.startRecording() else recorder.stopRecording()
    }

    override fun onCleared() {
        super.onCleared()
        recorder.release()
    }
}

data class RecordingState(val recording: Boolean)