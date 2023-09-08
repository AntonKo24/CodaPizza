package com.tonyk.android.codapizza

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.geometry.Size
import com.tonyk.android.codapizza.ui.pizzaorder.PizzaBuilderScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            PizzaBuilderScreen()
        }
    }
}

