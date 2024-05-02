package cc.seaotter.note.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import cc.seaotter.note.NoteApplication
import cc.seaotter.note.ui.edit.NoteEditViewModel
import cc.seaotter.note.ui.home.HomeViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Note app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            HomeViewModel(noteApplication().container.notesRepository)
        }

        initializer {
            NoteEditViewModel(
                this.createSavedStateHandle(),
                noteApplication().container.notesRepository
            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [NoteApplication].
 */
fun CreationExtras.noteApplication(): NoteApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NoteApplication)