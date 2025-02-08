package com.ema.my_project_crud.record.domain.UseCase

import android.content.Intent
import android.speech.RecognizerIntent
import java.util.*

class SpeechUseCase {
    fun createSpeech(): Intent {
        return Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Di el nombre de la película...")
        }
    }
}