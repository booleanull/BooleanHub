package com.boolenull.booleanhub.ui.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.boolenull.booleanhub.data.RepositoryProvider
import com.boolenull.booleanhub.ui.model.RepositoryModel
import com.boolenull.booleanhub.ui.view.RepositoryView


@InjectViewState
class RepositoryPresenter : MvpPresenter<RepositoryView>() {

    init {
        startLoadOrUpdateRepository(false)
    }

    fun startLoadOrUpdateRepository(isRefresh: Boolean) {
        viewState.showProgress(isRefresh)
        RepositoryProvider(this).loadRepository()
    }

    fun errorLoadOrUpdateRepository(repositories: List<RepositoryModel>, rid: Int) {
        viewState.endProgress()
        viewState.showEmpty()
        viewState.setRepositoryList(repositories)
        viewState.showError(rid)
    }

    fun finishLoadOrUpdateRepository(repositories: List<RepositoryModel>) {
        viewState.endProgress()
        if (repositories.isEmpty()) {
            viewState.showEmpty()
        } else {
            viewState.hideEmpty()
            viewState.setRepositoryList(repositories)
        }
    }
}