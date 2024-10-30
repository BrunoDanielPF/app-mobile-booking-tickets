package com.example.tickets.components.home.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
@Preview
fun OnBoardingScreenPreview() {
    OnBoardingScreen()
}

@Composable
fun OnBoardingScreen() {
    val scope = rememberCoroutineScope()
    val onBoardingItems = OnBoardingItem.get()
    val pagerState = rememberPagerState(pageCount = {onBoardingItems.size})

    Column(Modifier.fillMaxSize()) {
        TopSection(
            onBackClicked = { /* Handle back navigation */ },
            onSkipClicked = { /* Navigate to main screen */ }
        )

        OnBoardingPager(
            onBoardingItems = onBoardingItems,
            pagerState = pagerState
        )

        BottomSection(
            totalItems = onBoardingItems.size,
            currentIndex = pagerState.currentPage,
            onNextClicked = {
                scope.launch {
                    if (pagerState.currentPage + 1 < onBoardingItems.size) {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    } else {
                        // Handle action on last page
                    }
                }
            }
        )
    }
}

@Composable
fun TopSection(onBackClicked: () -> Unit, onSkipClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        IconButton(
            onClick = onBackClicked,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(Icons.Outlined.ArrowBack, contentDescription = "Back")
        }

        TextButton(
            onClick = onSkipClicked,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Text(text = "Skip", color = MaterialTheme.colorScheme.onBackground)
        }
    }
}

@Composable
fun OnBoardingPager(onBoardingItems: List<OnBoardingItem>, pagerState: PagerState) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxSize()
            .scale(0.8f)
    ) { page ->
        OnBoardingItemContent(onBoardingItems[page])
    }
}

@Composable
fun BottomSection(totalItems: Int, currentIndex: Int, onNextClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        val buttonText = if (currentIndex + 1 == totalItems) "Start" else "Next"

        FloatingActionButton(onClick = onNextClicked) {
            Text(text = buttonText)
        }
    }
}

@Composable
fun OnBoardingItemContent(item: OnBoardingItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = item.Image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .scale(1.5f),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = item.title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = item.text),
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}