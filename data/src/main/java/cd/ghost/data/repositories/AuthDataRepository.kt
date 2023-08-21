package cd.ghost.data.repositories

interface AuthDataRepository {

    /**
     * Login with
     * @param username
     * @param password
     */
    suspend fun signIn(username: String, password: String)

}