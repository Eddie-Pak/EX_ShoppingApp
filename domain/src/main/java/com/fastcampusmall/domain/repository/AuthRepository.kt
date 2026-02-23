package com.fastcampusmall.domain.repository

import android.content.Context
import com.fastcampusmall.domain.model.AccountInfo

interface AuthRepository {
    suspend fun signIn(context: Context): Result<AccountInfo>
    fun getAccountInfo(): AccountInfo?
    suspend fun signOut()
}