package com.dua.ynabapplication.utils.extension

import android.content.Context
import android.widget.Toast
import com.dua.ynabapplication.R
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.googlematerial.GoogleMaterial
import com.mikepenz.iconics.utils.sizeDp
import es.dmoral.toasty.Toasty
import androidx.fragment.app.Fragment as SupportFragment

fun Context.toastInfo(message: String, duration: Int = Toast.LENGTH_SHORT) =
        Toasty.info(this, message, duration).show()

fun Context.toastError(message: String?, duration: Int = Toast.LENGTH_SHORT) =
        Toasty.error(this, message.toString(), duration).show()

fun Context.toastSuccess(message: String, duration: Int = Toast.LENGTH_SHORT) =
        Toasty.success(this, message, duration).show()

fun SupportFragment.toastInfo(message: String, duration: Int = Toast.LENGTH_SHORT) =
        requireActivity().toastInfo(message, duration)

fun SupportFragment.toastError(message: String?, duration: Int = Toast.LENGTH_SHORT) =
        requireActivity().toastError(message, duration)

fun SupportFragment.toastSuccess(message: String, duration: Int = Toast.LENGTH_SHORT) =
        requireActivity().toastSuccess(message, duration)

fun SupportFragment.toastOffline(message: String, duration: Int = Toast.LENGTH_SHORT) =
       Toasty.custom(requireActivity(), message, IconicsDrawable(requireContext()).apply {
           icon = GoogleMaterial.Icon.gmd_cloud_off
           sizeDp = 24
       }, getCompatColor(R.color.darkBlue), getCompatColor(R.color.md_white_1000),
               duration, true, true).show()



