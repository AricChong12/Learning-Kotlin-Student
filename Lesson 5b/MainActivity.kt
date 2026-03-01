
package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.NumberFormat
import androidx.compose.material3.TextField
import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.annotation.StringRes

import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Switch
import androidx.compose.foundation.layout.wrapContentWidth

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource

import org.jetbrains.annotations.VisibleForTesting





class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    //set the surface to full size of the screen
                ) {
                    TipTimeLayout() //call custom func
                }
            }
        }
    }
}


// composable describes part of the ui
@Composable
fun EditNumberField(
    @StringRes label: Int,  //string resource id
    @DrawableRes leadingIcon: Int, //drawable resource id
    keyboardOptions: KeyboardOptions,  //controls keyboard types
    value: String, //current text in text field
    onValueChange: (String) -> Unit, //lambdas func that updates the value
    modifier : Modifier = Modifier){

    //one text field only
    TextField(
        value = value,  //no matter what value is value, state hoisting
        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), null) },
        //icon
        onValueChange = onValueChange, //trigger lambdas func
        modifier = modifier,  // external styling
        label = { Text(stringResource(label)) },  //converts resource id to string
        singleLine = true, //single line input only
        keyboardOptions = keyboardOptions  //applies keyboard
    )
}



@Composable
fun RoundTheTipRow(modifier: Modifier = Modifier, roundUp: Boolean,
                   onRoundUpChanged: (Boolean) -> Unit,  //call lambdas funcs
                   ){


    Row (
        modifier = modifier
            .fillMaxWidth() //full width of screen
            .size(48.dp),
            verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.round_up_tip)) //loads string from string resources

        Switch( //switch component
            //state hoisting no need declare var
            checked = roundUp,
            onCheckedChange = onRoundUpChanged,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End), //aligns to the end
        )

    }
}









@Composable
fun TipTimeLayout() {
    //remember func keep state across app
    //all states are mutable and it means changeable
    var amountInput by remember { mutableStateOf("") }
    var tipInput by remember { mutableStateOf("") }
    var roundUp by remember { mutableStateOf(false) }

    //to double of null, if null returns 0.0, both var
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0

    //stuff all params into the main logic func, roundUp is a toggle switch
    val tip = calculateTip(amount, tipPercent, roundUp)


    Column(
        modifier = Modifier
            .statusBarsPadding()  //avoids bar overlap
            .padding(horizontal = 40.dp) //side padding

            .safeDrawingPadding()  //avoids system ui areas
            .verticalScroll(rememberScrollState()), //enables vertical scroll
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )

        EditNumberField(
            label = R.string.bill_amount,
            leadingIcon = R.drawable.money,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number, //input num
                imeAction = ImeAction.Next //next button of keyboard
            ),
            value = amountInput,
            onValueChange = { amountInput = it }, //links to lambdas func
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )

        EditNumberField(
            label = R.string.how_was_the_service,
            leadingIcon = R.drawable.money,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,  //only inputs num
                imeAction = ImeAction.Done  //keyboard button done
            ),
            value = tipInput,
            onValueChange = { tipInput = it },  //same logics like above
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )

        RoundTheTipRow(
            roundUp = roundUp,
            onRoundUpChanged = { roundUp = it }, //same logics like above
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Text(
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(150.dp)) //space
    }
}







/**
 * Calculates the tip based on the user input and format the tip amount
 * according to the local currency.
 * Example would be "$10.00".
 */

@VisibleForTesting //targets this func for logic testing
internal fun calculateTip(amount: Double, tipPercent: Double = 15.0, roundUp: Boolean): String {
    var tip = tipPercent / 100 * amount //init tip var and assigns logics
    if (roundUp) {
        tip = kotlin.math.ceil(tip)
    }
    return NumberFormat.getCurrencyInstance().format(tip)
    //returns the formatted tip var
}





//preview only, ui design
@Preview(showBackground = true)
@Composable
fun TipTimeLayoutPreview() {
    TipTimeTheme {
        TipTimeLayout()
    }
}



