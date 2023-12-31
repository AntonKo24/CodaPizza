package com.tonyk.android.codapizza.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Pizza(
    val toppings: Map<Topping, ToppingPlacement> = emptyMap(),
    val size: Size = Size.LARGE // Добавляем поле для выбранного размера
) : Parcelable {
    val price: Double
        get() {
            return toppings.asSequence()
                .sumOf { (_, toppingPlacement) ->
                    when (toppingPlacement) {
                        ToppingPlacement.Left, ToppingPlacement.Right -> 0.5
                        ToppingPlacement.All -> 1.0
                    }
                }
        }

    fun withTopping(topping: Topping, placement: ToppingPlacement?): Pizza {
        return copy(
            toppings = if (placement == null) {
                toppings - topping
            } else {
                toppings + (topping to placement)
            }
        )
    }
}
