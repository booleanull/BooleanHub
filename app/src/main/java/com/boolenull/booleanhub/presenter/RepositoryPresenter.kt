package com.boolenull.booleanhub.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.boolenull.booleanhub.R
import com.boolenull.booleanhub.model.RepositoryModel
import com.boolenull.booleanhub.provider.RepositoryProvider
import com.boolenull.booleanhub.view.RepositoryView


@InjectViewState
class RepositoryPresenter : MvpPresenter<RepositoryView>() {

    init {
        startLoadOrUpdateRepository()
    }

    fun startLoadOrUpdateRepository() {
        viewState.showProgress()
        RepositoryProvider(this).testLoadRepository(true)
    }

    fun errorLoadOrUpdateRepository(rid: Int) {
        viewState.endProgress()
        viewState.showError(rid)
        viewState.showEmpty()
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