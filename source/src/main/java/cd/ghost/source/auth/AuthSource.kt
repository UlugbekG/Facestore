package cd.ghost.source.auth

import cd.ghost.source.auth.entity.AuthRequestBody
import cd.ghost.source.auth.entity.TokenResponse

interface AuthSource {
    suspend fun signIn(body: AuthRequestBody): TokenResponse
}