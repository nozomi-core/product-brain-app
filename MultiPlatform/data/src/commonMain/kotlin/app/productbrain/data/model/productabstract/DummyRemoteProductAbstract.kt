package app.productbrain.data.model.productabstract

import app.productbrain.data.model.productunit.UnitName

val allProducts = buildProducts {
    product {
        id("01KEY303YMYPPA9SD6G8PKSMEA")
        name = "Apple"
        units = listOf(UnitName.KILO)

        variant {
            name = "Pink Lady Apple"
            alias = listOf("Pink Lady")
        }

        variant {
            name = "Granny Smith Apple"
            alias = listOf("Granny Smith")
        }

        variant {
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
            name = "Apple Juice"
        }

        variant {
            name = "Orange Juice"
        }

        variant {
            name = "Breakfast Juice"
        }

        variant {
            name = "Fruit Juice"
        }
    }

    product {
        id("01KEY31PGKA4JEYVW8RQFDFFKM")
        name = "Milk"
        units = listOf(UnitName.LITER)

        variant {
             name = "Almond Milk"
        }

        variant {
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
            name = "Toilet Paper 2ply"
        }

        variant {
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
    }

    fun build(): BuiltProducts {
        val mapping = productList.map { product ->
            val abstractProduct = RemoteProductAbstract(
                id = RemoteProductAbstractId(product.rawRemoteId!!),
                name = product.name!!,
                units = product.units!!,
                alias = product.alias ?: listOf()
            )

            val variants = product.variantList.map { variant ->
                RemoteProductVariant()
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
    val products: List<RemoteProductAbstract>,
    val variants: List<RemoteProductVariant>
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
}

fun buildProducts(builder: ProductListBuilder.() -> Unit): ProductListBuilder {
    val productBuilder = ProductListBuilder()
    builder(productBuilder)
    return productBuilder
}

