package com.aral.marvelcomicscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aral.marvelcomicscompose.ui.theme.MarvelComicsComposeTheme
import com.aral.marvelcomicscompose.view.CharacterDetailScreen
import com.aral.marvelcomicscompose.view.CharactersBottomNav
import com.aral.marvelcomicscompose.view.CollectionScreen
import com.aral.marvelcomicscompose.view.LibraryScreen


sealed class Destination(val route : String){
    object Library: Destination("library")
    object Collection : Destination("collection")
    object CharacterDetail: Destination("character/{characterId}"){
        fun createRoute(characterId : Int?)="character/{$characterId}"
    }
}


class MainActivity : ComponentActivity() {
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
                    CharactersScaffold(navController = navController)
                }
            }
        }
    }
}

@Composable
fun CharactersScaffold(navController: NavHostController) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
                CharactersBottomNav(navController = navController)
        }
    ){ paddingValues ->
        NavHost(navController = navController, startDestination = Destination.Library.route ){
            composable(Destination.Library.route){
                LibraryScreen()
            }
            composable(Destination.Collection.route){
                CollectionScreen()
            }
            composable(Destination.CharacterDetail.route){ navBackStackEntry ->
                CharacterDetailScreen()
            }
        }

    }

}


