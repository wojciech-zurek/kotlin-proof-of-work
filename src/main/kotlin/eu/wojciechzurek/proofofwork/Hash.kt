package eu.wojciechzurek.proofofwork

import java.nio.ByteBuffer
import java.security.MessageDigest

fun hash(block: Block): ByteArray = hash(block.hashAlg, block.toByteArrayList())

fun hash(hashAlg: String, input: MutableList<ByteArray>, nonce: Long): ByteArray{
    input[5] = ByteBuffer.allocate(8).putLong(nonce).array()
    return hash(hashAlg, input)
}

fun hash(hashAlg: String, input: List<ByteArray>): ByteArray {

    val digest = MessageDigest.getInstance(hashAlg)
    input.forEach(digest::update)

    return digest.digest()
}