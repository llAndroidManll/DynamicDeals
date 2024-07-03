package sahak.sahakyan.dynamicdeals.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import sahak.sahakyan.dynamicdeals.data.model.User
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

) : ViewModel() {

    val user: MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }

}