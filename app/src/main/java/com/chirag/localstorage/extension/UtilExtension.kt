package com.chirag.localstorage.extension

import android.content.Context
import android.util.Log
import android.widget.Toast

fun logv(messsage: String){
    Log.v("mvvm_log--->",messsage)
}

fun Context.toast(messsage: String,isLongToast:Boolean = true){
    Toast.makeText(this,messsage,if (isLongToast)Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}