package com.wantech.gdsc_msu.feature_main.news.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.wantech.gdsc_msu.feature_main.news.domain.model.GroupNewsItemModel

@Composable
fun GroupNewsItem(
    modifier: Modifier = Modifier,
    newsItem: GroupNewsItemModel,
    onClick: (String) -> Unit,
    onclickNewsImage: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(newsItem.id) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            model = newsItem.imageUrl, contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable { onclickNewsImage(newsItem.imageUrl) },
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                modifier = Modifier.clickable { onClick(newsItem.id) },
                text = newsItem.title,
                style = MaterialTheme.typography.body2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = newsItem.description,
                style = MaterialTheme.typography.caption,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}