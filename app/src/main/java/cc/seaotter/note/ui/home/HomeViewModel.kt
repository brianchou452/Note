package cc.seaotter.note.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cc.seaotter.note.data.Note
import cc.seaotter.note.data.NotesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class HomeViewModel(
    private val notesRepository: NotesRepository
) : ViewModel() {


    val homeUiState: StateFlow<HomeUiState> =
        notesRepository.getAllNotesStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            notesRepository.deleteNote(note)
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * Ui State for HomeScreen
 */
data class HomeUiState(val noteList: List<Note> = listOf())