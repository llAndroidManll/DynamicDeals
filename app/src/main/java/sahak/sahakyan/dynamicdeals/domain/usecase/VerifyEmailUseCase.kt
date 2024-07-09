package sahak.sahakyan.dynamicdeals.domain.usecase

import sahak.sahakyan.dynamicdeals.domain.repository.AuthRepository
import javax.inject.Inject

class VerifyEmailUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(onComplete: (Result<Void>) -> Unit) {
        repository.sendVerificationEmail(onComplete)
    }
}