package cd.ghost.data

import cd.ghost.common.NoConnectionException
import cd.ghost.common.ParseBackendException
import retrofit2.HttpException
import java.io.IOException

open class BaseRepository {

    suspend fun <T> catchingBlock(bloc: suspend () -> T): T = try {
        bloc()
    } catch (e: HttpException) {
        throw ParseBackendException(e)
    } catch (e: IOException) {
        throw NoConnectionException()
    } catch (e: Exception) {
        throw e
    }

}