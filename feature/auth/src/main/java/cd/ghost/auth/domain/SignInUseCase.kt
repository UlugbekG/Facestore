package cd.ghost.auth.domain

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
        if (password.length < 8) throw IncorrectPasswordLengthException()
        authRepository.signIn(username, password)
    }

}