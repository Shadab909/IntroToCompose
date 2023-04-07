package com.android.introtocompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.introtocompose.ui.theme.IntroToComposeTheme

class MainActivity : ComponentActivity() {
    // a composable can only be called by another composable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntroToComposeTheme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }
}
// a composable function without default value can not be previewed
@Composable
fun MyApp() {
// we are using delegate by instead of =
    var moneyCounter by rememberSaveable{
        mutableStateOf(0)
    }
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        color = Color(0xFF54637A)
    ) {
        // if u put CreateCircle directly under surface it will take whole screen 
        // that's we use column here ( other reasons too)
        Column(
            verticalArrangement = Arrangement.Center ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "$$moneyCounter", style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ))
            Spacer(modifier = Modifier.height(24.dp))
            CreateCircle(moneyCounter){ newValue->
                moneyCounter = newValue
            }

        }

    }
}

@Composable
fun CreateCircle(moneyCounter : Int = 0 , updateMoneyCounter: (Int) -> Unit ) {

    Card(modifier = Modifier
        .padding(3.dp)
        .size(105.dp)
        .clip(shape = CircleShape)
        .clickable {
            updateMoneyCounter(moneyCounter + 1)
        },
        elevation = 4.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = "Tap")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IntroToComposeTheme {
        MyApp()
    }
}