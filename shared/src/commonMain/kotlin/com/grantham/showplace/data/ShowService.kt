package com.grantham.showplace.data

import co.touchlab.kermit.Logger
import com.grantham.showplace.Constants
import com.grantham.showplace.data.models.ShowDTO
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ShowService(
    private val client: HttpClient,
    val log: Logger
) {

    @NativeCoroutines
    suspend fun getShow(id: String): ShowDTO {
        log.d { "Fetching Show id $id from network" }
        return client.get(Constants.SHOW) {
            parameter("id", id)
        }.body()
    }

    @NativeCoroutines
    suspend fun getShows(): List<ShowDTO> {
        log.d { "Fetching Shows from network" }
        return client.get(Constants.SHOWS).body()
    }
}