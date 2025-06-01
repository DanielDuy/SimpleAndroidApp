package no.uio.ifi.in2000.daniedm.oblig1.ui.palindrome

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.ImeAction
import no.uio.ifi.in2000.daniedm.oblig1.isPalindrome

fun checkPalindromeResult(inputString: String): String {
    if (isPalindrome(inputString)) {
        return "It's Palindrome!"
    }
    return "It's not Palindrome."
}

@Composable
fun CheckPalindrome() {
    var checkResult by remember { mutableStateOf("") }
    var inputText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    Column (
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Enter text here:") },
            maxLines = 1,
            modifier = Modifier
                .focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions (
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    checkResult = checkPalindromeResult(inputText)
                    keyboardController?.hide()
                }
            )
        )
        TextField(
            value = checkResult,
            onValueChange = {},
            maxLines = 1,
            readOnly = true,
            label = { Text("Is this palindrome:") }
        )
        Button(
            modifier = Modifier,
            onClick = {
                checkResult = checkPalindromeResult(inputText)
                keyboardController?.hide()
            }
        ) {
            Text(text = "Check")
        }
    }

}
