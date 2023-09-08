package com.tonyk.android.codapizza.ui.pizzaorder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tonyk.android.codapizza.R
import com.tonyk.android.codapizza.model.Pizza
import com.tonyk.android.codapizza.model.Size
import com.tonyk.android.codapizza.model.Topping
import com.tonyk.android.codapizza.model.ToppingPlacement
import java.nio.DoubleBuffer
import java.text.NumberFormat


@Composable
fun PizzaBuilderScreen(
    modifier: Modifier = Modifier
) {
    var pizza by rememberSaveable { mutableStateOf(Pizza()) }
    var selectedSize by remember { mutableStateOf(Size.LARGE) }
    var currentPrice by rememberSaveable { mutableStateOf(selectedSize.price) } // Состояние для текущей цены

    Column(
        modifier = modifier
    ) {
        SizeDropdownMenu(
            sizes = enumValues<Size>().toList(),
            selectedSize = selectedSize,
            onSizeSelected = { newSize ->
                selectedSize = newSize
                currentPrice = newSize.price + pizza.price
            },
            modifier = Modifier.padding(16.dp)
        )
        ToppingsList(
            pizza = pizza,
            onEditPizza = { pizza = it
                currentPrice = selectedSize.price + pizza.price
                          },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true)

        )
        OrderButton(
            currentPrice = currentPrice,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}






@Composable
private fun ToppingsList(
    pizza: Pizza,
    onEditPizza: (Pizza) -> Unit,
    modifier: Modifier = Modifier
) {

    var toppingBeingAdded by rememberSaveable { mutableStateOf<Topping?>(null) }

    toppingBeingAdded?.let { topping ->
        ToppingPlacementDialog(
            topping = topping,
            onSetToppingPlacement = { placement ->
                onEditPizza(pizza.withTopping(topping, placement))
            },
            onDismissRequest = {
                toppingBeingAdded = null
            }
        )
    }


    LazyColumn(
        modifier = modifier
    ) {
        items(Topping.values()) { topping ->
            ToppingCell(
                topping = topping,
                placement = pizza.toppings[topping],
                onClickTopping = {
                    toppingBeingAdded = topping
                }
            )
        }
    }


}
@Composable
private fun OrderButton(
    currentPrice: Double,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = {

        }
    ) {

        val currencyFormatter = remember { NumberFormat.getCurrencyInstance() }
        val price = currencyFormatter.format(currentPrice)

        Text(
            text = stringResource(R.string.place_order_button, price)
                .uppercase()
        )
    }
}

