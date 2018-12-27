package com.boolenull.booleanhub.di

import com.boolenull.booleanhub.data.RepositoryProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, DatabaseModule::class])
interface ProviderComponent {
    fun inject(repositoryProvider: RepositoryProvider)
}
