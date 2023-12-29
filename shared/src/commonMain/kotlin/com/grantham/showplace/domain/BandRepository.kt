package com.grantham.showplace.domain


import com.grantham.showplace.data.BandService
import com.grantham.showplace.data.models.Band
import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface BandRepositoryInterface {
    @NativeCoroutines
    suspend fun getBand(id: Int): Band

    @NativeCoroutines
    suspend fun getBands(): List<Band>
}

class BandRepository : KoinComponent, BandRepositoryInterface {
    private val bandService: BandService by inject()

    @NativeCoroutineScope
    val coroutineScope: CoroutineScope = MainScope()

    @NativeCoroutines
    override suspend fun getBand(id: Int): Band {
        return bandService.getBand(id)
    }

    @NativeCoroutines
    override suspend fun getBands(): List<Band> {
        return bandService.getBands()
    }
}