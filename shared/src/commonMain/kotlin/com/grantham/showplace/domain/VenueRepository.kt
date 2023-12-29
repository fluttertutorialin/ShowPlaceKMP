package com.grantham.showplace.domain

import com.grantham.showplace.data.VenueService
import com.grantham.showplace.data.models.Venue
import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface VenueRepositoryInterface {

    @NativeCoroutines
    suspend fun getVenue(id: String): Venue

    @NativeCoroutines
    suspend fun getVenues(): List<Venue>
}

class VenueRepository : KoinComponent, VenueRepositoryInterface {
    private val venueService: VenueService by inject()

    @NativeCoroutineScope
    val coroutineScope: CoroutineScope = MainScope()

    override suspend fun getVenue(id: String): Venue {
        return venueService.getVenue(id)
    }

    override suspend fun getVenues(): List<Venue> {
        return venueService.getVenues()
    }
}