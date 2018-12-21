package com.boolenull.booleanhub.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.boolenull.booleanhub.model.RepositoryModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepositoryView : MvpView {
    fun showProgress()
    fun endProgress()
    @StateStrategyType(SkipStrategy::class)
    fun showError(rid : Int)
    fun setRepositoryList(mutableList : MutableList<RepositoryModel>)
    fun showEmpty()
    fun hideEmpty()
}