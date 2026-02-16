package com.fastcampusmall.domain.usecase

import android.content.Context
import com.fastcampusmall.domain.repository.AuthRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun signIn(context: Context) = authRepository.signIn(context)
    fun getAccountInfo() = authRepository.getAccountInfo()
    fun signOut() = authRepository.signOut()
}