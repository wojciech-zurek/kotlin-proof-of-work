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