package app.productbrain.data.model.fuzzytextsearch

import app.productbrain.common.LanguageCodeTag
import app.productbrain.data.model.fuzzytextsearch.engines.DefaultFuzzyTextEngine
import app.productbrain.data.model.fuzzytextsearch.engines.EnFuzzyTextEngine

interface FuzzyTextEngine {
    suspend fun <T> rankSearch(
        a: String,
        b: String,
        provide: T
    ): MatchResult<T>

    companion object {
        fun create(languageCodeTag: LanguageCodeTag): FuzzyTextEngine {
            return when(languageCodeTag) {
                LanguageCodeTag.EN -> EnFuzzyTextEngine()
                else -> DefaultFuzzyTextEngine()
            }
        }
    }
}

data class MatchResult<T>(
    val matched: T,
    val rank: Double,
    val isAutoMatchQualified: Boolean
)

