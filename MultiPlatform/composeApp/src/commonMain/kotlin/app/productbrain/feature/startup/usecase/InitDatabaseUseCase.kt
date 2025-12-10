package app.productbrain.feature.startup.usecase

import app.productbrain.common.Maybe
import app.productbrain.data.model.productunit.ProductUnitRepository
import app.productbrain.data.provider.TransactionProvider

class InitDatabaseUseCase(
    private val transactionProvider: TransactionProvider,
    private val productUnitRepository: ProductUnitRepository
) {
    suspend operator fun invoke(): Maybe<Initialised> = transactionProvider.tryTransaction {
        productUnitRepository.setup()
        Initialised
    }
    object Initialised
}