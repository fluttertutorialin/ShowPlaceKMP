package com.grantham.showplace.domain

import co.touchlab.kermit.Logger
import com.grantham.showplace.data.ShowService
import com.grantham.showplace.data.models.Show
import com.grantham.showplace.data.models.mapToShow
import com.grantham.showplace.di.injectLogger
import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface ShowRepositoryInterface {

    @NativeCoroutines
    suspend fun getShow(id: String): Show

    @NativeCoroutines
    suspend fun getShows(): List<Show>
}

class ShowRepository : KoinComponent, ShowRepositoryInterface {
    private val showService: ShowService by inject()
    private val logger: Logger by injectLogger("ShowRepository")

    @NativeCoroutineScope
    val coroutineScope: CoroutineScope = MainScope()

    @NativeCoroutines
    override suspend fun getShow(id: String): Show {
        val show = showService.getShow(id).mapToShow()
        logger.d { "fetched show id: $id from network" }
        return show
    }

    @NativeCoroutines
    override suspend fun getShows(): List<Show> {
        val shows = showService.getShows().map { it.mapToShow() }
        logger.d { "fetched shows frmo network: $shows" }
        return shows
    }
}