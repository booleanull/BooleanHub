package com.boolenull.booleanhub.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.boolenull.booleanhub.R
import com.boolenull.booleanhub.ui.adapter.PageAdapter
import com.boolenull.booleanhub.ui.fragment.EnumFragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_blog -> {
                    viewPager.currentItem = BLOG.ordinal
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_repository -> {
                    viewPager.currentItem = REPOSITORY.ordinal
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_aboutme -> {
                    viewPager.currentItem = LINK.ordinal
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager.adapter = PageAdapter(supportFragmentManager)
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}

/*class MainActivity : MvpAppCompatActivity(), RepositoryView {

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
        Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repositoryAdapter = RepositoryAdapter()
        recyclerView.adapter = repositoryAdapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, OrientationHelper.VERTICAL, false)
        recyclerView.setHasFixedSize(true)

        swipeRefreshLayout.setOnRefreshListener(refreshListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_repository, menu)
        menuItem = menu?.findItem(R.id.action_search)
        searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(queryTextListener)

        if (!TextUtils.isEmpty(searchString)) {
            menuItem?.expandActionView()
            searchView.setQuery(searchString, true)
            searchView.isIconified = false
        }

        return super.onCreateOptionsMenu(menu)
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
        tvProfile.text = getString(R.string.booleanull)
    }

    override fun showProgress(isRefresh: Boolean) {
        if (!isRefresh) {
            progressBar.visibility = View.VISIBLE
            layout.visibility = View.GONE
        } else
            swipeRefreshLayout.isRefreshing = true
    }

    override fun endProgress() {
        layout.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showError(rid: Int) {
        tvProfile.text = getString(R.string.nointernet)
        Toast.makeText(this, getString(rid), Toast.LENGTH_LONG).show()
    }

    override fun showEmpty() {
        tvEmpty.visibility = View.VISIBLE
    }

    override fun hideEmpty() {
        tvEmpty.visibility = View.GONE
    }
}*/
