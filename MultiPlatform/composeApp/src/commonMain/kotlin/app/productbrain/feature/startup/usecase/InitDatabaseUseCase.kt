package app.productbrain.feature.startup.usecase

import app.productbrain.common.Maybe
import app.productbrain.data.model.productabstract.ProductAbstractRepository
import app.productbrain.data.model.productabstract.allProducts
import app.productbrain.data.model.productunit.ProductUnitRepository
import app.productbrain.data.model.productvariant.ProductVariantRepository
import app.productbrain.data.provider.TransactionProvider

class InitDatabaseUseCase(
    private val transactionProvider: TransactionProvider,
    private val productUnitRepository: ProductUnitRepository,
    private val productAbstractRepository: ProductAbstractRepository,
    private val variantRepository: ProductVariantRepository
) {
    suspend operator fun invoke(): Maybe<Initialised> = transactionProvider.tryTransaction {
        productUnitRepository.setup()

        val builtProducts = allProducts.build()
        builtProducts.products.forEach {
            productAbstractRepository.upsert(it)
        }

        builtProducts.variants.forEach {
            variantRepository.upsert(it)
        }

        Initialised
    }
    object Initialised
}