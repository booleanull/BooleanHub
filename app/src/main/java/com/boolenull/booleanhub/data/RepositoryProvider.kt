package com.boolenull.booleanhub.data

import android.util.Log
import com.boolenull.booleanhub.MyApplication
import com.boolenull.booleanhub.R
import com.boolenull.booleanhub.model.RepositoryModel
import com.boolenull.booleanhub.presenter.RepositoryPresenter
import io.reactivex.Completable
import io.reactivex.Flowable
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
        list.forEach {
            repositoryList.add(it)
        }

        Completable.fromAction {
            Log.d("AAAAAA", "dsadas")
            MyApplication.database.beginTransaction()
            MyApplication.database.repositoryDao().insert(repositoryList)
            MyApplication.database.endTransaction()
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()

        presentor.finishLoadOrUpdateRepository(repositoryList)
    }

    private fun handleError(error: Throwable) {

        Completable.fromAction {
            MyApplication.database.beginTransaction()
            repositoryList = MyApplication.database.repositoryDao().all().toMutableList()
            MyApplication.database.endTransaction()
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()

        presentor.errorLoadOrUpdateRepository(repositoryList, R.string.answerfromservererror)
    }
}