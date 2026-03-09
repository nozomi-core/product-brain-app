package app.productbrain.feature.vendor.usecase

import app.productbrain.data.model.fuzzytextsearch.FuzzyTextSearchEngine
import app.productbrain.data.model.vendor.Vendor
import app.productbrain.data.model.vendor.VendorList

class MatchVendorUseCase(
    private val fuzzyTextSearchEngine: FuzzyTextSearchEngine
){
    suspend operator fun invoke(
        vendorText: String,
        vendorList: VendorList
    ): Vendor? {
        return fuzzyTextSearchEngine.prepare(
            vendorList.vendors,
            { vendors ->
                buildList {
                    add(vendors.name)
                    vendors.alias.forEach {
                        add(it)
                    }
                }
            }
        ).search(vendorText).firstOrNull { it.isAutoMatchQualified }?.matched
    }
}