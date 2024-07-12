package sahak.sahakyan.dynamicdeals.domain.repository

import com.google.firebase.auth.AuthResult

interface AuthRepository {
   suspend fun signUp(email: String, password: String, onComplete: (Result<AuthResult>) -> Unit)
   suspend fun signIn(email: String, password: String, onComplete: (Result<AuthResult>) -> Unit)
   suspend fun sendVerificationEmail(onComplete: (Result<Void>) -> Unit)
}