package com.grantham.showplace.android.showFeed

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.grantham.showplace.android.MyApplicationTheme
import com.grantham.showplace.android.ui.LoadingSpinner
import com.grantham.showplace.android.ui.ScreenTitle
import com.grantham.showplace.android.utils.toTitleCase
import com.grantham.showplace.data.models.Show
import com.grantham.showplace.showPreview
import org.koin.androidx.compose.getViewModel

@Composable
fun ShowListRoute(
    viewModel: ShowsViewModel = getViewModel(),
    onClickedShow: (Show) -> Unit,
) {
    val uiState = viewModel.shows.collectAsStateWithLifecycle()
    ShowScreen(uiState.value, onClickedShow)
}

@Composable
fun ShowScreen(showViewState: ShowViewState, onClickedShow: (Show) -> Unit) {
    when {
        showViewState.isLoading -> LoadingSpinner()
        else -> Shows(showViewState.shows, onClickedShow)
    }
}

@Composable
fun Shows(
    shows: List<Show>,
    onClickedShow: (Show) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(21.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(vertical = 16.dp),
    ) {
        item {
            ScreenTitle("Shows This Month")
        }
        items(shows) { show ->
            Show(show, onClickedShow)
        }
    }
}

@Composable
fun Show(show: Show, onClickedShow: (Show) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF212121)),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth()
            .clickable { onClickedShow.invoke(show) }
    ) {
        LineUpVenue(show)
    }
}

@Composable
fun LineUpVenue(show: Show) {
    Column(
        modifier = Modifier.padding(start = 26.dp)
    ) {
        Text(
            text = "${show.venue.name} • ${show.time?.lowercase()}",
            color = Color(0xFFE1E1E1),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 24.dp, end = 20.dp, bottom = 16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Price: ${show.price.toTitleCase()}",
            color = Color(0xFF7EBCB9),
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            modifier = Modifier.padding(top = 3.dp)
        )

        Text(
            text = show.date,
            color = Color(0xFF7EBCB9),
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            modifier = Modifier.padding(top = 3.dp)
        )
        Text(
            text = "Lineup: ${show.lineup}",
            fontSize = 13.sp,
            lineHeight = 16.sp,
            letterSpacing = 1.25.sp,
            color = Color(0xFFBC88FF),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 24.dp, top = 3.dp, end = 28.dp)
        )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        ShowScreen(
            showViewState = ShowViewState(
                isLoading = false,
                shows = listOf(
                    showPreview,
                    showPreview,
                    showPreview,
                    showPreview
                )
            ),
            onClickedShow = { })
    }
}