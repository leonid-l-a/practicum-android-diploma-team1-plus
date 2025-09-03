package ru.practicum.android.diploma.core

import android.app.Application
import org.koin.core.context.startKoin
import ru.practicum.android.diploma.core.di.data.coreDataModule
import ru.practicum.android.diploma.core.di.domain.coreInteractorModule
import ru.practicum.android.diploma.core.di.domain.coreRepositoryModule
import ru.practicum.android.diploma.core.di.viewmodel.coreViewModelModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                coreDataModule,
                coreRepositoryModule,
                coreInteractorModule,
                coreViewModelModule
            )
        }
    }
}
