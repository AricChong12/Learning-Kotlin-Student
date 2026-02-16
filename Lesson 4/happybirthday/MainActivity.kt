package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.happybirthday.ui.theme.HappyBirthdayTheme


import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.*
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HappyBirthdayTheme {
                /*
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingText(message = "Happy Birthday Sam!", from = "From Emma")
                }
                */

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingImage(
                        message = stringResource(R.string.happy_birthday_text),
                        from = stringResource(R.string.from_text),
                        //modifier = Modifier.padding(8.dp)
                        //It is a good practice to pass the
                        // modifier attribute(s) along with the
                        // modifier from the parent composable
                    )
                }


            }
        }
    }
}


/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
*/


@Composable
fun GreetingText(message: String,from: String,
                 modifier: Modifier = Modifier) {
    //gives 8dp to all chars
    Column(modifier = modifier,
        verticalArrangement = Arrangement.Center) {
        //verticalArrangement sets text to vertically center
        Text(
            text = message,
            fontSize = 90.sp, //set font size to scalable pixels
            lineHeight = 116.sp, // set the height of each line
            textAlign = TextAlign.Center
        // aligns to
        // horizontally center
        )

        Text(
            text = from,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
    }


}


@Composable
fun GreetingImage(message: String, from: String,
                  modifier: Modifier = Modifier){
    val image = painterResource(R.drawable.androidparty)
    //stores the android party image into a container named image

    //stacks ui components
    Box(modifier) {
        Image(
            painter = image,
            //assigns image var to painter
            contentDescription = null,
            //skips talkback
            contentScale = ContentScale.Crop,
            //scale image by crop
            alpha = 0.5F,
        )

        GreetingText(
            message = message,
            from = from,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )

    }

}




//to view in the design mode only
//preview is calling the composable func
@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        //Greeting("James")
        GreetingImage(message = "Happy Birthday Sam!",
                     from = "From Emma")
        //use GreetingText func and inserts argument
        //into message var
    }
}