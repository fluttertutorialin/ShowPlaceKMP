package com.grantham.showplace.android.showDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.grantham.showplace.android.navigation.ShowDetailsNavigation
import com.grantham.showplace.data.models.Show
import com.grantham.showplace.domain.ShowRepositoryInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShowDetailsViewModel(
    private val logger: Logger,
    private val showService: ShowRepositoryInterface,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _show = MutableStateFlow(ShowDetailViewState())
    val show: StateFlow<ShowDetailViewState>
        get() = _show

    init {
        val showId: String? = savedStateHandle[ShowDetailsNavigation.showArg]

        showId?.let {
            getShow(showId)
        }
    }

    fun getShow(id: String) {
        viewModelScope.launch {
            try {
                val show = showService.getShow(id)
                _show.value = _show.value.copy(isLoading = false, show = show)

            } catch (exception: Exception) {
                logger.e { "Failed to retrieve show from show id $id error: $exception" }
            }
        }
    }
}

data class ShowDetailViewState(
    val show: Show? = null,
    val isLoading: Boolean = true,
)