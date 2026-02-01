package app.productbrain.data.model.fuzzytextsearch

import app.productbrain.data.model.settings.SettingItem
import app.productbrain.data.model.settings.SettingsRepository

class FuzzyTextSearchEngine(
    private val settingsRepository: SettingsRepository
) {
    suspend fun <T> prepare(
        items: Iterable<T>,
        textSelector: (T) -> List<String>
    ): PreparedFuzzySearch<T> {

        val language = settingsRepository.get(SettingItem.LanguageCode)
        val matchEngine = FuzzyTextEngine.create(language)

        val entries = items.map { nextItem ->
            val searchFields = textSelector(nextItem)

            Pair(nextItem, searchFields)
        }
        return PreparedFuzzySearch(matchEngine, entries)
    }
}

class PreparedFuzzySearch<T>(
    private val engine: FuzzyTextEngine,
    private val entries: List<Pair<T, List<String>>>
) {
    suspend fun search(text: String): List<MatchResult<T>> {
        return buildList {
            entries.forEach { pair ->
                pair.second.forEach { alias ->
                    val result = engine.rankSearch(alias, text, pair.first)
                    add(result)
                }
            }
        }.sortedByDescending { it.rank }
    }
}