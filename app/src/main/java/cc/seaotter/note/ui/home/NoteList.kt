package cc.seaotter.note.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cc.seaotter.note.data.Note

@Composable
fun NoteList(
    itemList: List<Note>,
    onItemClick: (Note) -> Unit,
    viewModel: HomeViewModel,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = itemList, key = { it.id }) { item ->
            NoteItem(
                item = item,
                onDeleteNote = { viewModel.deleteNote(item) },
                modifier = Modifier
                    .clickable { onItemClick(item) }
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}