package cd.ghost.source.auth

import cd.ghost.source.auth.entity.AuthRequestBody
import cd.ghost.source.auth.entity.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    /**
     * Login with
     * @param username
     * @param password
     */
    @POST("/auth/login")
    suspend fun signIn(@Body body: AuthRequestBody): TokenResponse

}