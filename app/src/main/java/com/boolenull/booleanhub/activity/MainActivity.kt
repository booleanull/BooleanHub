package com.boolenull.booleanhub.activity

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.boolenull.booleanhub.MyApplication
import com.boolenull.booleanhub.R
import com.boolenull.booleanhub.adapter.RepositoryAdapter
import com.boolenull.booleanhub.model.RepositoryModel
import com.boolenull.booleanhub.presenter.RepositoryPresenter
import com.boolenull.booleanhub.utils.SpacesItemDecoration
import com.boolenull.booleanhub.view.RepositoryView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : MvpAppCompatActivity(), RepositoryView, SearchView.OnQueryTextListener,
    SwipeRefreshLayout.OnRefreshListener {

    @InjectPresenter
    lateinit var repositoryPresenter: RepositoryPresenter

    lateinit var repositoryAdapter: RepositoryAdapter
    lateinit var searchView: SearchView
    lateinit var menuItem: MenuItem
    var searchString: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repositoryAdapter = RepositoryAdapter()
        recyclerView.adapter = repositoryAdapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, OrientationHelper.VERTICAL, false)
        //recyclerView.addItemDecoration(SpacesItemDecoration(this, 8))
        recyclerView.setHasFixedSize(true)

        swipeRefreshLayout.setOnRefreshListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_repository, menu)
        menuItem = menu!!.findItem(R.id.action_search)
        searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        if (!TextUtils.isEmpty(searchString)) {
            menuItem.expandActionView();
            searchView.setQuery(searchString, true);
            searchView.isIconified = false
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(text: String?): Boolean {
        repositoryPresenter.viewState.setRepositorySearch(text!!)
        return true
    }

    override fun onRefresh() {
        repositoryPresenter.startLoadOrUpdateRepository(true)
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

    override fun setRepositoryList(mutableList: MutableList<RepositoryModel>) {
        repositoryAdapter.updateRepositoryList(mutableList)
        repositoryPresenter.viewState.setRepositorySearch(searchString)
        tvOnline.visibility = View.VISIBLE
        tvOffline.visibility = View.GONE
    }

    override fun showProgress(isRefresh: Boolean) {
        if (!isRefresh) {
            progressBar.visibility = View.VISIBLE
            layout.visibility = View.GONE
        }
        else
            swipeRefreshLayout.isRefreshing = true
    }

    override fun endProgress() {
        layout.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showError(rid: Int) {
        tvOnline.visibility = View.GONE
        tvOffline.visibility = View.VISIBLE
        Toast.makeText(this, getString(rid), Toast.LENGTH_LONG).show()
    }

    override fun showEmpty() {
        tvEmpty.visibility = View.VISIBLE
    }

    override fun hideEmpty() {
        tvEmpty.visibility = View.GONE
    }
}
