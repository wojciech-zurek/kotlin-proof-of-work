package eu.wojciechzurek.proofofwork

import java.math.BigInteger
import java.nio.ByteBuffer
import java.time.Instant

data class ProofOfWorkRequest(val id: Int,
                              val data: String,
                              val target: BigInteger,
                              val hashAlg: String,
                              val timestamp: Long = Instant.now().toEpochMilli()
) : ByteArrayList {

    override fun toByteArrayList() = mutableListOf(
            ByteBuffer.allocate(4).putInt(id).array(),
            data.toByteArray(),
            target.toByteArray(),
            hashAlg.toByteArray(),
            ByteBuffer.allocate(8).putLong(timestamp).array(),
            ByteBuffer.allocate(8).putLong(0).array()//for nonce
    )

    fun toBlock(nonce: Long, hash: String) = Block(id, data, target, hashAlg, timestamp, nonce, hash)
}