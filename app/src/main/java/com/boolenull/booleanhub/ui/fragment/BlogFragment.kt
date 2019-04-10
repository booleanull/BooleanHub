package com.boolenull.booleanhub.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.boolenull.booleanhub.R
import kotlinx.android.synthetic.main.fragment_blog.view.*

class BlogFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_blog, container, false)
        view.button.setOnClickListener {
            Toast.makeText(view.context, "s", Toast.LENGTH_LONG).show()
        }
        return view
    }
}