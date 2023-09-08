package com.tonyk.android.codapizza.ui.pizzaorder

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tonyk.android.codapizza.model.Size


@Composable
fun SizeDropdownMenu(
    sizes: List<Size>,
    selectedSize: Size,
    onSizeSelected: (Size) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            sizes.forEach { size ->
                DropdownMenuItem(
                    text =  { Text(text = size.displayName) },
                    onClick = {
                        onSizeSelected(size)
                        expanded = false
                    }
                )
            }
        }

        Text(
            text = "Size: ${selectedSize.displayName}",
            modifier = Modifier
                .clickable {
                    expanded = true
                }
                .padding(8.dp)
        )

    }
}





