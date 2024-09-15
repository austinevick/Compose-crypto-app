package com.austinevick.cryptoapp.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.austinevick.cryptoapp.model.TopListCoinData
import com.austinevick.cryptoapp.viewmodel.MainViewModel
import com.austinevick.cryptoapp.viewmodel.UIState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopCoinsScreen() {
    val viewModel = viewModel<MainViewModel>()

    val state = viewModel.topCoinState.collectAsState()
    val isRefreshing = viewModel.isRefreshing.collectAsState()
    val scope = rememberCoroutineScope()

    PullToRefreshBox(isRefreshing = isRefreshing.value,
        onRefresh = { scope.launch { viewModel.getTopCoins() } }) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                when (state.value) {
                    is UIState.Error -> (state.value as UIState.Error)
                        .message?.let { it1 -> Text(text = it1) }

                    UIState.IsLoading -> CircularProgressIndicator()
                    is UIState.Success<*> -> {
                        val data = state.value as UIState.Success<TopListCoinData>

                        List(data.data?.lIST!!.size) { i ->
                            val coin = data.data.lIST[i]

                            ListItem(
                                colors = ListItemDefaults.colors(
                                    containerColor = Color.White,
                                ),
                                headlineContent = { Text(text = coin.SYMBOL) },
                                supportingContent = { Text(text = coin.NAME, fontSize = 12.sp) },
                                leadingContent = {
                                    AsyncImage(
                                        model = coin.LOGOURL, "",
                                        Modifier.size(50.dp)
                                    )
                                },
                                trailingContent = { Text(text = "Price: ${Math.round(coin.PRICEUSD!! * 100.0) / 100.0} USD") }
                            )
                        }
                    }
                }
            }
        }
    }
}