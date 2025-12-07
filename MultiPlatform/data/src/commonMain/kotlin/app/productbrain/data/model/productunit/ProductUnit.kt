package app.productbrain.data.model.productunit

import app.productbrain.data.model.productunit.ProductUnit.Volume.*
import app.productbrain.data.model.productunit.ProductUnit.Weight.*
import app.productbrain.data.model.productunit.ProductUnit.Time.*

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

    sealed class Time(amount: Double, unitName: UnitName): ProductUnit(amount, unitName) {
        class Week(amount: Double): ProductUnit(amount, UnitName.WEEK)
        class Month(amount: Double): ProductUnit(amount, UnitName.MONTH)
        class Year(amount: Double): ProductUnit(amount, UnitName.YEAR)
    }

    companion object {
        fun of(amount: Double, name: UnitName): ProductUnit {
            return when(name) {
                UnitName.GRAM -> Gram(amount)
                UnitName.KILO -> Kilo(amount)
                UnitName.MILLILITER -> MilliLiter(amount)
                UnitName.LITER -> Liter(amount)
                UnitName.WEEK -> Week(amount)
                UnitName.MONTH -> Month(amount)
                UnitName.YEAR -> Year(amount)
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
* Time = Month
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
    GRAM("g", 1/1000.0),
    KILO("kg", 1.0),


    //Volume
    MILLILITER("ml", 1/1000.0),
    LITER("l", 1.0),

    //Time
    WEEK( "week", 12.0/52),
    MONTH( "month", 1.0),
    YEAR("year", 12.0)
    ;
}