package com.example.wokulskidashboard.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wokulskidashboard.model.Transaction

@Composable
fun TransactionCard(
    transaction: Transaction,
    modifier: Modifier = Modifier
) {
    val isExpense = transaction.isExpense
    val accentColor = if (isExpense) Color(0xFFEF5350) else Color(0xFF66BB6A)
    val sign = if (isExpense) "-" else "+"

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(accentColor.copy(alpha = 0.15f), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (isExpense) "💸" else "💰",
                        fontSize = 18.sp
                    )
                }

                Column {
                    Text(
                        text = transaction.name,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = transaction.category,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f)
                    )
                    if (isExpense) {
                        Text(
                            text = if (transaction.isUnnecessary) "Zbyteczny" else "Konieczny",
                            style = MaterialTheme.typography.bodySmall,
                            color = if (transaction.isUnnecessary) Color(0xFFFFB74D) else Color(0xFF81C784)
                        )
                    }
                }
            }

            Text(
                text = "$sign%.2f R".format(transaction.amount),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = accentColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TransactionCardIncomePreview() {
    TransactionCard(transaction = Transaction(name = "Parasol", amount = 15.0, isExpense = false, category = "Sklep"))
}

@Preview(showBackground = true)
@Composable
private fun TransactionCardExpensePreview() {
    TransactionCard(transaction = Transaction(name = "Kwiaty", amount = 10.0, isExpense = true, category = "Wydatki osobiste", isUnnecessary = true))
}
