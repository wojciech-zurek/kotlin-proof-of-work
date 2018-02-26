package eu.wojciechzurek.proofofwork

import kotlinx.coroutines.experimental.*
import java.math.BigInteger
import kotlin.system.measureTimeMillis

const val TARGET_BITS = 24
const val HASH_LENGTH = 256
const val HASH_ALG = "SHA-256"

fun main(args: Array<String>) {

    val target = BigInteger.ONE.shiftLeft(HASH_LENGTH - TARGET_BITS)

    println("Mining target: $target")
    println("Mining target as hex: ${toHex(target.toByteArray())}")

    val requestList = listOf(
            ProofOfWorkRequest(0, "Simple task", target, HASH_ALG),
            ProofOfWorkRequest(1, "Another task", target, HASH_ALG),
            ProofOfWorkRequest(2, "Ala ma kota", target, HASH_ALG),
            ProofOfWorkRequest(3, "Send 10 EUR to Ala", target, HASH_ALG),
            ProofOfWorkRequest(4, "Cool description 1", target, HASH_ALG),
            ProofOfWorkRequest(5, "Cool description 2", target, HASH_ALG),
            ProofOfWorkRequest(6, "Cool description 3", target, HASH_ALG)
            //expensive
            //,ProofOfWorkRequest(7, "Super expensive task with sha512", BigInteger.ONE.shiftLeft(512 - 28), "SHA-512")
    )

    runBlocking {
        val time = measureTimeMillis {
            println("Mining hard for proof of work...")

            /**
             * other async alternatives:
             * 1. async {} <-- with DefaultDispatcher && CommonPool
             * 2. async(CommonPool) {} <-- same as above
             * 3. executor = Executors.newFixedThreadPool(NUM_OF_THREAD)
             *              or Executors.newWorkStealingPool(PARALLELISM_LEVEL)
             *              or other executor
             *    async(executor.asCoroutineDispatcher() {}
             *    ...
             *    executor.shutdown() <-- remember to shutdown executor
             *    while (!executor.isTerminated){ }
             */
            requestList
                    .map { async { proofOfWork(it) } }
                    .map { it.await() }
                    .filter { it != null }
                    .filter { validateProofOfWork(it!!) }
                    .forEach(::println)

            println("\nFinished all threads")
        }
        println("Completed in ${time / 1000f} s")
    }
}