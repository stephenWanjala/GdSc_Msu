package com.wantech.gdsc_msu.feature_main.news.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NewsUpdatesScreen(
    tabData: List<String> = listOf("Groups", "News")
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->

        val scope = rememberCoroutineScope()
        val pagerState = rememberPagerState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            var tabIndex by remember {
                mutableStateOf(0)
            }
            TabRow(selectedTabIndex = pagerState.currentPage, indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    height = TabRowDefaults.IndicatorHeight * 1.5F
                )
            }) {
                tabData.forEachIndexed { index, tab ->
                    Tab(
                        text = { Text(text = tab) },
                        selected = tabIndex == index,
                        onClick = {
                            tabIndex = index
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }
            HorizontalPager(
                modifier = Modifier.weight(1f),
                state = pagerState,
                count = tabData.size,
            ) {
                // TODO: page content
            }

        }
    }
}