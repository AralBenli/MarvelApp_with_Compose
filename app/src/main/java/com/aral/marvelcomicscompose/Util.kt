package com.aral.marvelcomicscompose

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
