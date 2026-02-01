package app.productbrain.data.model.vendor

import app.productbrain.common.Maybe
import app.productbrain.data.provider.TransactionProvider

interface VendorRepository {
    suspend fun upsert(vendor: RemoteVendor): Maybe<Unit>
    suspend fun queryAll(): Maybe<VendorList>
}

class VendorRepositoryActual(
    private val vendorDao: VendorDao,
    private val transactionProvider: TransactionProvider
): VendorRepository {
    override suspend fun upsert(vendor: RemoteVendor) = transactionProvider.tryTransaction {
        val entity = VendorMapper.toRemoteVendorEntity(vendor)
        val alias = VendorMapper.toRemoteVendorAliasEntityList(vendor)

        vendorDao.upsert(entity)
        alias.forEach {
            vendorDao.upsert(it)
        }
    }

    override suspend fun queryAll() = Maybe.tryMaybe {
        val vendors = vendorDao.getAll()
        val alias = vendorDao.getAllAlias().groupBy { it.vendorId }

        val mapped= vendors.map { vendor ->
            val vendorAlias = alias.getOrElse(vendor.id, { listOf() }).map { it.alias }
            VendorMapper.toRemoteVendorModel(vendor, vendorAlias)
        }

        VendorList(mapped)
    }
}