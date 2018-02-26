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
Mining target: 6901746346790563787434755862277025452451108972170386555162524223799296
Mining target as hex: 010000000000000000000000000000000000000000000000000000000000
Mining hard for proof of work...
Found! Thread id: [10][ForkJoinPool.commonPool-worker-2] block id: [1] hash alg: [SHA-256] nonce: [166324]
Found! Thread id: [9][ForkJoinPool.commonPool-worker-1] block id: [0] hash alg: [SHA-256] nonce: [7066601]
Found! Thread id: [9][ForkJoinPool.commonPool-worker-1] block id: [4] hash alg: [SHA-256] nonce: [1678370]
Found! Thread id: [9][ForkJoinPool.commonPool-worker-1] block id: [5] hash alg: [SHA-256] nonce: [9248769]
Found! Thread id: [10][ForkJoinPool.commonPool-worker-2] block id: [3] hash alg: [SHA-256] nonce: [20766030]
Found! Thread id: [9][ForkJoinPool.commonPool-worker-1] block id: [6] hash alg: [SHA-256] nonce: [6635676]
Found! Thread id: [11][ForkJoinPool.commonPool-worker-3] block id: [2] hash alg: [SHA-256] nonce: [64590306]
Block(id=0, data=Simple task, target=6901746346790563787434755862277025452451108972170386555162524223799296, hashAlg=SHA-256, timestamp=1519664335186, nonce=7066601, hash=000000ce0ac255283dfb4d05cae8ccc096cb742d66f905dbe3bf4c32a1f29321)
Block(id=1, data=Another task, target=6901746346790563787434755862277025452451108972170386555162524223799296, hashAlg=SHA-256, timestamp=1519664335186, nonce=166324, hash=000000d165c0e619ec9405cb8516d9712752f1ba7718f44fcb9432966e28c5ab)
Block(id=2, data=Ala ma kota, target=6901746346790563787434755862277025452451108972170386555162524223799296, hashAlg=SHA-256, timestamp=1519664335186, nonce=64590306, hash=00000045220ab498ca48e1483d9635296010fcfd0b76ddbabe8c994449d544ce)
Block(id=3, data=Send 10 EUR to Ala, target=6901746346790563787434755862277025452451108972170386555162524223799296, hashAlg=SHA-256, timestamp=1519664335186, nonce=20766030, hash=0000001f69f302d5aa60afb888772a7bdb9f6fd232df81dc4803fff53eb1b9e8)
Block(id=4, data=Cool description 1, target=6901746346790563787434755862277025452451108972170386555162524223799296, hashAlg=SHA-256, timestamp=1519664335186, nonce=1678370, hash=000000fffd78426eaf4488b07e00b5802c6033c4e1e07edb416af48ac7079b4f)
Block(id=5, data=Cool description 2, target=6901746346790563787434755862277025452451108972170386555162524223799296, hashAlg=SHA-256, timestamp=1519664335186, nonce=9248769, hash=000000c3647a33d78c1555169ed4f00e2d332903f4a6cd8223f0e394c0d6cdc2)
Block(id=6, data=Cool description 3, target=6901746346790563787434755862277025452451108972170386555162524223799296, hashAlg=SHA-256, timestamp=1519664335186, nonce=6635676, hash=0000002cf19e683d582ff7647faa2bb87b3ae3d31a1a4122cabca9f66001d771)

Finished all threads
Completed in 79.149 s
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