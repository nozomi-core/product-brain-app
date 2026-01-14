package app.productbrain.feature.startup.usecase

import app.productbrain.common.Maybe
import app.productbrain.data.model.productabstract.ProductAbstractRepository
import app.productbrain.data.model.productunit.ProductUnitRepository
import app.productbrain.data.provider.TransactionProvider

class InitDatabaseUseCase(
    private val transactionProvider: TransactionProvider,
    private val productUnitRepository: ProductUnitRepository,
    private val productAbstractRepository: ProductAbstractRepository
) {
    suspend operator fun invoke(): Maybe<Initialised> = transactionProvider.tryTransaction {
        productUnitRepository.setup()
        productAbstractRepository.syncRemoteProductList()
        Initialised
    }
    object Initialised
}