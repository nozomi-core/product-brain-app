package app.productbrain.data.model.productabstract

import app.productbrain.common.Optional
import app.productbrain.common.RemoteId
import app.productbrain.data.model.productunit.UnitName
import app.productbrain.data.model.productvariant.ProductVariant
import app.productbrain.data.model.productvariant.ProductVariantLocalId
import app.productbrain.data.model.productvariant.ProductVariantRemoteId

val allProducts = buildProducts {
    product {
        id("01KEY303YMYPPA9SD6G8PKSMEA")
        name = "Apple"
        units = listOf(UnitName.KILO)

        variant {
            id("01KF7GTPC2QD6F6KG8T1QDSRQ5")
            name = "Pink Lady Apple"
            alias = listOf("Pink Lady")
        }

        variant {
            id("01KF7GTZK1JMTKVAY7J3ZW2HCF")
            name = "Granny Smith Apple"
            alias = listOf("Granny Smith")
        }

        variant {
            id("01KF7GV9CM0DGGJ6HN3WVENEFF")
            name = "Royal Gala Apple"
            alias = listOf("Royal Gala")
        }
    }

    product {
        id("01KEY30GCJ477FXJP8E97XCFYW")
        name = "Chips"
        units = listOf(UnitName.GRAM)

        alias = listOf("Crisps")
    }

    product {
        id("01KEY30WQ9ZHDP0W5GN6WRTCGS")
        name = "Coffee Beans"
        units = listOf(UnitName.KILO)
        alias = listOf("Coffee")
    }
    product {
        id("01KEY3130BFTY31J87E5J30S01")
        name = "Coffee Cup"
        units = listOf(UnitName.CUP_MEDIUM)
        alias = listOf("Coffee")
    }

    product {
        id("01KEY31EGQBPNHTH195V6Q0RDZ")
        name = "Juice"
        units = listOf(UnitName.LITER)

        variant {
            id("01KF7GVNVAB6WSQ024PWCMDSAZ")
            name = "Apple Juice"
        }

        variant {
            id("01KF7GVXJTKNRT7Q32WHMGHYVF")
            name = "Orange Juice"
        }

        variant {
            id("01KF7GW61HAWA7KCA90PAQ6W8K")
            name = "Breakfast Juice"
        }

        variant {
            id("01KF7GWDDTBTM0G0G4DXYT7YP7")
            name = "Fruit Juice"
        }
    }

    product {
        id("01KEY31PGKA4JEYVW8RQFDFFKM")
        name = "Milk"
        units = listOf(UnitName.LITER)

        variant {
            id("01KF7GWJ619A1QYHF9H9T7VK84")
             name = "Almond Milk"
        }

        variant {
            id("01KF7GWR371QY0GXZNRSWH8QVG")
            name = "Full Cream Milk"
        }
    }

    product {
        id("01KEY33977G53A5EN7GFV8E6EJ")
        name = "Soft Drink"
        units = listOf(UnitName.LITER)
        alias = listOf("Cola", "Coke", "Coke Cola", "Fanta", "Sprite", "Lift", "Kirks")
    }

    product {
        id("01KEY31X239CRAQTKR2PTD0Q2K")
        name = "Toilet Paper"
        units = listOf(UnitName.ROLL_REGULAR)

        variant {
            id("01KF7GWY2SYG0ZMYSWM14DFZC8")
            name = "Toilet Paper 2ply"
        }

        variant {
            id("01KF7GX3J8MTKN6H2TPTVZXC5D")
            name = "Toilet Paper 3ply"
        }
    }
}

class ProductListBuilder {
    private val productList = mutableListOf<ProductBuilder>()

    fun product(builder: ProductBuilder.() -> Unit) {
        val newProduct = ProductBuilder()

        productList.add(newProduct)
        builder(newProduct)

        newProduct.variant {
            id(newProduct.rawRemoteId!!)
            name = newProduct.name
            isDefaultVariant = true
        }
    }

    fun build(): BuiltProducts {
        val mapping = productList.map { product ->
            val abstractProduct = ProductAbstract(
                remoteId = RemoteId.Bound(ProductAbstractRemoteId(product.rawRemoteId!!)),
                localId = ProductAbstractLocalId(product.rawRemoteId!!),
                name = product.name!!,
                units = product.units!!,
                alias = product.alias ?: listOf()
            )

            val variants = product.variantList.map { variant ->
                ProductVariant(
                    localId = ProductVariantLocalId(variant.remoteIdRaw!!),
                    remoteId = RemoteId.Bound(ProductVariantRemoteId(variant.remoteIdRaw!!)),
                    parentProductId = abstractProduct.localId,
                    name = variant.name!!,
                    parent = Optional.Value(abstractProduct),
                    isDefaultVariant = variant.isDefaultVariant
                )
            }

            Pair(abstractProduct, variants)
        }

        val allProducts = mapping.map { it.first }

        val allVariants = buildList {
            mapping.forEach { pairs ->
                addAll(pairs.second)
            }
        }

        return BuiltProducts(
            allProducts,
            allVariants
        )
    }
}

class BuiltProducts(
    val products: List<ProductAbstract>,
    val variants: List<ProductVariant>
)


class ProductBuilder() {
    val variantList = mutableListOf<VariantBuilder>()
    var name: String? = null
    var alias: List<String>? = null
    var units: List<UnitName>? = null

    var rawRemoteId: String? = null


    fun id(ulid: String) {
        rawRemoteId = "REM$ulid"
    }

    fun variant(builder: VariantBuilder.() -> Unit) {
        val newVariant = VariantBuilder()
        variantList.add(newVariant)
        builder(newVariant)
    }
}

class VariantBuilder() {
    var name: String? = null
    var alias: List<String>? = null
    var remoteIdRaw: String? = null
    var isDefaultVariant = false

    fun id(ulid: String) {
        remoteIdRaw = "REM${ulid}"
    }
}

fun buildProducts(builder: ProductListBuilder.() -> Unit): ProductListBuilder {
    val productBuilder = ProductListBuilder()
    builder(productBuilder)
    return productBuilder
}

