package app.productbrain.data.model.fuzzytextsearch

import app.productbrain.extensions.normalise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

suspend fun <T> levenDistanceMatcher(
    a: String,
    b: String,
    value: T
): MatchResult<T>
{
    return withContext(Dispatchers.IO) {
        val compareA = a.lowercase().normalise()
        val compareB = b.lowercase().normalise()

        val levelDist = levenshtein(compareA, compareB)

        val autoMatch = if(compareA == compareB) {
            true
        } else if (minOf(compareA.length, compareB.length) >= 4) {
            levelDist <= 2 && compareB[0] == compareA[0]
        } else {
            false
        }

        MatchResult(
            value,
            rank = levelDist.toDouble(),
            isAutoMatchQualified = autoMatch
        )
    }
}


fun levenshtein(s1: String, s2: String): Int {
    val len1 = s1.length
    val len2 = s2.length

    // Early return if one string is empty
    if (len1 == 0) return len2
    if (len2 == 0) return len1

    // Create distance matrix
    val dp = Array(len1 + 1) { IntArray(len2 + 1) }

    // Initialize first row and column
    for (i in 0..len1) dp[i][0] = i
    for (j in 0..len2) dp[0][j] = j

    // Compute distances
    for (i in 1..len1) {
        for (j in 1..len2) {
            val cost = if (s1[i - 1] == s2[j - 1]) 0 else 1
            dp[i][j] = minOf(
                dp[i - 1][j] + 1,      // deletion
                dp[i][j - 1] + 1,      // insertion
                dp[i - 1][j - 1] + cost // substitution
            )
        }
    }

    return dp[len1][len2]
}