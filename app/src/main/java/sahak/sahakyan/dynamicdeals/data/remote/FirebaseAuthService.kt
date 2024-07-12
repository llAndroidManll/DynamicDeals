package sahak.sahakyan.dynamicdeals.data.remote

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class FirebaseAuthService @Inject constructor(
    private val auth: FirebaseAuth
) {

    suspend fun signUpWithEmail(email: String, password: String, onComplete: (Result<AuthResult>) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
           .addOnCompleteListener { task ->
               if (task.isSuccessful)   onComplete(Result.success(task.result))
               else onComplete(Result.failure(task.exception ?: Exception("Unknown error")))
           }
    }

    suspend fun signInWithEmail(email: String, password: String, onComplete: (Result<AuthResult>) -> Unit) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful)   onComplete(Result.success(task.result))
                else onComplete(Result.failure(task.exception ?: Exception("Unknown error")))
            }
    }

    suspend fun sendVerificationEmail(onComplete: (Result<Void>) -> Unit) {
        auth.currentUser?.sendEmailVerification()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(Result.success(task.result))
                } else {
                    onComplete(Result.failure(task.exception ?: Exception("Unknown error")))
                }
            }
    }

}