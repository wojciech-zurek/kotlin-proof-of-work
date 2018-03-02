package eu.wojciechzurek.proofofwork

import kotlinx.coroutines.experimental.*
import java.math.BigInteger
import kotlin.system.measureTimeMillis

const val TARGET_BITS = 24
const val HASH_LENGTH = 256
const val HASH_ALG = "SHA-256"

fun main(args: Array<String>) {

    println("Available processors: [${Runtime.getRuntime().availableProcessors()}]")

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
            requests()
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

fun requests(): List<ProofOfWorkRequest> {
    val target = BigInteger.ONE.shiftLeft(HASH_LENGTH - TARGET_BITS)

    println("Mining target: $target")
    println("Mining target as hex: ${toHex(target.toByteArray())}")
    println()

    return listOf(
            "Simple task",
            "Another task",
            "Ala ma kota",
            "Send 10 EUR to Ala",
            "Cool description 1",
            "Cool description 2",
            "Cool description 3"
    )
            .mapIndexed { index, value ->
                ProofOfWorkRequest(index, value, target, HASH_ALG) //more expensive ProofOfWorkRequest(index, value, BigInteger.ONE.shiftLeft(512 - 28), "SHA-512")
            }
}