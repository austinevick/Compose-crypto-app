package com.austinevick.cryptoapp.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.austinevick.cryptoapp.model.LatestArticleData
import com.austinevick.cryptoapp.model.LatestArticleModel
import com.austinevick.cryptoapp.model.TopListCoinData
import com.austinevick.cryptoapp.viewmodel.MainViewModel
import com.austinevick.cryptoapp.viewmodel.UIState
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LatestArticlesScreen() {
    val viewModel = viewModel<MainViewModel>()

    val state = viewModel.latestArticleState.collectAsState()
    val isRefreshing = viewModel.isRefreshing.collectAsState()

    val uriHandler = LocalUriHandler.current
    val scope = rememberCoroutineScope()


    PullToRefreshBox(isRefreshing = isRefreshing.value,
        onRefresh = { scope.launch {
            viewModel.getLatestArticles() } }) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {

            item {
                when (state.value) {
                    is UIState.Error -> (state.value as UIState.Error)
                        .message?.let { it1 ->
                            Text(text = it1)
                        }

                    UIState.IsLoading -> CircularProgressIndicator()
                    is UIState.Success<*> -> {
                        val data = state.value as UIState.Success<List<LatestArticleData>>

                        List(data.data!!.size) { i ->
                            val article = data.data[i]
                            ListItem(
                                modifier = Modifier.clickable {
                                    uriHandler.openUri(article.URL)
                                },
                                colors = ListItemDefaults.colors(containerColor = Color.White),
                                headlineContent = {
                                    Text(text = article.TITLE)
                                },
                                leadingContent = {
                                    AsyncImage(
                                        model = article.IMAGEURL,
                                        "",
                                        Modifier.size(80.dp)
                                    )
                                }
                            )

                        }
                    }
                }
            }


        }
    }


}
