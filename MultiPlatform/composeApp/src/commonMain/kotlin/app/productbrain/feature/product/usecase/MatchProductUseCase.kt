package app.productbrain.feature.product.usecase

import app.productbrain.data.model.fuzzytextsearch.FuzzyTextSearchEngine
import app.productbrain.data.model.productvariant.ProductVariant

class MatchProductUseCase(
    private val fuzzyTextSearchEngine: FuzzyTextSearchEngine
) {
    suspend operator fun invoke(
        productText: String,
        productList: List<ProductVariant>
    ): ProductVariant? {
        return fuzzyTextSearchEngine.prepare(
            productList, { variant ->
                buildList {
                    add(variant.name)
                }
            }
        ).search(productText).firstOrNull { it.isAutoMatchQualified }?.matched
    }
}