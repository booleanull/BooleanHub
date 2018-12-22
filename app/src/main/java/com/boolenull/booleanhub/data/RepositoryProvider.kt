package com.boolenull.booleanhub.data

import android.os.Handler
import com.boolenull.booleanhub.R
import com.boolenull.booleanhub.model.RepositoryModel
import com.boolenull.booleanhub.presenter.RepositoryPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RepositoryProvider(val presentor: RepositoryPresenter) {

    val repositoryList = mutableListOf<RepositoryModel>()

    fun testLoadRepository(hasRepository: Boolean) {
        Handler().postDelayed({
            if (hasRepository) {
                repositoryList.add(RepositoryModel("asdasdas", "dasdasdassda", "20.10.1000", "30.12.2012"))
                repositoryList.add(RepositoryModel("asdfsdfasdas", "dasdsasdasda", "20.10.1000", "30.12.2012"))
                repositoryList.add(RepositoryModel("asdfsdfasdas", "dasdsadasdasda", "20.10.1000", "30.12.2012"))
                repositoryList.add(RepositoryModel("asdasdas", "dasdasddasasda", "20.10.1000", "30.12.2012"))
                repositoryList.add(RepositoryModel("assdfadasdas", "dasdassaddasda", "20.10.1000", "30.12.2012"))
                repositoryList.add(RepositoryModel("asdasddasdas", "dasdasdasdsada", "20.10.1000", "30.12.2012"))
            }
            presentor.finishLoadOrUpdateRepository(repositoryList)
        }, 2000)
    }

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
        presentor.finishLoadOrUpdateRepository(repositoryList)
    }

    private fun handleError(error: Throwable) {
        presentor.errorLoadOrUpdateRepository(R.string.answerfromservererror)
    }
}