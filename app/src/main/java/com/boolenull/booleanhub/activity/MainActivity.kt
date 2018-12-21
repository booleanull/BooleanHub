package com.boolenull.booleanhub.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.boolenull.booleanhub.R
import com.boolenull.booleanhub.adapter.RepositoryAdapter
import com.boolenull.booleanhub.model.RepositoryModel
import com.boolenull.booleanhub.presenter.RepositoryPresenter
import com.boolenull.booleanhub.view.RepositoryView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpAppCompatActivity(), RepositoryView {

    @InjectPresenter
    lateinit var repositoryPresenter: RepositoryPresenter

    lateinit var repositoryAdapter: RepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repositoryAdapter = RepositoryAdapter()
        recyclerView.adapter = repositoryAdapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, OrientationHelper.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
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

    override fun setRepositoryList(mutableList: MutableList<RepositoryModel>) {
        repositoryAdapter.updateRepositoryList(mutableList)
    }

    override fun showEmpty() {
        tvEmpty.visibility = View.VISIBLE
    }

    override fun hideEmpty() {
        tvEmpty.visibility = View.GONE
    }
}
