package eu.wojciechzurek.proofofwork

import java.math.BigInteger

fun proofOfWork(proofOfWorkRequest: ProofOfWorkRequest): Block? {

    val input = proofOfWorkRequest.toByteArrayList()

    val start = System.currentTimeMillis()
    for (nonce in 0..Long.MAX_VALUE) {
        val result = hash(proofOfWorkRequest.hashAlg, input, nonce)

        if (nonce.rem(100000) == 0L) {
            print("\rMining... ${progress(proofOfWorkRequest, nonce, start)}")
        }

        if (BigInteger(1, result).compareTo(proofOfWorkRequest.target) == -1) {
            println("\rFound proof! ${progress(proofOfWorkRequest, nonce, start)}")
            return proofOfWorkRequest.toBlock(nonce, toHex(result))
        }
    }

    return null
}

fun validateProofOfWork(block: Block): Boolean = toHex(hash(block)) == block.hash