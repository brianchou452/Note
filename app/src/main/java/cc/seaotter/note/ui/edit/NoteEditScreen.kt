package cc.seaotter.note.ui.edit

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cc.seaotter.note.NoteTopAppBar
import cc.seaotter.note.R
import cc.seaotter.note.ui.AppViewModelProvider
import cc.seaotter.note.ui.navigation.NavigationDestination

object NoteEditDestination : NavigationDestination {
    override val route = "NoteEdit"
    override val titleRes = R.string.note_edit_title
    const val noteIdArg = "noteId"
    val routeWithArgs = "$route/{$noteIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NoteEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    Scaffold(
        topBar = {
            NoteTopAppBar(
                title = stringResource(NoteEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding(),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                )
                .verticalScroll(rememberScrollState())
        ) {
            TextField(
                value = viewModel.editUiState.note.title,
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineMedium,
                onValueChange = {
                    viewModel.updateTitle(it)
                    viewModel.updateNote()
                },
                placeholder = { Text(stringResource(id = R.string.title_label)) },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .border(width = 0.dp, color = MaterialTheme.colorScheme.surface)
            )

            TextField(
                value = viewModel.editUiState.note.content,
                textStyle = MaterialTheme.typography.bodyLarge,
                onValueChange = {
                    viewModel.updateContent(it)
                    viewModel.updateNote()
                },
                placeholder = { Text(stringResource(id = R.string.content_label)) },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .border(width = 0.dp, color = MaterialTheme.colorScheme.surface)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteEditScreenPreview() {
    NoteEditScreen(navigateBack = {})
}