package com.fastcampusmall.presentation.ui.screen.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fastcampusmall.presentation.viewmodel.MyPageViewModel

@Composable
fun MyPageScreen(
    modifier: Modifier,
    viewModel: MyPageViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val accountInfo by viewModel.accountInfo.collectAsStateWithLifecycle()

    Column(modifier = modifier
        .fillMaxSize()
        .padding(30.dp)) {
        if (accountInfo == null) {
            Button(
                onClick = { viewModel.signIn(context) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("구글 로그인")
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "반갑습니다, ${accountInfo?.name}님!",
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f)
                )
                Button(onClick = { viewModel.signOut() }) {
                    Text("로그아웃")
                }
            }
        }
    }
}