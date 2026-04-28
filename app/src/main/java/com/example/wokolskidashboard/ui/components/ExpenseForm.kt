package com.example.wokulskidashboard.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ExpenseForm(
    name: String,
    amount: String,
    category: String,
    categories: List<String>,
    isUnnecessary: Boolean,
    onNameChange: (String) -> Unit,
    onAmountChange: (String) -> Unit,
    onCategoryChange: (String) -> Unit,
    onUnnecessaryChange: (Boolean) -> Unit,
    onSave: () -> Unit,
    nameError: String = "",
    amountError: String = "",
    categoryError: String = "",
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Wydatki",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFEF5350)
            )
            Text(
                text = "Tutaj Wokulski zapisuje koszty",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            WokulskiTextField(
                value = name,
                onValueChange = onNameChange,
                label = "Cel wydatku (np. Kareta dla panny Izabeli)",
                isError = nameError.isNotEmpty(),
                errorMessage = nameError
            )
            WokulskiTextField(
                value = amount,
                onValueChange = onAmountChange,
                label = "Kwota (Ruble)",
                keyboardType = KeyboardType.Decimal,
                isError = amountError.isNotEmpty(),
                errorMessage = amountError
            )
            WokulskiCategorySelector(
                categories = categories,
                selectedCategory = category,
                onCategorySelected = onCategoryChange
            )
            if (categoryError.isNotEmpty()) {
                Text(
                    text = categoryError,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Wydatek zbyteczny?",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = if (isUnnecessary) "Tak - kaprys" else "Nie - konieczny",
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isUnnecessary) Color(0xFFFFB74D) else Color(0xFF81C784)
                    )
                }

                Switch(
                    checked = isUnnecessary,
                    onCheckedChange = onUnnecessaryChange,
                    colors = SwitchDefaults.colors(
                        checkedTrackColor = Color(0xFFEF6C00),
                        uncheckedTrackColor = MaterialTheme.colorScheme.outline
                    )
                )
            }
            WokulskiButton(
                text = "Zapisz wydatek",
                onClick = onSave,
                containerColor = Color(0xFFC62828)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpenseFormPreview() {
    ExpenseForm(
        name = "Kareta dla panny Izabeli",
        amount = "300.00",
        category = "Wydatki osobiste",
        categories = listOf("Sklep", "Kamienica", "Wydatki osobiste"),
        isUnnecessary = true,
        onNameChange = {},
        onAmountChange = {},
        onCategoryChange = {},
        onUnnecessaryChange = {},
        onSave = {}
    )
}
