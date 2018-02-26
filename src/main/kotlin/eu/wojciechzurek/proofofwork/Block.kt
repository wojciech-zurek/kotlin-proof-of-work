package eu.wojciechzurek.proofofwork

import java.math.BigInteger
import java.nio.ByteBuffer

data class Block(
        val id: Int,
        val data: String,
        val target: BigInteger,
        val hashAlg: String,
        val timestamp: Long,
        val nonce: Long,
        val hash: String
) : ByteArrayList {

    override fun toByteArrayList() = listOf(
            ByteBuffer.allocate(4).putInt(id).array(),
            data.toByteArray(),
            target.toByteArray(),
            hashAlg.toByteArray(),
            ByteBuffer.allocate(8).putLong(timestamp).array(),
            ByteBuffer.allocate(8).putLong(nonce).array()
    )
}