package com.structure.kotlin.injection.component

import com.structure.kotlin.injection.module.ContextModule
import com.structure.kotlin.injection.module.NetworkModule
import com.structure.kotlin.ui.login.LoginViewModel
import com.structure.kotlin.ui.paging.PagingLibViewModel

import com.structure.kotlin.ui.post.PostViewModel
import dagger.Component
import javax.inject.Singleton


/**
 * Component providing inject() methods for presenters.
 */
@Singleton
//@Component(modules = [(ContextModule::class), (NetworkModule::class)])
@Component(modules = arrayOf((ContextModule::class), (NetworkModule::class)))
interface VMInjector {

    fun inject(postViewModel: PostViewModel)
    fun inject(loginViewModel: LoginViewModel)
    fun inject(pagingLibViewModel: PagingLibViewModel)

    @Component.Builder
    interface Builder {
        fun build(): VMInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder

    }
}