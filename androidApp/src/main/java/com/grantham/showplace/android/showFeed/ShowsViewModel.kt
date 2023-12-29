package com.grantham.showplace.android.showFeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.grantham.showplace.data.models.Show
import com.grantham.showplace.domain.ShowRepositoryInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShowsViewModel(
    private val logger: Logger,
    private val showRepository: ShowRepositoryInterface
) : ViewModel() {

    private val _shows = MutableStateFlow(ShowViewState())
    val shows: StateFlow<ShowViewState>
        get() = _shows

    init {
        getShows()
    }

    fun getShows() {
        viewModelScope.launch {
            try {
                val shows = showRepository.getShows()
                _shows.value = _shows.value.copy(isLoading = false, shows = shows)
            } catch (exception: Exception) {
                logger.e { "Failed to retrieve shows $exception" }
            }
        }
    }
}

data class ShowViewState(
    val shows: List<Show> = listOf(),
    val isLoading: Boolean = true,
    val currentShow: Show? = null
)