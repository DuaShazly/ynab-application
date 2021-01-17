package com.dua.ynabapplication.ui

import android.accounts.AccountManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.dua.ynabapplication.utils.extension.getViewModel
import com.dua.ynabapplication.data.local.account.AuthenticatorManager
import com.dua.myapplication.ui.base.AccountAuthenticator_Activity
import com.dua.ynabapplication.R

class AuthActivity : AccountAuthenticator_Activity() , FragmentManager.OnBackStackChangedListener{

    private lateinit var authActivityViewModel: AuthActivityViewModel
    private val accountManager by lazy { AuthenticatorManager(AccountManager.get(this))  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG","AuthActivity onCreate")
        setContentView(R.layout.activity_auth)
        authActivityViewModel = getViewModel(AuthActivityViewModel::class.java)
        if(savedInstanceState == null){
            supportFragmentManager.commit {
                add(R.id.container, FirstFragment())
            }
        } else {
            authActivityViewModel.isShowingBack.postValue(supportFragmentManager.backStackEntryCount > 0)
        }
        supportFragmentManager.addOnBackStackChangedListener(this)

    }


    override fun onBackStackChanged() {
        authActivityViewModel.isShowingBack.postValue(supportFragmentManager.backStackEntryCount > 0)

    }
}