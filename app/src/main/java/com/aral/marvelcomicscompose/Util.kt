package com.aral.marvelcomicscompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Created by AralBenli on 12.05.2023.
 */

fun getHash(timestamp: String, privateKey: String, publicKey: String): String {
    val hashStr = timestamp + privateKey + publicKey
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(hashStr.toByteArray())).toString(16).padStart(32, '0')
}

@Composable
fun AttributionText(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .padding(start = 8.dp, top = 4.dp),
        fontSize = 12.sp
    )
}

@Composable
fun CharacterImage(
    url: String,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.FillWidth
) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier,
        contentScale = contentScale
    )
}

fun List<String>.comicsToString() = this.joinToString(separator = ", ")

