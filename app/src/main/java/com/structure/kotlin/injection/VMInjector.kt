package com.structure.kotlin.injection

import com.structure.kotlin.ui.LoginViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf((ContextModule::class), (NetworkModule::class)))
interface VMInjector {

    fun inject(loginViewModel: LoginViewModel)


    @Component.Builder
    interface Builder {
        fun build(): VMInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder

    }
}