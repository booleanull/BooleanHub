package com.boolenull.booleanhub.utils.extension

import android.support.v7.widget.SearchView

open class MyOnQueryTextListener(private val change: (string: String) -> Boolean): SearchView.OnQueryTextListener {

    override fun onQueryTextSubmit(string: String?): Boolean = false

    override fun onQueryTextChange(string: String?): Boolean {
        string?.let {
            return change.invoke(it)
        }
        return false
    }
}