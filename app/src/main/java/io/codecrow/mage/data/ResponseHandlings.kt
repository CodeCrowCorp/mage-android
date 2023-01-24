package io.codecrow.mage.data

import retrofit2.Response
import java.io.IOException

internal suspend fun <T, R> handle(
    apiCall: suspend () -> Response<T>,
    errorHandler: () -> Either<DataException, R> = {
        Either.Left(
            DataException.Api(
                message = "Something went wrong!",
                status = "ERROR"
            )
        )
    },
    handler: (T?) -> Either<DataException, R>
): Either<DataException, R> = try {
    val call = apiCall()
    val body = call.body()
    if (call.isSuccessful && body != null) {
        try {
            handler(body)
        } catch (e: Exception) {
            errorHandler()
        }
    } else {
        Either.Left(
            DataException.Api(
                message = "Something went wrong!",
                status = "ERROR"
            )
        )
    }
} catch (e: Exception) {
    e.printStackTrace()
    if (e is IOException) {
        Either.Left(DataException.Network)
    } else {
        Either.Left(DataException.Conversion)
    }
}
