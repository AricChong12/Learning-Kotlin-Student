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
package com.example.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import com.example.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() { //app entry point

    override fun onCreate(savedInstanceState: Bundle?) {  //place where ui is set up
        super.onCreate(savedInstanceState)
        setContent {


            //composable ui code
            AffirmationsTheme {   //custom theme
                // A surface container using the 'background' color from the theme
                //surface is material design container
                Surface(
                    modifier = Modifier.fillMaxSize(),  //takes entire screen
                    color = MaterialTheme.colorScheme.background  //sets bg color
                ) {
                    AffirmationsApp()  //fires composable func
                }
            }

        }
    }
}

@Composable  //composable tells jetpack compose this is part of the ui
fun AffirmationsApp() {  //defines func
    AffirmationList(  //call another composable func
        affirmationList = Datasource().loadAffirmations(),
        //inserts datasource and loads all affirmations and stuff them into affirmationList
        //params
    )
}




@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    //func params
    //display affirmations
    //allows customization , modifier


    //scrollable vertical list
    LazyColumn(modifier = modifier) {
        //items like a loop, loop through the list, inputs affirmationList into the loop
        items(affirmationList) { affirmation ->
            //lambdas func

            AffirmationCard(  //pass the card
                affirmation = affirmation,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    //card is material design container
    Card(modifier = modifier) {
        //arranges vertically
        Column {
            //image
            Image(
                painter = painterResource(affirmation.imageResourceId),  //affirmation image
                contentDescription = stringResource(affirmation.stringResourceId), //affirmation string
                modifier = Modifier  //ui modifications
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop  //image cropping
            )

            Text(
                text = LocalContext.current.getString(affirmation.stringResourceId),
                //display affirmation text

                //ui modification
                modifier = Modifier.padding(16.dp),

                //word typography
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}






//preview, just one affirmation
@Preview
@Composable
private fun AffirmationCardPreview() {
    AffirmationCard(Affirmation(R.string.affirmation1, R.drawable.image1))
}
