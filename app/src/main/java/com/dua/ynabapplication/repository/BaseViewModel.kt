package com.dua.ynabapplication.repository

import android.accounts.AccountManager
import android.app.Application
import android.preference.PreferenceManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.viewModelScope
import com.dua.ynabapplication.data.local.account.AuthenticatorManager
import com.dua.ynabapplication.data.local.pref.AppPref
import com.dua.ynabapplication.data.remote.YnabClient
import kotlinx.coroutines.*
import retrofit2.Retrofit

open class BaseViewModel(application: Application) : AndroidViewModel(application){

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val apiResponse: MutableLiveData<String> = MutableLiveData()
    protected val accManager by lazy { AuthenticatorManager(AccountManager.get(getApplication()))  }
    protected val sharedPref by lazy { PreferenceManager.getDefaultSharedPreferences(getApplication()) }

    protected fun genericService(): Retrofit {
        val cert = AppPref(sharedPref).certValue
//        return if (AppPref(sharedPref).isCustomCa) {
//            val customCa = CustomCa(File(getApplication<Application>().filesDir.path + "/user_custom.pem"))
//            YnabClient.getClient(AppPref(sharedPref).baseUrl,
//                    accManager.accessToken, cert, customCa.getCustomTrust(), customCa.getCustomSSL())
//        } else {
        return     YnabClient.getClient(
            AppPref(sharedPref).baseUrl,
                    accManager.accessToken, cert, null, null)
//        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}