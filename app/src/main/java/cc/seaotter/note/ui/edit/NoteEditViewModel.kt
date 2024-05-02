package cc.seaotter.note.ui.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cc.seaotter.note.data.Note
import cc.seaotter.note.data.NotesRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


/**
 * ViewModel to retrieve and update an item from the [NotesRepository]'s data source.
 */
class NoteEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val notesRepository: NotesRepository
) : ViewModel() {


    /**
     * Holds current item ui state
     */
    var editUiState by mutableStateOf(EditUiState())

    private val noteId: Long = checkNotNull(savedStateHandle[NoteEditDestination.noteIdArg])

    init {
        if (noteId != (-1).toLong()) {
            loadNote()
        } else {
            createNote()
        }
    }

    private fun loadNote() {
        viewModelScope.launch {
            editUiState = editUiState.copy(
                note = notesRepository.getNoteStream(noteId)
                    .filterNotNull()
                    .first()
            )
        }
    }

    private fun createNote() {
        viewModelScope.launch {
            editUiState = editUiState.copy(
                note = editUiState.note.copy(
                    id = notesRepository.insertNote(editUiState.note)
                )
            )
        }
    }

    fun updateTitle(title: String) {
        editUiState = editUiState.copy(
            note = editUiState.note.copy(title = title)
        )
    }

    fun updateContent(content: String) {
        editUiState = editUiState.copy(
            note = editUiState.note.copy(content = content)
        )
    }

    fun updateNote() {
        viewModelScope.launch {
            notesRepository.updateNote(editUiState.note)
        }
    }


}

/**
 * Ui State for EditScreen
 */
data class EditUiState(val note: Note = Note())
