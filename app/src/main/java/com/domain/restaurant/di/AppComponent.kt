package com.domain.restaurant.di

import android.app.Application
import com.domain.restaurant.App
import com.domain.restaurant.di.activity.ActivityBuilderModule
import com.domain.restaurant.di.module.AppModule
import com.domain.restaurant.di.viewmodel.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuilderModule::class,
        ViewModelFactoryModule::class


    ]


)

interface AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: App)
}
