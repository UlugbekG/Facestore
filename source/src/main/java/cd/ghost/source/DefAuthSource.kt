package cd.ghost.source

import cd.ghost.source.auth.AuthApi
import cd.ghost.source.auth.AuthSource
import cd.ghost.source.auth.entity.AuthRequestBody
import cd.ghost.source.auth.entity.TokenResponse
import javax.inject.Inject

class DefAuthSource @Inject constructor(
    private val authApi: AuthApi
) : BaseSource(), AuthSource {

    override suspend fun signIn(body: AuthRequestBody): TokenResponse =
        catchExceptions {
            authApi.signIn(body)
        }

}