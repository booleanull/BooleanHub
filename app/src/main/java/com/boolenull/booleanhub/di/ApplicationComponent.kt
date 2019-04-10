package com.boolenull.booleanhub.di

import com.boolenull.booleanhub.data.RepositoryProvider
import com.boolenull.booleanhub.ui.adapter.PageAdapter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, DatabaseModule::class])
interface ApplicationComponent {

    fun inject(repositoryProvider: RepositoryProvider)
    fun inject(pageAdapter: PageAdapter)
}
