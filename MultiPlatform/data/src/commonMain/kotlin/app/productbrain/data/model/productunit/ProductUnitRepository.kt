package app.productbrain.data.model.productunit

interface ProductUnitRepository {
    suspend fun setup()
}

class ProductUnitRepositoryActual(
    private val productUnitDao: ProductUnitDao
): ProductUnitRepository {
    override suspend fun setup() {
        UnitName.entries.forEach { unit ->
            val entity = ProductUnitEntity(
                key = unit.key
            )
            productUnitDao.upsert(entity)
        }
    }
}