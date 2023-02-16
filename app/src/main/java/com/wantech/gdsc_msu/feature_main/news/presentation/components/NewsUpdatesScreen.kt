package com.wantech.gdsc_msu.feature_main.news.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        var selectedTabIndex by remember { mutableStateOf(0) }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {


            LaunchedEffect(pagerState.currentPage) {
                selectedTabIndex = pagerState.currentPage
            }
            TabRow(selectedTabIndex = selectedTabIndex, indicator = { tabPositions ->
                TabRowDefaults.Indicator(
//                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    height = TabRowDefaults.IndicatorHeight * 1.5F
                )
            }) {
                tabData.forEachIndexed { index, tab ->
                    Tab(
                        text = {
                            Text(
                                text = tab, style = MaterialTheme.typography.body2.copy(
                                    color = if (selectedTabIndex == index) MaterialTheme.colors.primary else Color.Unspecified
                                )
                            )
                        },
                        selected = selectedTabIndex == index,
                        onClick = {

                            scope.launch {
                                pagerState.scrollToPage(index)
                            }
                        }
                    )
                }
            }
            HorizontalPager(
                modifier = Modifier.weight(1f),
                state = pagerState,
                count = tabData.size,
            ) { page ->
                when (page) {
                    0 -> {
                        GroupNewsScreen(
                            news = listOf(),
                            onClick = {

                            },
                            onclickNewsImage = {}
                        )
                    }
                    1 -> {
//                        NewsScreen()
                    }
                }
            }

        }
    }
}