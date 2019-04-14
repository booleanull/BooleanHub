package com.boolenull.booleanhub.ui.fragment

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.boolenull.booleanhub.R
import com.boolenull.booleanhub.ui.adapter.RepositoryAdapter
import com.boolenull.booleanhub.ui.fragment.EnumFragment.REPOSITORY
import com.boolenull.booleanhub.ui.model.RepositoryModel
import com.boolenull.booleanhub.ui.presenter.RepositoryPresenter
import com.boolenull.booleanhub.ui.view.RepositoryView
import com.boolenull.booleanhub.utils.view.SpacesItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_repository.*
import kotlinx.android.synthetic.main.fragment_repository.view.*

class RepositoryFragment: MvpAppCompatFragment(), RepositoryView {

    @InjectPresenter
    lateinit var repositoryPresenter: RepositoryPresenter

    private lateinit var repositoryAdapter: RepositoryAdapter

    private val refreshListener = SwipeRefreshLayout.OnRefreshListener {
        repositoryPresenter.startLoadOrUpdateRepository(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_repository, container, false)
        repositoryAdapter = RepositoryAdapter()
        view.recyclerView.adapter = repositoryAdapter
        view.recyclerView.layoutManager = LinearLayoutManager(context, OrientationHelper.VERTICAL, false)
        view.recyclerView.addItemDecoration(SpacesItemDecoration(context, 8))
        view.recyclerView.setHasFixedSize(true)
        view.swipeRefreshLayout.setOnRefreshListener(refreshListener)
        return view
    }

    override fun setRepositoryList(repositories: List<RepositoryModel>) {
        repositoryAdapter.updateRepositoryList(repositories)
    }

    override fun showProgress(isRefresh: Boolean) {
        if (!isRefresh) {
            progressBar.visibility = View.VISIBLE
        } else {
            swipeRefreshLayout.isRefreshing = true
        }
    }

    override fun endProgress() {
        progressBar.visibility = View.GONE
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showEmpty() {
        tvEmpty.visibility = View.VISIBLE
    }

    override fun hideEmpty() {
        tvEmpty.visibility = View.GONE
    }

    override fun showNetworkError() {
        tvError.visibility = View.VISIBLE
    }

    override fun hideNetworkError() {
        tvError.visibility = View.GONE
    }

    override fun showError(rid: Int) {
        activity?.let {
            if (it.viewPager.currentItem == REPOSITORY.ordinal) {
                Toast.makeText(context, getString(rid), Toast.LENGTH_SHORT).show()
            }
        }
    }
}