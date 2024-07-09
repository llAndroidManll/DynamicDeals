package sahak.sahakyan.dynamicdeals.domain.usecase

import com.google.firebase.auth.AuthResult
import sahak.sahakyan.dynamicdeals.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(email: String, password: String, onComplete: (Result<AuthResult>) -> Unit) {
        repository.signIn(email, password, onComplete)
    }
}