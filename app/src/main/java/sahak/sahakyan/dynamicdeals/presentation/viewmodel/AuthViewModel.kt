package sahak.sahakyan.dynamicdeals.presentation.viewmodel

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthResult
import sahak.sahakyan.dynamicdeals.data.model.User
import sahak.sahakyan.dynamicdeals.domain.usecase.SignInUseCase
import sahak.sahakyan.dynamicdeals.domain.usecase.SignUpUseCase
import sahak.sahakyan.dynamicdeals.domain.usecase.VerifyEmailUseCase
import sahak.sahakyan.dynamicdeals.utils.AUTH_VIEW_MODEL
import sahak.sahakyan.dynamicdeals.utils.NAV_GRAPH
import sahak.sahakyan.dynamicdeals.utils.Resource
import sahak.sahakyan.dynamicdeals.utils.UserState
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val verifyEmailUseCase: VerifyEmailUseCase
) : ViewModel() {

    private val _authState = MutableLiveData<Resource<AuthResult>>()
    val authState: LiveData<Resource<AuthResult>> get() = _authState

    private val _userState = MutableLiveData<UserState>(UserState(user = User(), error = ""))
    val userState: LiveData<UserState> get() = _userState

    suspend fun signIn(email: String, password: String) {
        _authState.value = Resource.Loading
        signInUseCase(email, password) { result ->
            _authState.value = if (result.isSuccess) {
                Resource.Success(result.getOrNull())
            } else {
                Resource.Error(result.exceptionOrNull()?.message)
            }
        }
    }

    suspend fun signUp(email: String, password: String)  {
        Log.d(AUTH_VIEW_MODEL, "AuthViewModel class: signUp() called with email: $email and password: $password")
        _authState.value = Resource.Loading
        signUpUseCase(email, password) { result ->
            _authState.value = if (result.isSuccess) {
                Resource.Success(result.getOrNull())
            } else {
                Resource.Error(result.exceptionOrNull()?.message)
            }
        }
    }

    suspend fun sendVerificationEmail() {
        _authState.value = Resource.Loading
        verifyEmailUseCase { result ->
            if (result.isSuccess) {
                _authState.value = Resource.Success(null)
            } else {
                _authState.value = Resource.Error(result.exceptionOrNull()?.message.toString())
            }
        }
    }

    suspend fun setUser(user: User) {
        Log.d(NAV_GRAPH,"AuthViewModel class: setUser() called with user = $user")
        if (_userState.value != null) {
            _userState.value = UserState(user = user)
            Log.d(NAV_GRAPH,"AuthViewModel class: setUser() called and user state has been updated to ${_userState.value.toString()}")
        } else {
            throw IllegalStateException("AuthViewModel class: setUser() called and user state has not been updated because the user state is null")
        }
    }
    fun setDefaultUser() {
        _userState.value.let {
            _userState.value = UserState(user = User())
        }
    }

}