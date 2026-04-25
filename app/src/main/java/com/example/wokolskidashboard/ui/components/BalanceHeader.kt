package com.example.wokulskidashboard.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun BalanceHeader(
    balance: Double,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (balance >= 0.0) Color(0xFF2E7D32) else Color(0xFFC62828)
    val statusText = if (balance >= 0.0) "Kapital rosnie!" else "Deficyt w kasie!"

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Stan kasy Wokulski i Spolka",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
        Text(
            text = "%.2f Rubli".format(balance),
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = statusText,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White.copy(alpha = 0.8f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BalanceHeaderPositivePreview() {
    BalanceHeader(balance = 150.75)
}

@Preview(showBackground = true)
@Composable
private fun BalanceHeaderNegativePreview() {
    BalanceHeader(balance = -42.0)
}
