package com.dua.ynabapplication.utils.biometric

internal sealed class AuthenticationResult {
    internal data class Success(val cryptoObject: androidx.biometric.BiometricPrompt.CryptoObject?) :
            AuthenticationResult()
    internal data class RecoverableError(val code: Int, val message: CharSequence) :
            AuthenticationResult()
    internal data class UnrecoverableError(val code: Int, val message: CharSequence) :
            AuthenticationResult()
    internal object Failure : AuthenticationResult()
    internal object Cancelled : AuthenticationResult()
}