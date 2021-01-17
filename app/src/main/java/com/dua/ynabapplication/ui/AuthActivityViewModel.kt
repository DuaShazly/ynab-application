package com.dua.ynabapplication.ui

import android.accounts.AccountManager
import android.app.Application
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dua.ynabapplication.data.local.account.AuthenticatorManager
import com.dua.ynabapplication.data.local.dao.AppDatabase
import com.dua.ynabapplication.data.local.pref.AppPref
import com.dua.ynabapplication.data.remote.YnabClient
import com.dua.ynabapplication.data.remote.api.AccountsService
import com.dua.ynabapplication.data.remote.api.OAuthService
import com.dua.ynabapplication.repository.BaseViewModel
import com.dua.ynabapplication.repository.account.AccountRepository
import com.dua.ynabapplication.repository.models.auth.AuthModel
import com.dua.ynabapplication.utils.Constants
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

import java.io.File
import java.net.UnknownServiceException
import java.security.cert.CertificateException

class AuthActivityViewModel(application: Application): BaseViewModel(application) {

    val isShowingBack: MutableLiveData<Boolean> = MutableLiveData()
    val baseUrl: MutableLiveData<String> = MutableLiveData()
    val isAuthenticated: MutableLiveData<Boolean> = MutableLiveData()
    val showInfoMessage: MutableLiveData<String> = MutableLiveData()
    val showErrorMessage: MutableLiveData<String> = MutableLiveData()

    private val applicationContext = getApplication<Application>()
    private val accountManager = AccountManager.get(applicationContext)
    private val customCaFile by lazy { File(applicationContext.filesDir.toString() + File.pathSeparator +  "user_custom.pem") }


    private lateinit var repository: AccountRepository


    fun authViaPat(baseUrl: String, accessToken: String, fileUri: Uri?) {
        if(accessToken.isEmpty()){
            showInfoMessage.postValue("Personal Access Token Required!")
            return
        }
        if(baseUrl.isEmpty()){
            showInfoMessage.postValue("Base URL Required!")
            return
        }
        isLoading.postValue(true)
        if(fileUri != null && fileUri.toString().isNotBlank()) {
        }
        authInit(accessToken, baseUrl)
        val accountDao = AppDatabase.getInstance(applicationContext).accountDataDao()
        val accountsService = genericService().create(AccountsService::class.java)
        repository = AccountRepository(accountDao, accountsService)
        viewModelScope.launch(Dispatchers.IO){
            try {
                repository.authViaPat()
                AuthenticatorManager(accountManager).authMethod = "pat"
                isAuthenticated.postValue(true)
            } catch (exception: UnknownServiceException){
                showErrorMessage.postValue("http is not supported. Please use https")
                isAuthenticated.postValue(false)
            } catch (certificateException: CertificateException){
                showErrorMessage.postValue("Are you using self signed cert?")
                isAuthenticated.postValue(false)
            } catch (exception: Exception){
                showErrorMessage.postValue(exception.localizedMessage)
                isAuthenticated.postValue(false)
            }
            isLoading.postValue(false)
        }
    }


    fun authViaOauth(baseUrl: String, clientSecret: String, clientId: String, fileUri: Uri?): Boolean{
        if(baseUrl.isEmpty()){
            showInfoMessage.postValue("Base URL Required!")
            return false
        }
        if(clientSecret.isEmpty()){
            showInfoMessage.postValue("Client Secret Required!")
            return false
        }
        if(clientId.isEmpty()){
            showInfoMessage.postValue("Client ID Required!")
            return false
        }
        if(fileUri != null && fileUri.toString().isNotBlank()) {
//            FileUtils.saveCaFile(fileUri, getApplication())
        }
        authInit("", baseUrl)
        accManager.clientId = clientId
        accManager.secretKey = clientSecret
        return true
    }

    fun getAccessToken(code: String, isDemo: Boolean = false){
        isLoading.postValue(true)
        if (!code.isBlank()) {

            isAuthenticated.postValue(false)
            showErrorMessage.postValue("Bearer Token contains invalid Characters!")
        } else {
            var networkCall: Response<AuthModel>?
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val redirectUri = if(isDemo){
                       Constants.REDIRECT_URI
                    } else {
                        Constants.REDIRECT_URI
                    }
                    val oAuthService = genericService().create(OAuthService::class.java)
                    networkCall = oAuthService.getAccessToken(code.trim(), accManager.clientId,
                            accManager.secretKey, redirectUri)
                    val authResponse = networkCall?.body()
                    val errorBody = networkCall?.errorBody()
                    if (authResponse != null && networkCall?.isSuccessful != false) {
                        accManager.accessToken = authResponse.access_token.trim()
                        accManager.refreshToken = authResponse.refresh_token.trim()
                        accManager.tokenExpiry = authResponse.expires_in
                        accManager.authMethod = "oauth"
                        isAuthenticated.postValue(true)
                    } else if(errorBody != null){
                        val errorBodyMessage = String(errorBody.bytes())
                        showErrorMessage.postValue(errorBodyMessage)
                        isAuthenticated.postValue(false)
                    }
                } catch (exception: UnknownServiceException){
                    showErrorMessage.postValue("http is not supported. Please use https")
                    isAuthenticated.postValue(false)
                } catch (certificateException: CertificateException){
                    showErrorMessage.postValue("Are you using self signed cert?")
                    isAuthenticated.postValue(false)
                } catch (exception: Exception){
                    showErrorMessage.postValue(exception.localizedMessage)
                    isAuthenticated.postValue(false)
                }
            }
            isLoading.postValue(false)
        }
    }


    fun getUser(): LiveData<Boolean> {
        val apiOk: MutableLiveData<Boolean> = MutableLiveData()
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            apiOk.postValue(false)
        }) {
            apiOk.postValue(true)
        }
        return apiOk
    }

    fun userSystem(): LiveData<Boolean> {
        val apiOk: MutableLiveData<Boolean> = MutableLiveData()
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            apiOk.postValue(false)
        }){
            apiOk.postValue(true)
        }
        return apiOk
    }

    private fun authInit(accessToken: String, baseUrl: String){
        YnabClient.destroyInstance()
        accManager.destroyAccount()
        AuthenticatorManager(accountManager).initializeAccount()
        AuthenticatorManager(accountManager).accessToken = accessToken.trim()
        AppPref(sharedPref).baseUrl = baseUrl
    }
}