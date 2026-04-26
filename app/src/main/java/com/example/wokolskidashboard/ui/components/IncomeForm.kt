package com.example.wokulskidashboard.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun IncomeForm(
    name: String,
    amount: String,
    category: String,
    categories: List<String>,
    onNameChange: (String) -> Unit,
    onAmountChange: (String) -> Unit,
    onCategoryChange: (String) -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
    nameError: String = "",
    amountError: String = "",
    categoryError: String = ""
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
                text = "Przychody",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4CAF50)
            )
            Text(
                text = "Tutaj Rzecki zapisuje zyski ze sklepu",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            WokulskiTextField(
                value = name,
                onValueChange = onNameChange,
                label = "Nazwa towaru (np. Parasol)",
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
            WokulskiButton(
                text = "Zapisz przychod",
                onClick = onSave,
                containerColor = Color(0xFF2E7D32)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IncomeFormPreview() {
    IncomeForm(
        name = "Parasol",
        amount = "15",
        category = "Sklep",
        categories = listOf("Sklep", "Kamienica", "Wydatki osobiste"),
        onNameChange = {},
        onAmountChange = {},
        onCategoryChange = {},
        onSave = {}
    )
}
