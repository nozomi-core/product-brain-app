package app.productbrain.data.model.vendor

import app.productbrain.data.lang.Maybe

interface VendorRepository {
    suspend fun upsert(vendor: Vendor): Maybe<Unit>
}

class VendorRepositoryActual(
    private val vendorDao: VendorDao
): VendorRepository {
    override suspend fun upsert(vendor: Vendor) = Maybe.tryResult {
        val entity = vendor.toEntity()
        vendorDao.upsert(entity)
    }
}