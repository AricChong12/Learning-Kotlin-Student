package com.example.tiptime

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat

//test class
class TipCalculatorTests {

    @Test //test func
    fun calculateTip_20PercentNoRoundup() {

        //dummy inputs
        val amount = 10.00
        val tipPercent = 20.00

        //expected outputs
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(amount = amount, tipPercent = tipPercent, false)
        assertEquals(expectedTip, actualTip)
    //checks both and compare, if both matched then test success
    }
}