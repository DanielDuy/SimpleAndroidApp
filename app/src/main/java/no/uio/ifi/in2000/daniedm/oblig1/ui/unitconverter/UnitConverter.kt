package no.uio.ifi.in2000.daniedm.oblig1.ui.unitconverter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import no.uio.ifi.in2000.daniedm.oblig1.ConverterUnits
import no.uio.ifi.in2000.daniedm.oblig1.converter

fun convertUnitResult(inputAmount: String, chosenUnit: ConverterUnits): String {
    if (inputAmount == "") {
        return "0"
    }
    val inputToInt = inputAmount.toIntOrNull()
    if (inputToInt != null) {
        return converter(inputToInt, chosenUnit).toString()
    }
    return converter(0, chosenUnit).toString()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvertUnit() {
    var inputNumber by remember { mutableStateOf("") }
    var resultConvertion by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val unitOptions = listOf(ConverterUnits.OUNCE, ConverterUnits.CUP, ConverterUnits.GALLON, ConverterUnits.HOGSHEAD)
    var isExpanded by remember { mutableStateOf(false) }
    var unitSelected by remember { mutableStateOf(unitOptions[0]) }
    val pattern = remember { Regex("^\\d+\$") }
    Column (
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = inputNumber,
            onValueChange = { if(it.matches(pattern)) {
                inputNumber = it
                resultConvertion = convertUnitResult(inputNumber, unitSelected)
            } },
            label = { Text("Enter number here:") },
            maxLines = 1,
            modifier = Modifier
                .focusRequester(focusRequester)
                .onFocusEvent { focusState ->
                    if (focusState.isFocused) {
                        keyboardController?.show()
                    }
                },
            keyboardOptions = KeyboardOptions (
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded}
        ) {
            TextField(
                modifier = Modifier.menuAnchor(),
                value = unitSelected.toString(),
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
            )
            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false}) {
                unitOptions.forEachIndexed { index, text ->
                    DropdownMenuItem(
                        text = { Text(text = text.toString()) },
                        onClick = {
                            unitSelected = unitOptions[index]
                            isExpanded = false
                            resultConvertion = convertUnitResult(inputNumber, unitSelected)
                        }

                    )
                }
            }
            Text(text = "Select a unit:")
        }
        TextField(
            value = resultConvertion,
            onValueChange = {},
            maxLines = 1,
            readOnly = true,
            label = { Text("Converted to liter:") }
        )
    }

}
