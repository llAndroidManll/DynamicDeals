package sahak.sahakyan.dynamicdeals.data.repository

import com.google.firebase.auth.AuthResult
import sahak.sahakyan.dynamicdeals.data.remote.FirebaseAuthService
import sahak.sahakyan.dynamicdeals.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: FirebaseAuthService
) : AuthRepository {
    override fun signUp(email: String, password: String, onComplete: (Result<AuthResult>) -> Unit) {
        authService.signUpWithEmail(email, password, onComplete)
    }

    override fun signIn(email: String, password: String, onComplete: (Result<AuthResult>) -> Unit) {
        authService.signInWithEmail(email, password, onComplete)
    }

    override fun sendVerificationEmail(onComplete: (Result<Void>) -> Unit) {
        authService.sendVerificationEmail(onComplete)
    }

}