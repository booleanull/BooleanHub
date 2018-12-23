package com.boolenull.booleanhub.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.boolenull.booleanhub.data.RepositoryProvider
import com.boolenull.booleanhub.model.RepositoryModel
import com.boolenull.booleanhub.view.RepositoryView


@InjectViewState
class RepositoryPresenter : MvpPresenter<RepositoryView>() {

    init {
        startLoadOrUpdateRepository(false)
    }

    fun startLoadOrUpdateRepository(isRefresh: Boolean) {
        viewState.showProgress(isRefresh)
        RepositoryProvider(this).loadRepository()
    }

    fun errorLoadOrUpdateRepository(mutableList: MutableList<RepositoryModel>, rid: Int) {
        viewState.endProgress()
        viewState.showError(rid)
        viewState.showEmpty()
        viewState.setRepositoryList(mutableList)
    }

    fun finishLoadOrUpdateRepository(mutableList: MutableList<RepositoryModel>) {
        viewState.endProgress()
        if (mutableList.isEmpty()) {
            viewState.showEmpty()
        } else {
            viewState.hideEmpty()
            viewState.setRepositoryList(mutableList)
        }
    }
}