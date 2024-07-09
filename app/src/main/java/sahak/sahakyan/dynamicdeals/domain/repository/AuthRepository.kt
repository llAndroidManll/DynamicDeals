package sahak.sahakyan.dynamicdeals.domain.repository

import com.google.firebase.auth.AuthResult

interface AuthRepository {
    fun signUp(email: String, password: String, onComplete: (Result<AuthResult>) -> Unit)
    fun signIn(email: String, password: String, onComplete: (Result<AuthResult>) -> Unit)
    fun sendVerificationEmail(onComplete: (Result<Void>) -> Unit)
}