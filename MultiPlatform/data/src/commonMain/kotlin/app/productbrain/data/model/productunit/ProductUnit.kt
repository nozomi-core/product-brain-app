package app.productbrain.data.model.productunit

import app.productbrain.data.model.productunit.ProductUnit.Cup.*
import app.productbrain.data.model.productunit.ProductUnit.Length.*
import app.productbrain.data.model.productunit.ProductUnit.Roll.*
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
        class MilliLiter(amount: Double): Volume(amount, UnitName.MILLI_LITER)
        class Liter(amount: Double): Volume(amount, UnitName.LITER)
    }

    sealed class Time(amount: Double, unitName: UnitName): ProductUnit(amount, unitName) {
        class Week(amount: Double): ProductUnit(amount, UnitName.WEEK)
        class Month(amount: Double): ProductUnit(amount, UnitName.MONTH)
        class Year(amount: Double): ProductUnit(amount, UnitName.YEAR)
    }

    sealed class Length(amount: Double, unitName: UnitName): ProductUnit(amount, unitName) {
        class MilliMeter(amount: Double): ProductUnit(amount, UnitName.MILLI_METER)
        class CentiMeter(amount: Double): ProductUnit(amount, UnitName.CENTI_METER)
        class Meter(amount: Double): ProductUnit(amount, UnitName.METER)
    }

    sealed class Cup(amount: Double, unitName: UnitName): ProductUnit(amount, unitName) {
        class Small(amount: Double): ProductUnit(amount, UnitName.CUP_SMALL)
        class Medium(amount: Double): ProductUnit(amount, UnitName.CUP_MEDIUM)
        class Large(amount: Double): ProductUnit(amount, UnitName.CUP_LARGE)
    }

    sealed class Roll(amount: Double, unitName: UnitName): ProductUnit(amount, unitName) {
        class Regular(amount: Double): ProductUnit(amount, UnitName.ROLL_REGULAR)
    }

    class Qty(amount: Double): ProductUnit(amount, UnitName.QTY)

    companion object {
        fun of(amount: Double, name: UnitName): ProductUnit {
            return when(name) {
                UnitName.GRAM -> Gram(amount)
                UnitName.KILO -> Kilo(amount)
                UnitName.MILLI_LITER -> MilliLiter(amount)
                UnitName.LITER -> Liter(amount)
                UnitName.WEEK -> Week(amount)
                UnitName.MONTH -> Month(amount)
                UnitName.YEAR -> Year(amount)
                UnitName.CUP_SMALL -> Small(amount)
                UnitName.CUP_MEDIUM -> Medium(amount)
                UnitName.CUP_LARGE -> Large(amount)
                UnitName.MILLI_METER -> MilliMeter(amount)
                UnitName.CENTI_METER -> CentiMeter(amount)
                UnitName.METER -> Meter(amount)
                UnitName.ROLL_REGULAR -> Regular(amount)
                UnitName.QTY -> Qty(amount)
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
    val unitSystem: UnitSystem,
    val key: String,
    val baseUnits: Double
) {
    //Weight
    GRAM(UnitSystem.WEIGHT,"g", 1/1000.0),
    KILO(UnitSystem.WEIGHT,"kg", 1.0),


    //Volume
    MILLI_LITER(UnitSystem.VOLUME,"ml", 1/1000.0),
    LITER(UnitSystem.VOLUME,"l", 1.0),

    //Time
    WEEK(UnitSystem.TIME, "week", 12.0/52),
    MONTH(UnitSystem.TIME, "month", 1.0),
    YEAR(UnitSystem.TIME,"year", 12.0),

    //Length
    MILLI_METER(UnitSystem.LENGTH,"mm", 1/1000.0),
    CENTI_METER(UnitSystem.LENGTH,"cm", 1.0),
    METER(UnitSystem.LENGTH,"m", 100.0),

    //Cup
    CUP_SMALL(UnitSystem.CUP,"cup_small", 1.0),
    CUP_MEDIUM(UnitSystem.CUP,"cup_medium", 1.0),
    CUP_LARGE(UnitSystem.CUP,"cup_large", 1.0),

    //Roll
    ROLL_REGULAR(UnitSystem.ROLL, "roll_regular", 1.0),

    QTY(UnitSystem.QTY, "qty", 1.0)

    ;
}

enum class UnitSystem {
    WEIGHT,
    VOLUME,
    TIME,
    LENGTH,
    CUP,
    ROLL,
    QTY
}