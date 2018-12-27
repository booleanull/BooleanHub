package com.boolenull.booleanhub.data

import android.content.Context
import android.util.Log
import com.boolenull.booleanhub.MyApplication
import com.boolenull.booleanhub.R
import com.boolenull.booleanhub.model.RepositoryModel
import com.boolenull.booleanhub.model.UserModel
import com.boolenull.booleanhub.presenter.RepositoryPresenter
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class RepositoryProvider(val presentor: RepositoryPresenter) {

    @Inject
    lateinit var context: Context
    @Inject
    lateinit var apiService: ApiService
    @Inject
    lateinit var repositoryDatabase: RepositoryDatabase

    var repositoryList = mutableListOf<RepositoryModel>()
    var userList = mutableListOf<UserModel>()

    fun loadRepository() {
        MyApplication.providerComponent.inject(this)
        val compositeDisposable = CompositeDisposable()
        val repositories = apiService.getRepositories()
        val userCommits = apiService.getUserCommits()

        compositeDisposable.add(
            Observable.merge(repositories, userCommits)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    handleResponse(it)
                }, {
                    handleError(it)
                })
        )
    }

    private fun handleResponse(list: List<Any>) {
        var count = 1
        list.forEach {
            if(it is RepositoryModel) {
                it.id = count++

                val stringCreate: String =
                    context.getString(R.string.createdate) + " " + it.date[8] + it.date[9] + ":" + it.date[5] + it.date[6] + ":" + it.date[0] + it.date[1] + it.date[2] + it.date[3]
                it.date = stringCreate

                val stringUpdate =
                    context.getString(R.string.updatedate) + " " + it.dateUpdate[8] + it.dateUpdate[9] + ":" + it.dateUpdate[5] + it.dateUpdate[6] + ":" + it.dateUpdate[0] + it.dateUpdate[1] + it.dateUpdate[2] + it.dateUpdate[3]
                it.dateUpdate = stringUpdate

                repositoryList.add(it)
            }
            else if(it is UserModel) {
                userList.add(it)
            }
        }

        val calendar : Calendar = Calendar.getInstance()
        count = 0
        userList.forEach {
            if (it.date.contains((calendar.get(Calendar.MONTH) + 1).toString()) && it.date.contains((calendar.get(Calendar.DAY_OF_MONTH)).toString())) {
                count++
            }
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

        presentor.finishLoadOrUpdateRepository(repositoryList, count, 0)
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