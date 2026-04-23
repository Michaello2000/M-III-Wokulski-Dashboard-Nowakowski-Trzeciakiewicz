package com.example.wokulskidashboard.model

data class Transaction(
    val name: String,
    val amount: Double,
    val isExpense: Boolean
)