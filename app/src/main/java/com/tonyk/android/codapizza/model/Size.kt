package com.tonyk.android.codapizza.model

enum class Size(val displayName: String, val price: Double) {
    SMALL("Small", 8.99),
    MEDIUM("Medium", 10.99),
    LARGE("Large", 12.99),
    EXTRA_LARGE("Extra Large", 14.99)
}
