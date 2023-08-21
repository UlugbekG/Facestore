package cd.ghost.auth.domain

import android.util.Log
import cd.ghost.auth.domain.exceptions.EmptyPasswordException
import cd.ghost.auth.domain.exceptions.EmptyUsernameException
import cd.ghost.auth.domain.exceptions.IncorrectPasswordLengthException
import cd.ghost.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    /**
     * Sign in with username & password
     * @throws EmptyUsernameException
     * @throws EmptyPasswordException
     * @throws IncorrectPasswordLengthException
     */
    suspend fun signIn(username: String?, password: String?) {
        if (username.isNullOrBlank()) throw EmptyUsernameException()
        if (password.isNullOrBlank()) throw EmptyPasswordException()
        authRepository.signIn(username, password)
    }

}