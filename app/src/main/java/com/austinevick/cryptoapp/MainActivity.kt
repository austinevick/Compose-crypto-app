package com.austinevick.cryptoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.austinevick.cryptoapp.ui.theme.CryptoAppTheme
import com.austinevick.cryptoapp.view.HomeScreen
import com.austinevick.cryptoapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoAppTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun MyApp(viewModel: MainViewModel= viewModel()) {
    
    val coins = rememberUpdatedState(newValue = viewModel.uiState.collectAsLazyPagingItems())


    LazyColumn {
        items(coins.value.itemCount){i->
            ListItem(headlineContent = { Text(text = coins.value[i]!!.SYMBOL) },
                supportingContent = {
                    Text(text = coins.value[i]!!.NAME)
                }
                )
        }
    }
}