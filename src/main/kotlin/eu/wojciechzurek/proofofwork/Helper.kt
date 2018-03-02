package eu.wojciechzurek.proofofwork

const val HEX_CHARS = "0123456789abcdef"

fun toHex(input: ByteArray): String {

    val result = StringBuilder(input.size * 2)
    input.forEach {
        val i = it.toInt()
        result.append(HEX_CHARS[i shr 4 and 0x0f])
        result.append(HEX_CHARS[i and 0x0f])
    }

    return result.toString()
}

fun progress(proofOfWorkRequest: ProofOfWorkRequest, nonce: Long, start: Long): String {
    val stop = System.currentTimeMillis()
    val seconds = (stop - start) / 1000f
    val thread = Thread.currentThread()
    return info().format(thread.name, proofOfWorkRequest.id, proofOfWorkRequest.hashAlg, nonce, nonce / seconds)
}

fun info() = "[%s], req id: [%d], hash alg: [%s], nonce: [%d], bench: [%s hash/s]"