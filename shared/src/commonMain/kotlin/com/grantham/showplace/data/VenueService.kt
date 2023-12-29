package com.grantham.showplace.data

import com.grantham.showplace.Constants
import com.grantham.showplace.data.models.Venue
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class VenueService(
    private val client: HttpClient
) {

    @NativeCoroutines
    suspend fun getVenue(id: String): Venue {
        return client.get(Constants.VENUE) {
            parameter("id", id)
        }.body()
    }

    @NativeCoroutines
    suspend fun getVenues(): List<Venue> {
        return client.get(Constants.VENUES).body()
    }
}