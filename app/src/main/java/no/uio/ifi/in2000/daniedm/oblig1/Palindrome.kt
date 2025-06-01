package no.uio.ifi.in2000.daniedm.oblig1

fun isPalindrome(inputString: String): Boolean {
    return inputString.lowercase() == inputString.lowercase().reversed()
}