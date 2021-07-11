package com.receipe.di.components

import com.receipe.di.annotations.HistoryScope
import com.receipe.di.modules.history_frg.HistoryViewModelModule
import com.receipe.fragments.history.HistoryFragment
import dagger.Subcomponent

@HistoryScope
@Subcomponent(modules = [HistoryViewModelModule::class])
interface HistoryComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HistoryComponent
    }

    fun inject(historyFragment: HistoryFragment)

}