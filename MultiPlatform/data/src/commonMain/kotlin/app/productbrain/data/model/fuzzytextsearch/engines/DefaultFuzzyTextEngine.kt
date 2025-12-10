package app.productbrain.data.model.fuzzytextsearch.engines

import app.productbrain.data.model.fuzzytextsearch.FuzzyTextEngine
import app.productbrain.data.model.fuzzytextsearch.MatchResult
import app.productbrain.data.model.fuzzytextsearch.levenDistanceMatcher

class DefaultFuzzyTextEngine: FuzzyTextEngine {
    override suspend fun <T> rankSearch(
        a: String,
        b: String,
        provide: T
    ): MatchResult<T> {
        return levenDistanceMatcher(a, b,provide)
    }
}