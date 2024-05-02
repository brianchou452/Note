package cc.seaotter.note.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cc.seaotter.note.R
import cc.seaotter.note.data.Note


@Composable
fun NoteItem(
    item: Note,
    onDeleteNote : (Note) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row {
                Text(
                    text = item.title,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(8.dp),
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { onDeleteNote(item) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(R.string.delete_button)
                    )
                }
            }
            Text(
                text = item.content,
                maxLines = 4,
                modifier = Modifier
                    .padding(8.dp),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

