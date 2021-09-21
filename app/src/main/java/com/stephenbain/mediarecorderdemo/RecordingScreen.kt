package com.stephenbain.mediarecorderdemo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RecordingScreen() {
    val viewModel = viewModel<MainViewModel>()
    val state = viewModel.state.collectAsState(initial = RecordingState(false))
    Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(), contentAlignment = Alignment.Center) {
        RecordingButton(recording = state.value.recording, onClick = viewModel::toggleRecording)
    }
}

@Composable
fun RecordingButton(recording: Boolean, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = if (recording) "recording" else "start recording")
    }
}