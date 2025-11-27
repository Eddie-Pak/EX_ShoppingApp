package com.fastcampusmall.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fastcampusmall.presentation.ui.theme.FastcampusmallTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FastcampusmallTheme {
                MainScreen()
            }
        }
    }
}