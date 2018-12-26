package com.boolenull.booleanhub.utils

import com.boolenull.booleanhub.MyApplication

fun Int.getString() = MyApplication.context.getString(this)