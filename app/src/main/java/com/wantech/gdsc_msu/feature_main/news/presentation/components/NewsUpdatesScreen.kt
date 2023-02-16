package com.wantech.gdsc_msu.feature_main.news.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NewsUpdatesScreen(
    tabData: List<String> = listOf("Groups", "News"),
    pagerState: PagerState
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->

        val scope = rememberCoroutineScope()
        var selectedTabIndex by remember {
            mutableStateOf(0)
        }
        LaunchedEffect(pagerState.currentPage) {
            selectedTabIndex = pagerState.currentPage
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {

            TabRow(selectedTabIndex = pagerState.currentPage, indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    height = TabRowDefaults.IndicatorHeight * 1.5F
                )
            }) {
                tabData.forEachIndexed { index, tab ->
                    Tab(
                        selectedContentColor = MaterialTheme.colors.primary,
                        unselectedContentColor = MaterialTheme.colors.onBackground,
                        selected = selectedTabIndex == index,
                        onClick = {
                            selectedTabIndex = index
                            scope.launch {
                                pagerState.animateScrollToPage(selectedTabIndex)


                            }
                        }) {
                        Text(text = tab)
                    }
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