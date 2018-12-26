package com.boolenull.booleanhub.data

import com.boolenull.booleanhub.MyApplication
import com.boolenull.booleanhub.R
import com.boolenull.booleanhub.model.RepositoryModel
import com.boolenull.booleanhub.presenter.RepositoryPresenter
import com.boolenull.booleanhub.utils.getString
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

            val stringCreate: String = R.string.createdate.getString() + " " + it.date[8] + it.date[9] + ":" + it.date[5] + it.date[6] + ":" + it.date[0] + it.date[1] + it.date[2] + it.date[3]
            it.date = stringCreate

            val stringUpdate = R.string.updatedate.getString() + " " + it.dateUpdate[8] + it.dateUpdate[9] + ":" + it.dateUpdate[5] + it.dateUpdate[6] + ":" + it.dateUpdate[0] + it.dateUpdate[1] + it.dateUpdate[2] + it.dateUpdate[3]
            it.dateUpdate = stringUpdate

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