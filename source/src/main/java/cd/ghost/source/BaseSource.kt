package cd.ghost.source

import android.util.Log
import cd.ghost.common.ErrorResponseException
import cd.ghost.common.NoConnectionException
import retrofit2.HttpException
import java.io.IOException

open class BaseSource {

    private val TAG = "BaseRepository"

    suspend fun <T> catchExceptions(bloc: suspend () -> T): T = try {
        bloc()
    } catch (e: HttpException) {
        Log.d(TAG, "catchingBlock: ${e.message()} ${e.code()}")
        throw ErrorResponseException(e.code(), e)
    } catch (e: IOException) {
        Log.d(TAG, "catchingBlock: $e")
        throw NoConnectionException()
    } catch (e: Exception) {
        Log.d(TAG, "catchingBlock: $e")
        throw e
    }

    //return try {
    //         block()
    //      } catch (e: AppException) {
    //         throw e
    //         // moshi
    //      } catch (e: JsonDataException) {
    //         throw ParseBackendResponseException(e)
    //      } catch (e: JsonEncodingException) {
    //         throw ParseBackendResponseException(e)
    //         // retrofit
    //      } catch (e: HttpException) {
    //         throw createBackendException(e)
    //         // mostly retrofit
    //      } catch (e: IOException) {
    //         throw ConnectionException(e)
    //      }
}