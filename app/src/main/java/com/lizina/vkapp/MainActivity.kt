package com.lizina.vkapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.lizina.vkapp.ui.theme.VKAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val scope = rememberCoroutineScope()


            var text by remember { mutableStateOf("") }


            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) }
            ) { padding ->
                val context = LocalContext.current

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Введите текст") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(8.dp))


                    // 1. Явный Intent — открыть SecondActivity
                    OpenSecondActivity(text, scope, snackbarHostState)
                    Spacer(Modifier.height(8.dp))

                    // 3. Системный Intent — ACTION_SEND
                    OpenActionSend(text, scope, snackbarHostState)


                    Spacer(Modifier.height(16.dp))

                    // 2. Неявный Intent — ACTION_DIAL
                    OpenActionDial(scope, snackbarHostState)



                }
            }
        }
    }
}

// 1. Явный Intent — открыть SecondActivity
@Composable
fun OpenSecondActivity(
    text: String,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState){

    val context = LocalContext.current

    Button(
        onClick = {
            if (text.isBlank()) {
                scope.launch {
                    snackbarHostState.showSnackbar("Введите текст для передачи в SecondActivity")
                }
                return@Button
            }
            val intent = Intent(context, SecondActivity::class.java).apply {
                putExtra("EXTRA_TEXT", text)
            }
            (context as ComponentActivity).startActivity(intent)
        },
        modifier = Modifier.fillMaxWidth()
    ) { Text("Открыть вторую Activity") }
}


// 2. Неявный Intent — ACTION_DIAL
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpenActionDial(
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState
) {
    val context = LocalContext.current

    var phone by remember { mutableStateOf("") }          // только цифры номера
    var selectedCountry by remember { mutableStateOf(countries.first { it.isoCode == "RU" }) }
    var countryMenuExpanded by remember { mutableStateOf(false) }
    // --- Поле телефона с выбором кода страны ---
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Dropdown выбора страны
        ExposedDropdownMenuBox(
            expanded = countryMenuExpanded,
            onExpandedChange = { countryMenuExpanded = it },
            modifier = Modifier.width(130.dp)
        ) {
            OutlinedTextField(
                value = "${selectedCountry.flag} ${selectedCountry.dialCode}",
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = countryMenuExpanded
                    )
                },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = countryMenuExpanded,
                onDismissRequest = { countryMenuExpanded = false }
            ) {
                countries.forEach { country ->
                    DropdownMenuItem(
                        text = { Text("${country.flag} ${country.dialCode}  ${country.name}") },
                        onClick = {
                            selectedCountry = country
                            countryMenuExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.width(8.dp))

        // Поле ввода номера — цифровая клавиатура
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it.filter { c -> c.isDigit() } },
            placeholder = { Text("Номер телефона") },
            singleLine = true,
            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }

    Spacer(Modifier.height(8.dp))


    Button(
        onClick = {
            if (phone.isBlank()) {
                scope.launch {
                    snackbarHostState.showSnackbar("Введите номер телефона")
                }
                return@Button
            }
            // Валидация через libphonenumber
            if (!PhoneValidator.isValid(phone, selectedCountry.isoCode)) {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        "Неверный номер для ${selectedCountry.name}. " +
                                "Пример: 9991234567"
                    )
                }
                return@Button
            }
            // Форматируем в E.164 и звоним
            val e164 = PhoneValidator.toE164(phone, selectedCountry.isoCode)
            val dialIntent = Intent(Intent.ACTION_DIAL, "tel:$e164".toUri())
            if (dialIntent.resolveActivity(context.packageManager) != null) {
                (context as ComponentActivity).startActivity(dialIntent)
            } else {
                scope.launch {
                    snackbarHostState.showSnackbar("Нет приложения для звонков")
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) { Text("Позвонить другу") }
}

// 3. Системный Intent — ACTION_SEND
@Composable
fun OpenActionSend(
    text: String,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState
){
    val context = LocalContext.current

    Button(
        onClick = {
            if (text.isBlank()) {
                scope.launch {
                    snackbarHostState.showSnackbar("Введите текст для отправки")
                }
                return@Button
            }
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, text)
            }
            val chooser = Intent.createChooser(shareIntent, "Поделиться через...")
            if (shareIntent.resolveActivity(context.packageManager) != null) {
                (context as ComponentActivity).startActivity(chooser)
            } else {
                scope.launch {
                    snackbarHostState.showSnackbar("Нет приложений для обмена")
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) { Text("Поделиться текстом") }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VKAppTheme {
        Greeting("Android")
    }
}