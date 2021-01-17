package com.dua.myapplication.ui.base

import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.annotation.SuppressLint
import android.os.Bundle
import com.dua.ynabapplication.base.BaseActivity

@SuppressLint("Registered")
open class AccountAuthenticator_Activity: BaseActivity() {

    private var accountAuthenticatorResponse: AccountAuthenticatorResponse? = null
    private var resultBundle: Bundle? = null

    fun setAccountAuthenticatorResult(result: Bundle){
        resultBundle = result
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        accountAuthenticatorResponse = intent.getParcelableExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE)
        if(accountAuthenticatorResponse != null){
            accountAuthenticatorResponse?.onRequestContinued()
        }
    }

    override fun finish() {
        if (accountAuthenticatorResponse != null){
            if(resultBundle != null){
                accountAuthenticatorResponse?.onResult(resultBundle)
            } else {
                accountAuthenticatorResponse?.onError(AccountManager.ERROR_CODE_CANCELED,
                        "canceled")
            }
            accountAuthenticatorResponse = null
        }
        super.finish()
    }
}