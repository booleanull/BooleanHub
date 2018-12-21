package com.boolenull.booleanhub.presenter

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.boolenull.booleanhub.R
import com.boolenull.booleanhub.model.RepositoryModel
import com.boolenull.booleanhub.provider.RepositoryProvider
import com.boolenull.booleanhub.view.RepositoryView


@InjectViewState
class RepositoryPresenter() : MvpPresenter<RepositoryView>() {
    fun startLoadOrUpdateRepository() {
        viewState.showProgress()
        RepositoryProvider(this).testLoadRepository(true)
    }

    fun errorLoadOrUpdateRepository(message: String) {
        viewState.showError(R.string.answerfromservererror)
    }

    fun finishLoadOrUpdateRepository(mutableList : MutableList<RepositoryModel>) {
        viewState.endProgress()
        if(mutableList.isEmpty()) {
            viewState.showEmpty()
        }
        else {
            viewState.hideEmpty()
            viewState.setRepositoryList(mutableList)
        }
    }
}