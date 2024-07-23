package sahak.sahakyan.dynamicdeals.domain.usecase

import android.util.Log
import com.google.firebase.auth.AuthResult
import sahak.sahakyan.dynamicdeals.domain.repository.AuthRepository
import sahak.sahakyan.dynamicdeals.utils.NAV_GRAPH
import sahak.sahakyan.dynamicdeals.utils.SIGN_UP_USE_CASE
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String, onComplete: (Result<AuthResult>) -> Unit) {
        Log.d(SIGN_UP_USE_CASE,"SignUpUseCase-invoke(): email: $email ; password: $password")
        repository.signUp(email, password, onComplete)
    }

    fun isUserVerified(): Boolean {
        val result = repository.isUserVerified()
        Log.d(SIGN_UP_USE_CASE,"isUserVerified(): result = $result")
        return result
    }
}