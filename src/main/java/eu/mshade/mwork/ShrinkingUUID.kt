package eu.mshade.mwork

import java.security.SecureRandom

object ShrinkingUUID {

    private val SECURE_RANDOM = SecureRandom()

    @JvmOverloads
    fun randomUUID(size: Int = 8): String {
        val bytes = ByteArray(size)
        SECURE_RANDOM.nextBytes(bytes)
        val stringBuilder = StringBuilder()
        for (b in bytes) {
            stringBuilder.append((b.toInt() and 0xFF).toString(16))
        }
        return stringBuilder.toString()
    }
}