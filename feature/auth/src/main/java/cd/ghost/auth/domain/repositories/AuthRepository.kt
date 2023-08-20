package cd.ghost.auth.domain.repositories

interface AuthRepository {

    suspend fun signIn(username: String, password: String)

}