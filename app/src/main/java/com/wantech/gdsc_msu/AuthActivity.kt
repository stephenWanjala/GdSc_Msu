package com.wantech.gdsc_msu

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.wantech.gdsc_msu.feature_main.presentation.HomeActivity
import com.wantech.gdsc_msu.feature_main.presentation.MainVieModel
import com.wantech.gdsc_msu.feature_main.profile.presentation.ProfileViewModel
import com.wantech.gdsc_msu.ui.theme.GdSc_MsuTheme
import com.wantech.gdsc_msu.ui.theme.Theme
import com.wantech.gdsc_msu.util.AuthNavHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel:MainVieModel by viewModels()
        super.onCreate(savedInstanceState)
        setContent {
            val themeValue = viewModel.theme.collectAsState(
                initial = Theme.FOLLOW_SYSTEM.themeValue,
                context = Dispatchers.Main.immediate
            )
            GdSc_MsuTheme (theme = themeValue.value){
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    AuthNavHost(navHostController = navController, onNavigate = {
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    })
                }
            }
        }
    }
}

