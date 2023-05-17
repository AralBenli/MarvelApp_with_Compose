package com.aral.marvelcomicscompose.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aral.marvelcomicscompose.CharacterImage
import com.aral.marvelcomicscompose.viewmodel.CollectionDbViewModel

/**
 * Created by AralBenli on 11.05.2023.
 */

@Composable
fun CollectionScreen(
    cvm: CollectionDbViewModel,
    navController: NavHostController
) {
    val charactersInCollection = cvm.collection.collectAsState()
    val expandedElement = remember { mutableStateOf(-1) }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(charactersInCollection.value) { character ->
            Column {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(4.dp)
                    .clickable {
                        if (expandedElement.value == character.id) {
                            expandedElement.value = -1
                        } else {
                            expandedElement.value = character.id
                        }
                    }) {
                    character.thumbnail?.let {
                        CharacterImage(
                            url = it,
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxHeight(),
                            contentScale = ContentScale.FillHeight
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp)
                                .fillMaxHeight()
                        ) {
                            Text(
                                text = character.name ?: "No Name",
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp,
                                maxLines = 2
                            )
                            Text(text = character.comics ?: "", fontStyle = FontStyle.Italic)
                        }

                        Column(
                            modifier = Modifier
                                .wrapContentWidth()
                                .fillMaxHeight()
                                .padding(4.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(Icons.Outlined.Delete, contentDescription = null,
                                modifier = Modifier.clickable {
                                    cvm.deleteCharacter(character)
                                })
                            if (character.id == expandedElement.value)
                                Icon(Icons.Outlined.KeyboardArrowUp, null)
                            else
                                Icon(Icons.Outlined.KeyboardArrowDown, null)
                        }
                    }
                }
            }

            Divider(
                color = Color.LightGray,
                modifier = Modifier.padding(
                    top = 4.dp, bottom = 4.dp, start = 20.dp, end = 20.dp
                )
            )
        }
    }
}