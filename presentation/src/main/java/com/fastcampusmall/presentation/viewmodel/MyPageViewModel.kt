package com.fastcampusmall.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fastcampusmall.domain.model.AccountInfo
import com.fastcampusmall.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    private val _accountInfo = MutableStateFlow<AccountInfo?>(authUseCase.getAccountInfo())
    val accountInfo get() = _accountInfo.asStateFlow()

    fun signIn(context: Context) {
        viewModelScope.launch {
            authUseCase.signIn(context).onSuccess {
                _accountInfo.value = it
            }.onFailure { /* 에러 처리 */ }
        }
    }

    fun signOut() {
        authUseCase.signOut()
        _accountInfo.value = null
    }
}