package io.codecrow.mage.data

import com.google.gson.Gson
import io.codecrow.mage.remote.model.response.ErrorResponse
import retrofit2.Response
import java.io.IOException

internal suspend fun <T, R> handle(
    apiCall: suspend () -> Response<T>,
    errorHandler: (ErrorResponse) -> Either<DataException, R> = {
        Either.Left(
            DataException.Api(
                message = it.message ?: "Something went wrong!",
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
            val errorResponse = Gson().fromJson(call.errorBody()?.string(), ErrorResponse::class.java)
            errorHandler(errorResponse)
        }
    } else {
        val errorResponse = Gson().fromJson(call.errorBody()?.string(), ErrorResponse::class.java)
        errorHandler(errorResponse)
    }
} catch (e: Exception) {
    e.printStackTrace()
    if (e is IOException) {
        Either.Left(DataException.Network)
    } else {
        Either.Left(DataException.Conversion)
    }
}
