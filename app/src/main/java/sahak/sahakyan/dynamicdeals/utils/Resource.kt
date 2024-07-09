package sahak.sahakyan.dynamicdeals.utils

import sahak.sahakyan.dynamicdeals.data.model.User

sealed class Resource<out T> {
    data class Success<out T>(val data: T?) : Resource<T>()
    data class Error(val message: String?) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
}

data class UserState(
    val user: User = User(),
    val error: String = ""
)