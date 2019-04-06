package com.boolenull.booleanhub.ui.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.boolenull.booleanhub.ui.model.RepositoryModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepositoryView: MvpView {

    fun setRepositoryList(repositories: List<RepositoryModel>)

    fun setRepositorySearch(text: String)

    fun showProgress(isRefresh: Boolean)

    fun endProgress()

    @StateStrategyType(SkipStrategy::class)
    fun showError(rid: Int)

    fun showEmpty()

    fun hideEmpty()
}