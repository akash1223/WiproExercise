package com.appcretor.wiproexercise.ui.base



import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
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

    protected fun hideKeyboard() {
        val view = requireActivity().currentFocus
        if (view != null) {
            val inputManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    protected fun showToast(msg: String, isLengthLong: Boolean = false) {
        if (isLengthLong) {
            Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
            return
        }
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
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


