package com.boolenull.booleanhub.data

import android.content.Context
import com.boolenull.booleanhub.MyApplication
import com.boolenull.booleanhub.R
import com.boolenull.booleanhub.model.RepositoryModel
import com.boolenull.booleanhub.presenter.RepositoryPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryProvider(val presentor: RepositoryPresenter) {

    @Inject
    lateinit var context: Context
    @Inject
    lateinit var apiService: ApiService
    @Inject
    lateinit var repositoryDatabase: RepositoryDatabase

    var repositoryList = mutableListOf<RepositoryModel>()

    init {
        MyApplication.providerComponent.inject(this)
    }

    fun loadRepository() {
        val compositeDisposable = CompositeDisposable()
        val repositories = apiService.getRepositories()

        compositeDisposable.add(
                repositories
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            handleResponse(it)
                        }, {
                            handleError(it)
                        })
        )
    }

    private fun handleResponse(list: List<RepositoryModel>) {
        var count = 1
        list.forEach {
            it.id = count++
            it.date = context.getString(R.string.createdate) + " " + it.date[8] + it.date[9] + ":" + it.date[5] + it.date[6] + ":" + it.date[0] + it.date[1] + it.date[2] + it.date[3]
            it.dateUpdate = context.getString(R.string.updatedate) + " " + it.dateUpdate[8] + it.dateUpdate[9] + ":" + it.dateUpdate[5] + it.dateUpdate[6] + ":" + it.dateUpdate[0] + it.dateUpdate[1] + it.dateUpdate[2] + it.dateUpdate[3]
            repositoryList.add(it)
        }

        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(Observable.fromCallable {
            repositoryDatabase.repositoryDao().deleteAll()
            repositoryDatabase.repositoryDao().insert(repositoryList)
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
            repositoryList = repositoryDatabase.repositoryDao().all().toMutableList()
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