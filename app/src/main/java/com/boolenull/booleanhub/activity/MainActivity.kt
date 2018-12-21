package com.boolenull.booleanhub.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.boolenull.booleanhub.R
import com.boolenull.booleanhub.view.RepositoryView

class MainActivity : MvpAppCompatActivity(), RepositoryView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showProgress() {
    }

    override fun endProgress() {
    }

    override fun showError() {
    }

    override fun showRepository() {
    }
}
