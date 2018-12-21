package com.boolenull.booleanhub.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.boolenull.booleanhub.R
import com.boolenull.booleanhub.model.RepositoryModel
import com.boolenull.booleanhub.presenter.RepositoryPresenter
import com.boolenull.booleanhub.view.RepositoryView
import kotlinx.android.synthetic.main.activity_main.*

@InjectPresenter
lateinit var repositoryPresenter: RepositoryPresenter

class MainActivity : MvpAppCompatActivity(), RepositoryView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repositoryPresenter.startLoadOrUpdateRepository()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun endProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showError(rid: Int) {
        Toast.makeText(this, getString(rid), Toast.LENGTH_LONG).show()
    }

    override fun setRepositoryList(mutableList : MutableList<RepositoryModel>) {

    }

    override fun showEmpty() {
        tvEmpty.visibility = View.VISIBLE
    }

    override fun hideEmpty() {
        tvEmpty.visibility = View.GONE
    }
}
