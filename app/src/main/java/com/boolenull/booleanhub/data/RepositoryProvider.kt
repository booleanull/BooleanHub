package com.boolenull.booleanhub.data

import android.util.Log
import com.boolenull.booleanhub.MyApplication
import com.boolenull.booleanhub.R
import com.boolenull.booleanhub.model.RepositoryModel
import com.boolenull.booleanhub.presenter.RepositoryPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RepositoryProvider(val presentor: RepositoryPresenter) {

    var repositoryList = mutableListOf<RepositoryModel>()

    fun loadRepository() {
        val compositeDisposable = CompositeDisposable()
        val repository = ApiProvide.provideApi()
        compositeDisposable.add(
            repository.getRepositories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        )
    }

    private fun handleResponse(list: List<RepositoryModel>) {
        var count = 1
        list.forEach {
            it.id = count++
            repositoryList.add(it)
        }

        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(Observable.fromCallable {
            MyApplication.database.repositoryDao().deleteAll()
            MyApplication.database.repositoryDao().insert(repositoryList)
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()
        )

        presentor.finishLoadOrUpdateRepository(repositoryList)
    }

    private fun handleError(error: Throwable) {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(Observable.fromCallable {
            repositoryList = MyApplication.database.repositoryDao().all().toMutableList()
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                repositoryList.reversed()
                presentor.errorLoadOrUpdateRepository(repositoryList, R.string.answerfromservererror)
            }
        )
    }
}