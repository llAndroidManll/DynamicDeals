package sahak.sahakyan.dynamicdeals.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sahak.sahakyan.dynamicdeals.data.remote.FirebaseAuthService
import sahak.sahakyan.dynamicdeals.data.repository.AuthRepositoryImpl
import sahak.sahakyan.dynamicdeals.domain.repository.AuthRepository
import sahak.sahakyan.dynamicdeals.domain.usecase.SignInUseCase
import sahak.sahakyan.dynamicdeals.domain.usecase.SignUpUseCase
import sahak.sahakyan.dynamicdeals.domain.usecase.VerifyEmailUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseAuthService(auth: FirebaseAuth): FirebaseAuthService {
        return FirebaseAuthService(auth)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authService: FirebaseAuthService): AuthRepository {
        return AuthRepositoryImpl(authService)
    }

    @Provides
    @Singleton
    fun provideSignInUseCase(repository: AuthRepository): SignInUseCase {
        return SignInUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(repository: AuthRepository): SignUpUseCase {
        return SignUpUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideVerifyEmailUseCase(repository: AuthRepository): VerifyEmailUseCase {
        return VerifyEmailUseCase(repository)
    }


}