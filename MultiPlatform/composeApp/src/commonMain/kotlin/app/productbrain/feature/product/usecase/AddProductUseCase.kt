package app.productbrain.feature.product.usecase

import app.productbrain.common.Maybe
import app.productbrain.common.Optional
import app.productbrain.common.RemoteId
import app.productbrain.data.model.productabstract.ProductAbstract
import app.productbrain.data.model.productabstract.ProductAbstractLocalId
import app.productbrain.data.model.productabstract.ProductAbstractRepository
import app.productbrain.data.model.productunit.UnitName
import app.productbrain.data.model.productunit.UnitSystem
import app.productbrain.data.model.productvariant.ProductVariant
import app.productbrain.data.model.productvariant.ProductVariantLocalId
import app.productbrain.data.model.productvariant.ProductVariantRepository
import app.productbrain.data.provider.TransactionProvider

class AddProductUseCase(
    private val transactionProvider: TransactionProvider,
    private val productRepository: ProductAbstractRepository,
    private val variantRepository: ProductVariantRepository
) {

    suspend operator fun invoke(
        name: String,
        unit: UnitSystem
    ): Maybe<ProductVariant> = transactionProvider.tryTransaction {
        val localProductId = ProductAbstractLocalId.create()

        val product = ProductAbstract(localProductId, RemoteId.NoBinding, name, listOf(unit), listOf())
        productRepository.upsert(product)

        val localVariantId = ProductVariantLocalId.create()

        val variant = ProductVariant(
            localVariantId,
            RemoteId.NoBinding,
            parentProductId = product.localId,
            name,
            Optional.Value(product),
            isDefaultVariant = true
        )

        variantRepository.upsert(variant)
        variant
    }
}