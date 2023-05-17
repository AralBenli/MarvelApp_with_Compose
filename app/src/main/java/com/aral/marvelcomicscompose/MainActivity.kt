package com.aral.marvelcomicscompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aral.marvelcomicscompose.ui.theme.MarvelComicsComposeTheme
import com.aral.marvelcomicscompose.view.CharacterDetailScreen
import com.aral.marvelcomicscompose.view.CharactersBottomNav
import com.aral.marvelcomicscompose.view.CollectionScreen
import com.aral.marvelcomicscompose.view.LibraryScreen
import com.aral.marvelcomicscompose.viewmodel.CollectionDbViewModel
import com.aral.marvelcomicscompose.viewmodel.LibraryViewModel
import dagger.hilt.android.AndroidEntryPoint


sealed class Destination(val route: String) {
    object Library : Destination("library")
    object Collection : Destination("collection")
    object CharacterDetail : Destination("character/{characterId}") {
        fun createRoute(characterId: Int?) = "character/$characterId"
    }
}


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val lvm by viewModels<LibraryViewModel>()
    private val cvm by viewModels<CollectionDbViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelComicsComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    CharactersScaffold(navController = navController, lvm , cvm)
                }
            }
        }
    }
}

@Composable
fun CharactersScaffold(
    navController: NavHostController,
    lvm: LibraryViewModel,
    cvm : CollectionDbViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            CharactersBottomNav(navController = navController)
        }
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = Destination.Library.route) {
            composable(Destination.Library.route) {
                LibraryScreen(navController, lvm, paddingValues)
            }
            composable(Destination.Collection.route) {
                CollectionScreen(cvm , navController)
            }
            composable(Destination.CharacterDetail.route) { navBackStackEntry ->
                val id = navBackStackEntry.arguments?.getString("characterId")?.toIntOrNull()
                if (id == null)
                    Toast.makeText(context, "Character id is required", Toast.LENGTH_SHORT).show()
                else {
                    lvm.retrieveSingleCharacter(id)
                    CharacterDetailScreen(
                        lvm = lvm,
                        paddingValues = paddingValues,
                        navController = navController,
                        cvm = cvm
                    )
                }
            }
        }
    }
}


