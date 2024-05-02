package cc.seaotter.note.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import cc.seaotter.note.ui.edit.NoteEditDestination
import cc.seaotter.note.ui.edit.NoteEditScreen
import cc.seaotter.note.ui.home.HomeDestination
import cc.seaotter.note.ui.home.HomeScreen

@Composable
fun NoteNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToEditNote = {
                    navController.navigate("${NoteEditDestination.route}/${it}")
                }
            )
        }
        composable(
            route = NoteEditDestination.routeWithArgs,
            arguments = listOf(navArgument(NoteEditDestination.noteIdArg) {
                type = NavType.IntType
            })
        ) {
            NoteEditScreen(
                navigateBack = { navController.navigateUp() }
            )
        }
    }
}
