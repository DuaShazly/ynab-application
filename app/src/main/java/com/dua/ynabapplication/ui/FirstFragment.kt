package com.dua.ynabapplication.ui

import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.core.net.toUri
import com.dua.ynabapplication.utils.extension.getViewModel
import kotlinx.android.synthetic.main.content_main.*
import androidx.fragment.app.Fragment
import com.dua.ynabapplication.R
import com.dua.ynabapplication.utils.extension.hideKeyboard


class FirstFragment : Fragment() {

    private val sharedPref by lazy { PreferenceManager.getDefaultSharedPreferences(requireContext()) }
    private val authViewModel by lazy { getViewModel(AuthActivityViewModel::class.java) }
    private lateinit var fileUri: Uri

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signInButtonClick()

    }


    private fun signInButtonClick(){
        login_button.setOnClickListener {
            hideKeyboard()
            authViewModel.authViaPat(url_edittext.toString(),
                access_edittext.toString(), "".toUri())
        }
    }

}