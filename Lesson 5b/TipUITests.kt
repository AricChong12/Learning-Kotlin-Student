package com.example.tiptime

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import com.example.tiptime.ui.theme.TipTimeTheme
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import java.text.NumberFormat
import org.junit.Test
import androidx.compose.material3.Surface

import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize

//test ui class
class TipUITests {
    @get:Rule  //tells JUnit to use this compose test rule
    val composeTestRule = createComposeRule()  //sets up the compose testing env

    //marks the func as test func
    @Test
    fun calculate_20_percent_tip() {

        //set up content
        composeTestRule.setContent {
            TipTimeTheme {
                Surface (modifier = Modifier.fillMaxSize()){
                    TipTimeLayout()
                }
            }
        }

        //targets the text field and load it with 10
        composeTestRule.onNodeWithText("Bill Amount")
            .performTextInput("10")

        //targets the text field and load it with 20
        composeTestRule.onNodeWithText("Tip Percentage").performTextInput("20")

        //calc expected tip
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)

        //output verification
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip").assertExists(
            "No node with this text was found."
        )
    }
}