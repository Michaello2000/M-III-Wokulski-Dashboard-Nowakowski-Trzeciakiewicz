package com.example.wokulskidashboard.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.wokulskidashboard.model.Transaction
import com.example.wokulskidashboard.ui.components.BalanceHeader
import com.example.wokulskidashboard.ui.components.ExpenseForm
import com.example.wokulskidashboard.ui.components.IncomeForm
import com.example.wokulskidashboard.ui.components.TransactionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val transactions = remember { mutableStateListOf<Transaction>() }

    var incomeName by remember { mutableStateOf("") }
    var incomeAmount by remember { mutableStateOf("") }
    var incomeCategory by remember { mutableStateOf("Sklep") }
    var incomeNameError by remember { mutableStateOf("") }
    var incomeAmountError by remember { mutableStateOf("") }
    var incomeCategoryError by remember { mutableStateOf("") }

    var expenseName by remember { mutableStateOf("") }
    var expenseAmount by remember { mutableStateOf("") }
    var expenseCategory by remember { mutableStateOf("Wydatki osobiste") }
    var expenseUnnecessary by remember { mutableStateOf(false) }
    var expenseNameError by remember { mutableStateOf("") }
    var expenseAmountError by remember { mutableStateOf("") }
    var expenseCategoryError by remember { mutableStateOf("") }

    val categories = listOf("Sklep", "Kamienica", "Wydatki osobiste")

    val balance = transactions.fold(0.0) { acc, t ->
        if (t.isExpense) acc - t.amount else acc + t.amount
    }

    fun zapiszPrzychod() {
        var valid = true

        if (incomeName.isBlank()) {
            incomeNameError = "Nazwa nie moze byc pusta!"
            valid = false
        } else {
            incomeNameError = ""
        }

        val kwota = incomeAmount.toDoubleOrNull()
        if (incomeAmount.isBlank()) {
            incomeAmountError = "Kwota nie moze byc pusta!"
            valid = false
        } else if (kwota == null) {
            incomeAmountError = "Wpisz prawidlowa liczbe!"
            valid = false
        } else if (kwota <= 0.0) {
            incomeAmountError = "Kwota musi byc wieksza od zera!"
            valid = false
        } else {
            incomeAmountError = ""
        }

        if (incomeCategory.isBlank()) {
            incomeCategoryError = "Wybierz kategorie!"
            valid = false
        } else {
            incomeCategoryError = ""
        }

        if (valid && kwota != null) {
            transactions.add(Transaction(incomeName.trim(), kwota, false, incomeCategory))
            incomeName = ""
            incomeAmount = ""
            incomeCategory = categories.first()
        }
    }

    fun zapiszWydatek() {
        var valid = true

        if (expenseName.isBlank()) {
            expenseNameError = "Cel wydatku nie moze byc pusty!"
            valid = false
        } else {
            expenseNameError = ""
        }

        val kwota = expenseAmount.toDoubleOrNull()
        if (expenseAmount.isBlank()) {
            expenseAmountError = "Kwota nie moze byc pusta!"
            valid = false
        } else if (kwota == null) {
            expenseAmountError = "Wpisz prawidlowa liczbe!"
            valid = false
        } else if (kwota <= 0.0) {
            expenseAmountError = "Kwota musi byc wieksza od zera!"
            valid = false
        } else {
            expenseAmountError = ""
        }

        if (expenseCategory.isBlank()) {
            expenseCategoryError = "Wybierz kategorie!"
            valid = false
        } else {
            expenseCategoryError = ""
        }

        if (valid && kwota != null) {
            transactions.add(Transaction(expenseName.trim(), kwota, true, expenseCategory, expenseUnnecessary))
            expenseName = ""
            expenseAmount = ""
            expenseCategory = "Wydatki osobiste"
            expenseUnnecessary = false
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Wokulski i Spolka",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                BalanceHeader(balance = balance)
            }

            item {
                IncomeForm(
                    name = incomeName,
                    amount = incomeAmount,
                    category = incomeCategory,
                    categories = categories,
                    onNameChange = { incomeName = it },
                    onAmountChange = { incomeAmount = it },
                    onCategoryChange = { incomeCategory = it },
                    onSave = { zapiszPrzychod() },
                    nameError = incomeNameError,
                    amountError = incomeAmountError,
                    categoryError = incomeCategoryError
                )
            }

            item {
                ExpenseForm(
                    name = expenseName,
                    amount = expenseAmount,
                    category = expenseCategory,
                    categories = categories,
                    isUnnecessary = expenseUnnecessary,
                    onNameChange = { expenseName = it },
                    onAmountChange = { expenseAmount = it },
                    onCategoryChange = { expenseCategory = it },
                    onUnnecessaryChange = { expenseUnnecessary = it },
                    onSave = { zapiszWydatek() },
                    nameError = expenseNameError,
                    amountError = expenseAmountError,
                    categoryError = expenseCategoryError
                )
            }

            if (transactions.isNotEmpty()) {
                item {
                    HorizontalDivider()
                    Text(
                        text = "Ostatnie transakcje (${transactions.size})",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            items(transactions.reversed().take(5)) { transakcja ->
                TransactionCard(transaction = transakcja)
            }
        }
    }
}
