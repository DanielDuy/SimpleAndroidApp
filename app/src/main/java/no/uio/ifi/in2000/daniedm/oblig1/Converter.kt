package no.uio.ifi.in2000.daniedm.oblig1

import kotlin.math.roundToInt

fun converter(amount: Int, unit: ConverterUnits): Int {
    return (amount * unit.convertRate).roundToInt()
}
