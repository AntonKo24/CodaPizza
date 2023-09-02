package com.tonyk.android.codapizza.ui.pizzaorder

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import java.util.Locale


import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tonyk.android.codapizza.R
import com.tonyk.android.codapizza.model.Pizza
import com.tonyk.android.codapizza.model.Topping
import com.tonyk.android.codapizza.model.ToppingPlacement

@Preview
@Composable
fun PizzaBuilderScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        ToppingsList(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true)
        )
        OrderButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }

}

private var pizza =
    Pizza(
        toppings = mapOf(
            Topping.Pepperoni to ToppingPlacement.All,
            Topping.Pineapple to ToppingPlacement.All
        )
    )
    set(value) {
        Log.d("PizzaBuilderScreen", "Reassigned pizza to $value")
        field = value
    }



@Composable
private fun ToppingsList(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(Topping.values()) { topping ->
            ToppingCell(
                topping = topping,
                placement = pizza.toppings[topping],
                onClickTopping = {
                    val isOnPizza = pizza.toppings[topping] != null
                    pizza = pizza.withTopping(
                        topping = topping,
                        placement = if (isOnPizza) {
                            null
                        } else {
                            ToppingPlacement.All
                        }
                    )

                }
            )
        }
    }


}
@Composable
private fun OrderButton(
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = {
// TODO
        }
    ) {
        Text(
            text = stringResource(R.string.place_order_button)
                .uppercase()
        )
    }
}

