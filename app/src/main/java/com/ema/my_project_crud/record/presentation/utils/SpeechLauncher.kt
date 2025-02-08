package com.ema.my_project_crud.record.presentation.utils

import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.ema.my_project_crud.record.domain.UseCase.SpeechUseCase
import kotlinx.coroutines.launch

@Composable
fun SpeechLaunher(onResult: (String) -> Unit): () -> Unit {
    val scope = rememberCoroutineScope()
    val contract = remember { ActivityResultContracts.StartActivityForResult() }

    val laucher = rememberLauncherForActivityResult(contract) { result: ActivityResult ->
        val data = result.data
        val matches = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
        matches?.firstOrNull()?.let {text ->
            scope.launch {
                onResult(text)
            }
        }
    }

    val speechUseCase = SpeechUseCase()

    return {
        val speechIntent = speechUseCase.createSpeech()
        laucher.launch(speechIntent)
    }



}