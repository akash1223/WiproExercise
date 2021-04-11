package com.appcretor.wiproexercise.ui.base



import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.appcretor.wiproexercise.R
import com.appcretor.wiproexercise.utils.CustomProgressBar
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {

    abstract fun setup()
    private var mProgressDialog: CustomProgressBar? = null
    lateinit var mNavController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        mNavController = Navigation.findNavController(view)
    }

    protected fun showSnackBar(view: View, message: String, isLengthLong: Boolean = false) {
        if (isLengthLong) {
            Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .show()
            return
        }
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            .show()
    }
    fun showAlertDialogWithOK(
        title: String,
        message: String,
        okBtnCallback: (() -> Unit)?
    ) {
        val alertDialog = AlertDialog.Builder(requireActivity())
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setCancelable(false)
        alertDialog.setPositiveButton(getString(R.string.ok)) { _, _ ->
            okBtnCallback?.invoke()
        }
        alertDialog.show()
    }

    protected fun showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = CustomProgressBar(requireActivity())
        }
        if (!mProgressDialog?.isShowing!!) {
            mProgressDialog?.show()
        }
    }


    open fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog?.isShowing!!) {
            mProgressDialog?.dismiss()
        }
    }


}


