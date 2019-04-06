package com.boolenull.booleanhub.ui.fragment

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.boolenull.booleanhub.R
import com.boolenull.booleanhub.ui.adapter.RepositoryAdapter
import com.boolenull.booleanhub.ui.model.RepositoryModel
import com.boolenull.booleanhub.ui.presenter.RepositoryPresenter
import com.boolenull.booleanhub.ui.view.RepositoryView
import com.boolenull.booleanhub.utils.extension.MyOnQueryTextListener
import kotlinx.android.synthetic.main.fragment_repository.*
import kotlinx.android.synthetic.main.fragment_repository.view.*

class RepositoryFragment: MvpAppCompatFragment(), RepositoryView {

    @InjectPresenter
    lateinit var repositoryPresenter: RepositoryPresenter

    lateinit var repositoryAdapter: RepositoryAdapter
    lateinit var searchView: SearchView
    var menuItem: MenuItem? = null
    var searchString: String = ""

    private val refreshListener = SwipeRefreshLayout.OnRefreshListener {
        repositoryPresenter.startLoadOrUpdateRepository(true)
    }

    private val queryTextListener = MyOnQueryTextListener {
        //repositoryPresenter.viewState.setRepositorySearch(it)
        Toast.makeText(this.context, it, Toast.LENGTH_LONG).show()
        true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_repository, container, false)
        repositoryAdapter = RepositoryAdapter()
        view.recyclerView.adapter = repositoryAdapter
        view.recyclerView.layoutManager = LinearLayoutManager(context, OrientationHelper.VERTICAL, false)
        view.recyclerView.setHasFixedSize(true)
        view.swipeRefreshLayout.setOnRefreshListener(refreshListener)
        return view
    }

    override fun setRepositorySearch(text: String) {
        searchString = text

        repositoryAdapter.filter(text)
        if (repositoryAdapter.itemCount == 0) {
            showEmpty()
        } else {
            hideEmpty()
        }
    }

    override fun setRepositoryList(repositories: List<RepositoryModel>) {
        repositoryAdapter.updateRepositoryList(repositories)
        repositoryPresenter.viewState.setRepositorySearch(searchString)
    }

    override fun showProgress(isRefresh: Boolean) {
        if (!isRefresh) {
            progressBar.visibility = View.VISIBLE
        } else swipeRefreshLayout.isRefreshing = true
    }

    override fun endProgress() {
        progressBar.visibility = View.GONE
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showError(rid: Int) {
        Toast.makeText(context, getString(rid), Toast.LENGTH_LONG).show()
    }

    override fun showEmpty() {
        tvEmpty.visibility = View.VISIBLE
    }

    override fun hideEmpty() {
        tvEmpty.visibility = View.GONE
    }
}