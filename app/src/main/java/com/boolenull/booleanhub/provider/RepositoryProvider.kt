package com.boolenull.booleanhub.provider

import android.os.Handler
import com.boolenull.booleanhub.R
import com.boolenull.booleanhub.model.RepositoryModel
import com.boolenull.booleanhub.presenter.RepositoryPresenter

class RepositoryProvider(val presentor: RepositoryPresenter) {

    fun testLoadRepository(hasRepository: Boolean) {
        Handler().postDelayed({
            val repositoryList = mutableListOf<RepositoryModel>()
            if (hasRepository) {
                repositoryList.add(RepositoryModel("asdasdas", "dasdasdassda", "20.10.1000", "30.12.2012"))
                repositoryList.add(RepositoryModel("asdfsdfasdas", "dasdsasdasda", "20.10.1000", "30.12.2012"))
                repositoryList.add(RepositoryModel("asdfsdfasdas", "dasdsadasdasda", "20.10.1000", "30.12.2012"))
                repositoryList.add(RepositoryModel("asdasdas", "dasdasddasasda", "20.10.1000", "30.12.2012"))
                repositoryList.add(RepositoryModel("assdfadasdas", "dasdassaddasda", "20.10.1000", "30.12.2012"))
                repositoryList.add(RepositoryModel("asdasddasdas", "dasdasdasdsada", "20.10.1000", "30.12.2012"))
            }
            presentor.errorLoadOrUpdateRepository(R.string.answerfromservererror)
            //presentor.finishLoadOrUpdateRepository(repositoryList)
        }, 2000)
    }
}