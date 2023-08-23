package cd.ghost.data.repositories

import cd.ghost.data.repositories.AuthDataRepository
import cd.ghost.source.auth.AuthSource
import cd.ghost.source.auth.entity.AuthRequestBody
import cd.ghost.source.settings.AppSettings
import javax.inject.Inject

class DefAuthDataRepository @Inject constructor(
    private val authSource: AuthSource,
    private val appSettings: AppSettings
) : AuthDataRepository {

    override suspend fun signIn(username: String, password: String) {
        val body = AuthRequestBody(username, password)
        val token = authSource.signIn(body).value
        appSettings.setCurrentToken(token)
    }

}