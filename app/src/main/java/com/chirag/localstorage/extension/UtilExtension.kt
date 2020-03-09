package com.chirag.localstorage.extension

import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

fun logv(messsage: String){
    Log.v("mvvm_log--->",messsage)
}

fun Context.toast(messsage: String,isLongToast:Boolean = true) {
    Toast.makeText(this, messsage, if (isLongToast) Toast.LENGTH_LONG else Toast.LENGTH_SHORT)
        .show()
}


fun AppCompatEditText.setDebounce(f: () -> Unit) {

}
