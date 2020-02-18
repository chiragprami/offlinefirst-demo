package com.chirag.localstorage.extension

import android.content.Context
import android.util.Log
import android.widget.Toast

const val  TAG = "mvvm_realm_log"

fun logv(tag:String = "$TAG", message:String){
    Log.v(tag,message)
}

fun Context.logv(message: String){
    Log.v(this.javaClass.simpleName,message)
}

fun Context.toast(message: String,isLongToast:Boolean = true){
    Toast.makeText(this,message,if (isLongToast)Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}
