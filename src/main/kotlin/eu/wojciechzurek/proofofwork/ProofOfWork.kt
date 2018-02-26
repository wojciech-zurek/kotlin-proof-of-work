package eu.wojciechzurek.proofofwork

import java.math.BigInteger

fun proofOfWork(proofOfWorkRequest: ProofOfWorkRequest): Block? {
    val thread = Thread.currentThread()

    val input = proofOfWorkRequest.toByteArrayList()

    for (nonce in 0..Long.MAX_VALUE) {
        val result = hash(proofOfWorkRequest.hashAlg, input, nonce)

        if (nonce.rem(100000) == 0L) {
            print("\rMining... Thread id: [${thread.id}] block id: [${proofOfWorkRequest.id}] hash alg: [${proofOfWorkRequest.hashAlg}] nonce: [$nonce]")
        }

        if (BigInteger(1, result).compareTo(proofOfWorkRequest.target) == -1) {
            println("\rFound! Thread id: [${thread.id}][${thread.name}] block id: [${proofOfWorkRequest.id}] hash alg: [${proofOfWorkRequest.hashAlg}] nonce: [$nonce]")
            return proofOfWorkRequest.toBlock(nonce, toHex(result))
        }
    }

    return null
}

fun validateProofOfWork(block: Block): Boolean = toHex(hash(block)) == block.hash