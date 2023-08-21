package cd.ghost.fakestore.features.auth.repositories

import cd.ghost.auth.domain.repositories.AuthRepository
import cd.ghost.data.repositories.AuthDataRepository
import javax.inject.Inject

class AdapterAuthRepository @Inject constructor(
    private val authDataRepository: AuthDataRepository,
) : AuthRepository {

    override suspend fun signIn(username: String, password: String) {
        authDataRepository.signIn(username, password)
    }

}