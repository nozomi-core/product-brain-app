package app.productbrain.data.lang

import kotlin.jvm.JvmInline
import kotlin.random.Random
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

object UlidFactory {
    private const val ALPHABET = "0123456789ABCDEFGHJKMNPQRSTVWXYZ"

    // Not thread-safe (commonMain cannot provide locks)
    private var lastTimestamp: Long = -1L
    private val lastRandom = ByteArray(10)

    private val rnd = Random.Default

    @OptIn(ExperimentalTime::class)
    fun create(): Ulid {
        val now = Clock.System.now().toEpochMilliseconds()

        val ts: Long
        val randomBytes: ByteArray

        if (now > lastTimestamp) {
            // New millisecond → fresh random
            random10Into(lastRandom)
            lastTimestamp = now
            ts = now
            randomBytes = lastRandom.copyOf()
        } else {
            // Same millisecond → monotonic increment
            incrementRandom(lastRandom)
            ts = lastTimestamp
            randomBytes = lastRandom.copyOf()
        }

        return encodeUlid(ts, randomBytes)
    }

    private fun random10Into(bytes: ByteArray) {
        for (i in bytes.indices) {
            bytes[i] = rnd.nextInt(0, 256).toByte()
        }
    }

    private fun incrementRandom(bytes: ByteArray) {
        for (i in bytes.size - 1 downTo 0) {
            val v = bytes[i].toInt() and 0xFF
            if (v == 0xFF) {
                bytes[i] = 0
            } else {
                bytes[i] = (v + 1).toByte()
                return
            }
        }
    }

    private fun encodeUlid(timestamp: Long, rnd: ByteArray): Ulid {
        val out = CharArray(26)

        // Encode 48-bit timestamp → 10 chars
        var t = timestamp
        for (i in 9 downTo 0) {
            out[i] = ALPHABET[(t and 31).toInt()]
            t = t ushr 5
        }

        // Encode 80-bit randomness → 16 chars
        var buffer = 0L
        var bits = 0
        var idx = 10

        for (b in rnd) {
            buffer = (buffer shl 8) or (b.toInt() and 0xFF).toLong()
            bits += 8

            while (bits >= 5) {
                bits -= 5
                out[idx++] = ALPHABET[((buffer ushr bits) and 31).toInt()]
            }
        }

        return Ulid(out.concatToString())
    }
}

@JvmInline
value class Ulid(val value: String)