# Proof of Work with Kotlin/Coroutines and JUnit5
Simple Proof of Work with Kotlin/Coroutines.</br>This is **not** blockchain.</br>Each block is independent.

## Status

[![Build Status](https://travis-ci.org/wojciech-zurek/kotlin-proof-of-work.svg?branch=master)](https://travis-ci.org/wojciech-zurek/kotlin-proof-of-work)

## Download

```bash
    git clone https://github.com/wojciech-zurek/kotlin-proof-of-work.git
```

## Run with gradle

```bash
    cd kotlin-proof-of-work/
    ./gradlew run
```

## Run as jar file

```bash
    cd kotlin-proof-of-work/
    ./gradlew fatJar
    java -jar build/libs/kotlin-proof-of-work-1.0-SNAPSHOT.jar
```

## Sample output

```
Available processors: [4]
Mining hard for proof of work...
Mining target: 6901746346790563787434755862277025452451108972170386555162524223799296
Mining target as hex: 010000000000000000000000000000000000000000000000000000000000

Found proof! [ForkJoinPool.commonPool-worker-1], req id: [0], hash alg: [SHA-256], nonce: [3576671], bench: [589625.94 hash/s]
Found proof! [ForkJoinPool.commonPool-worker-1], req id: [3], hash alg: [SHA-256], nonce: [4166384], bench: [666408.2 hash/s]
Found proof! [ForkJoinPool.commonPool-worker-3], req id: [2], hash alg: [SHA-256], nonce: [11325580], bench: [630565.1 hash/s]
Found proof! [ForkJoinPool.commonPool-worker-2], req id: [1], hash alg: [SHA-256], nonce: [12904672], bench: [690680.4 hash/s]
Found proof! [ForkJoinPool.commonPool-worker-2], req id: [6], hash alg: [SHA-256], nonce: [6953359], bench: [681167.6 hash/s]
Found proof! [ForkJoinPool.commonPool-worker-3], req id: [5], hash alg: [SHA-256], nonce: [11827992], bench: [714337.0 hash/s]
Found proof! [ForkJoinPool.commonPool-worker-1], req id: [4], hash alg: [SHA-256], nonce: [26193376], bench: [792633.75 hash/s]
Block(id=0, data=Simple task, target=6901746346790563787434755862277025452451108972170386555162524223799296, hashAlg=SHA-256, timestamp=1520015264459, nonce=3576671, hash=000000fc5438ecb6030e22653db58e8cbe8721467fef1466ad6cc7ee66f30fac)
Block(id=1, data=Another task, target=6901746346790563787434755862277025452451108972170386555162524223799296, hashAlg=SHA-256, timestamp=1520015264459, nonce=12904672, hash=000000e703efc25d044ae82769e87f5daa65ce6e43f39acbbaef35b846e78dfb)
Block(id=2, data=Ala ma kota, target=6901746346790563787434755862277025452451108972170386555162524223799296, hashAlg=SHA-256, timestamp=1520015264459, nonce=11325580, hash=000000632ee4dc3170246f3883787a7da634d9f65df35f6e91384a18308599c6)
Block(id=3, data=Send 10 EUR to Ala, target=6901746346790563787434755862277025452451108972170386555162524223799296, hashAlg=SHA-256, timestamp=1520015264459, nonce=4166384, hash=0000006ad4310ffc6ecd6280de6fa609e158097aebcc7c60c4dc83e103643d15)
Block(id=4, data=Cool description 1, target=6901746346790563787434755862277025452451108972170386555162524223799296, hashAlg=SHA-256, timestamp=1520015264459, nonce=26193376, hash=000000aa822aeac2c81be15f637442112b1c2eeb126a5a8b2e62f11f83dd1200)
Block(id=5, data=Cool description 2, target=6901746346790563787434755862277025452451108972170386555162524223799296, hashAlg=SHA-256, timestamp=1520015264459, nonce=11827992, hash=0000001fbf3b2f27e22874b103db56b8c0de53d00e3537d04f8b650393e86c08)
Block(id=6, data=Cool description 3, target=6901746346790563787434755862277025452451108972170386555162524223799296, hashAlg=SHA-256, timestamp=1520015264459, nonce=6953359, hash=000000627fda08a32fffa826d04a7ef67ef30955590acba89e1bba5ed4821e25)

Finished all threads
Completed in 45.399 s
```

## Test

```bash
    cd kotlin-proof-of-work/
    ./gradlew clean test
```

## Coroutines

Other async alternatives (Main.kt):
```
    1. async {} <-- with DefaultDispatcher and CommonPool
    2. async(CommonPool) {} <-- same as above
    3. val executor =  Executors.newFixedThreadPool(NUM_OF_THREAD)
                or Executors.newWorkStealingPool(PARALLELISM_LEVEL)
                or other executor
        async(executor.asCoroutineDispatcher() {}
        ...
        executor.shutdown() <-- remember to shutdown executor
        while (!executor.isTerminated){ }