package com.fastcampusmall.data.repository

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.fastcampusmall.data.datasource.PreferenceDataSource
import com.fastcampusmall.data.db.dao.BasketProductDao
import com.fastcampusmall.data.db.dao.LikeProductDao
import com.fastcampusmall.domain.model.AccountInfo
import com.fastcampusmall.domain.repository.AuthRepository
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val credentialManager: CredentialManager,
    private val getCredentialRequest: GetCredentialRequest,
    private val preferenceDataSource: PreferenceDataSource,
    private val likeProductDao: LikeProductDao,
    private val basketProductDao: BasketProductDao
) : AuthRepository {
    override suspend fun signIn(context: Context): Result<AccountInfo> = try {
        val result = credentialManager.getCredential(context = context, request = getCredentialRequest)
        val credential = result.credential

        if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
            val firebaseCredential = GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)

            // Firebase 로그인
            val authResult = auth.signInWithCredential(firebaseCredential).await()
            val user = authResult.user

            val accountInfo = AccountInfo(
                tokenId = googleIdTokenCredential.idToken,
                name = user?.displayName ?: "",
                type = AccountInfo.Type.GOOGLE
            )

            preferenceDataSource.putAccountInfo(accountInfo)
            Result.success(accountInfo)
        } else {
            Result.failure(Exception("Invalid Credential"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override fun getAccountInfo(): AccountInfo? = preferenceDataSource.getAccountInfo()

    override suspend fun signOut() {
        auth.signOut()
        likeProductDao.deleteAll()
        basketProductDao.deleteAll()
        preferenceDataSource.removeAccountInfo()
    }
}