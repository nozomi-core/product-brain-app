package app.productbrain.data.model.productunit

sealed class ProductUnit(
    val amount: Double,
    val unitName: UnitName
) {
    sealed class Weight(amount: Double, unitName: UnitName): ProductUnit(amount, unitName) {
        class Gram(amount: Double): Weight(amount, UnitName.GRAM)
        class Kilo(amount: Double): Weight(amount, UnitName.KILO)
    }

    sealed class Volume(amount: Double, unitName: UnitName): ProductUnit(amount, unitName) {
        class MilliLiter(amount: Double): Volume(amount, UnitName.MILLILITER)
        class Liter(amount: Double): Volume(amount, UnitName.LITER)
    }

    companion object {
        fun of(amount: Double, name: UnitName): ProductUnit {
            return when(name) {
                UnitName.GRAM -> Weight.Gram(amount)
                UnitName.KILO -> Weight.Kilo(amount)
                UnitName.MILLILITER -> Volume.MilliLiter(amount)
                UnitName.LITER -> Volume.Liter(amount)
            }
        }
    }
}

/*
* This class is the enumeration of a unit type,
* please note also here that the "baseUnits" variable tells the program how to convert this
* unit into what is called the "Base" unit for comparison. Weight and volume have different
* base units
*
* BASE UNIT DEFINITION
* Volume = Liter
* Weight = Kilogram
*
* so if we take 'grams', the base unit of '1/1000' tells it how to convert to KG
*
* NOTE: Please note, if in the future more unit types are adding they should have the same
* base unit of their unit class and not their scale class, ie the imperial units should use
* kilos as the base unit as well
*
*/
enum class UnitName(
    val key: String,
    val baseUnits: Double
) {
    //Weight
    GRAM(key = "g", 1/1000.0),
    KILO(key = "kg", 1.0),


    //Volume
    MILLILITER(key = "ml", 1/1000.0),
    LITER(key = "l", 1.0);
}