package eu.wojciechzurek.proofofwork

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigInteger

class MainTest {

    @Test
    fun `do cheap proof of work with sha256`() {

        val targetBits = 20
        val hashLength = 256
        val hashAlg = "SHA-256"

        val target = BigInteger.ONE.shiftLeft(hashLength - targetBits)

        val request = ProofOfWorkRequest(0, "Simple task", target, hashAlg)

        val block = proofOfWork(request)

        assertNotNull(block)
        assertTrue(block!!.hash.startsWith("00000"))
        assertTrue(validateProofOfWork(block))
    }

    @Test
    fun `do proof of work with sha256`() {

        val targetBits = 24
        val hashLength = 256
        val hashAlg = "SHA-256"

        val target = BigInteger.ONE.shiftLeft(hashLength - targetBits)

        val request = ProofOfWorkRequest(0, "Simple task", target, hashAlg)

        val block = proofOfWork(request)

        assertNotNull(block)
        assertTrue(block!!.hash.startsWith("000000"))
        assertTrue(validateProofOfWork(block))
    }

    @Test
    fun `do proof of work with sha512`() {

        val targetBits = 24
        val hashLength = 512
        val hashAlg = "SHA-512"
        val target = BigInteger.ONE.shiftLeft(hashLength - targetBits)

        val request = ProofOfWorkRequest(0, "Simple task", target, hashAlg)

        val block = proofOfWork(request)

        assertNotNull(block)
        assertTrue(block!!.hash.startsWith("000000"))
        assertTrue(validateProofOfWork(block))
    }

    @Test
    fun `validate expensive proof of work with sha256`() {

        val targetBits = 24
        val hashLength = 256
        val hashAlg = "SHA-256"
        val hexHash = "000000896046e0ae3e61ae363be458fbbbaec073ac986355a9b1a191c41e375f"
        val target = BigInteger.ONE.shiftLeft(hashLength - targetBits)
        val nonce = 65_436_628L
        val timestamp = 1_519_583_519_142

        val block = Block(0, "Ala ma kota", target, hashAlg, timestamp, nonce, hexHash)

        assertTrue(validateProofOfWork(block))
    }

    @Test
    fun `validate expensive proof of work with sha512`() {

        val targetBits = 28
        val hashLength = 512
        val hashAlg = "SHA-512"
        val hexHash = "0000000bd0e5c40b9e7cb6ee97696bcf5147d87d204a4a7cf0755a849d0af429d4d9d1853e05b6eb4f65b48ca3614a505d03bc1e71acccc651454d351cf33ce2"
        val target = BigInteger.ONE.shiftLeft(hashLength - targetBits)
        val nonce = 134_328_981L
        val timestamp = 1_519_584_312_401

        val block = Block(0, "Super expensive task with sha512", target, hashAlg, timestamp, nonce, hexHash)

        assertTrue(validateProofOfWork(block))
    }
}