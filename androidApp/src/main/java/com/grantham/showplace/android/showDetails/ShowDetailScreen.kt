package com.grantham.showplace.android.showDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun ShowDetailsRoute(
    viewModel: ShowDetailsViewModel = getViewModel()
) {
    val uiState = viewModel.show.collectAsStateWithLifecycle()

    when {
        uiState.value.isLoading -> LoadingSpinner()
        uiState.value.show == null -> LoadingSpinner()
        else -> ShowDetailScreen(uiState.value.show!!)
    }
}


@Composable
fun ShowDetailScreen(show: Show) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.padding(top = 16.dp))
        ScreenTitle("Event Details")

        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFF212121)),
            elevation = CardDefaults.cardElevation(0.dp),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 36.dp)
        ) {
            Text(
                text = "${show.venue.name} • ${show.time}\nBaltimore",
                fontSize = 20.sp,
                color = Color(0xFFE1E1E1),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 28.dp, end = 20.dp, start = 28.dp)
            )
            Text(
                text = "Price: ${show.price.toTitleCase()}",
                color = Color(0xFF7EBCB9),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 46.dp, start = 28.dp)
            )
            Text(
                text = show.date,
                color = Color(0xFF7EBCB9),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 1.dp, start = 28.dp)
            )
            Text(
                text = show.lineup,
                color = Color(0xFFBC88FF),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 3.dp, end = 20.dp, start = 28.dp)
            )
            Divider(
                modifier = Modifier
                    .padding(start = 14.dp, top = 24.dp, end = 14.dp)
                    .background(Color(0xFF545050))
                    .height(1.dp)
            )
            ShowDetailRowItem("Age", show.venue.ageLimit)
            ShowDetailRowItem("Capacity", show.venue.capacity)
            ShowDetailRowItem("Accessibility", show.venue.accessibility)
            Divider(
                modifier = Modifier
                    .padding(start = 14.dp, top = 24.dp, end = 14.dp)
                    .background(Color(0xFF545050))
                    .height(1.dp)
            )
            ShowDetailRowItem("Contact", show.venue.url, addBottomPadding = true, showPurple = true)
        }
    }
}

@Composable
private fun ShowDetailRowItem(
    title: String,
    body: String?,
    addBottomPadding: Boolean = false,
    showPurple: Boolean = false
) {
    Column(
        modifier = Modifier.padding(top = 24.dp, start = 28.dp, end = 20.dp)
    ) {
        Text(
            text = title,
            fontSize = 13.sp,
            lineHeight = 16.sp,
            letterSpacing = 1.25.sp,
            color = if (showPurple) Color(0xFFBC88FF) else Color(0xFF7EBCB9),
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = body ?: "TBD",
            fontSize = 13.sp,
            lineHeight = 16.sp,
            letterSpacing = 1.25.sp,
            color = Color(0xFFE1E1E1),
            fontWeight = FontWeight.SemiBold,
            modifier = if (addBottomPadding) Modifier.padding(bottom = 30.dp) else Modifier.padding(
                bottom = 0.dp
            )
        )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        ShowDetailScreen(showPreview)
    }
}