/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.woof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.woof.data.Dog
import com.example.woof.data.dogs
import com.example.woof.ui.theme.WoofTheme

import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.ui.Alignment

//experimental api
import androidx.compose.material3.ExperimentalMaterial3Api


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {  //set up ui
        super.onCreate(savedInstanceState)  //must
        setContent {
            WoofTheme {  //app theme
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()  //takes up entire screen
                ) {
                    WoofApp() //call composable func
                }
            }
        }
    }
}

/**
 * Composable that displays an app bar and a list of dogs.
 */
@Composable   //contains lazy column that displays the dog item
fun WoofApp() {
    Scaffold(
        topBar = {  //app bar at top
            WoofTopAppBar()  //displays logo and app name
        }
    ) { it ->
        LazyColumn(contentPadding = it) {  //arranges the dog cards
            items(dogs) {  //loops through dogs
                DogItem(
                    dog = it,    //display a card with dogs image and ino
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                    //do small gap around each dog card
                )
            }
        }
    }
}

/**
 * Composable that displays a list item containing a dog icon and their information.
 *
 * @param dog contains the data that populates the list item
 * @param modifier modifiers to set to this composable
 */
@Composable  //contains row that displays photo of the dog and info about it
fun DogItem(
    dog: Dog, //takes dog data class
    modifier: Modifier = Modifier
) {
    //material card
    Card(modifier = modifier) {
        Row( //arranges child composable horizontally
            modifier = Modifier
                .fillMaxWidth()  //row stretches across entire card width
                .padding(dimensionResource(id = R.dimen.padding_small))  //small gap inside the card
        ) {
            DogIcon(dog.imageResourceId) //shows dog photo
            DogInformation(dog.name, dog.age) //shows dog name and age
        }
    }
}

/**
 * Composable that displays a photo of a dog.
 *
 * @param dogIcon is the resource ID for the image of the dog
 * @param modifier modifiers to set to this composable
 */
@Composable
fun DogIcon(  //displays photo of the dog
    @DrawableRes dogIcon: Int, //resource id of dog
    modifier: Modifier = Modifier  //allows custom layout styling
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))  //sets width and height
            .padding(dimensionResource(R.dimen.padding_small))  //adds space around image
            .clip(MaterialTheme.shapes.small), //applies rounded corners
        contentScale = ContentScale.Crop,  //crops the image
        painter = painterResource(dogIcon), //display image

        // Content Description is not needed here - image is decorative, and setting a null content
        // description allows accessibility services to skip this element during navigation.

        contentDescription = null  //no descriptions
    )
}

/**
 * Composable that displays a dog's name and age.
 *
 * @param dogName is the resource ID for the string of the dog's name
 * @param dogAge is the Int that represents the dog's age
 * @param modifier modifiers to set to this composable
 */
@Composable  //part of ui
fun DogInformation(  //displays dog name and age
    @StringRes dogName: Int,  //string res of dog
    dogAge: Int,  //dog age
    modifier: Modifier = Modifier  //custom layout modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(dogName),  //egt dog name text from strin xml
            style = MaterialTheme.typography.displayMedium,  //applies larger font style
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_small))  //small space above text
        )
        Text(
            text = stringResource(R.string.years_old, dogAge), //loads formatted string
            style = MaterialTheme.typography.bodyLarge
            //uses large font style
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class) //experimentation api class
@Composable
fun WoofTopAppBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar( //experimental func
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(  //displays logo
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.image_size))
                        .padding(dimensionResource(id = R.dimen.padding_small)),
                    painter = painterResource(R.drawable.ic_woof_logo),

                    contentDescription = null
                )
                Text( //display text
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}








/**
 * Composable that displays what the UI of the app looks like in light theme in the design tab.
 */

//preview light theme
@Preview
@Composable
fun WoofPreview() {
    WoofTheme(darkTheme = false) {
        WoofApp()
    }
}

//preview dark theme
@Preview
@Composable
fun WoofDarkThemePreview(){
    WoofTheme(darkTheme = true){
        WoofApp()
    }
}
