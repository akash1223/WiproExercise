package com.appcretor.wiproexercise.ui.base


import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar


abstract class BaseActivity :AppCompatActivity () {

    abstract fun setup()

    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       context=this
    }

}
