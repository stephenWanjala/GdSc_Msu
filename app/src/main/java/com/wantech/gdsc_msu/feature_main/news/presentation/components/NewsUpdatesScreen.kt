package com.wantech.gdsc_msu.feature_main.news.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NewsUpdatesScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        val unUsedPadding = it.calculateTopPadding()
        val scope = rememberCoroutineScope()
        val tabData = listOf("Groups", "News")
        Column(modifier = Modifier.fillMaxWidth()) {
            val pagerState = rememberPagerState()
            val tabIndex = pagerState.currentPage
            TabRow(selectedTabIndex = tabIndex, indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }) {
                tabData.forEachIndexed { index,tab ->
                    Tab(
                        text = { Text(text = tab) },
                        selected = tabIndex == index,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }
            HorizontalPager(
                count = tabData.size,
                state = pagerState,
            ) { page ->
                // TODO: page content
            }

        }
    }
}