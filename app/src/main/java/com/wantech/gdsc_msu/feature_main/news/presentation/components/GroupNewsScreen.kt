package com.wantech.gdsc_msu.feature_main.news.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wantech.gdsc_msu.feature_main.news.domain.model.GroupNewsItemModel

@Composable
fun GroupNewsScreen(
    modifier: Modifier = Modifier,
    news: List<GroupNewsItemModel>,
    onClick: (String) -> Unit,
    onclickNewsImage: (String) -> Unit,
) {
    LazyColumn(modifier = modifier.fillMaxWidth()){
        items(news.size) { index ->
            GroupNewsItem(
                newsItem = news[index],
                onClick = onClick,
                onclickNewsImage = onclickNewsImage
            )
        }
    }

}