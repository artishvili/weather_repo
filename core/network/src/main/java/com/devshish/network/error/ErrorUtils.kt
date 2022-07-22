package com.devshish.network.error

import androidx.annotation.StringRes
import com.devshish.mvvm.text.NativeText
import com.devshish.network.json
import com.devshish.utils.result.Result
import kotlinx.serialization.decodeFromString
import retrofit2.Response
import timber.log.Timber

// TODO Move to :base:utils module
/**
 * Process network request, additionally mapping it to [Result] instance
 */
suspend fun <T> processRequest(
    @StringRes defaultMessageRes: Int,
    runRequest: suspend () -> Response<T>
): Result<T, NativeText> {
    return try {
        val response = runRequest()
        val body = response.body()
        if (body != null) {
            Result.Success(body)
        } else {
            val errorModel: ErrorModel = parseErrorBody(response)
            Timber.e("Network parsed error model: $errorModel")
            Result.Error(NativeText.Simple(errorModel.error))
        }
    } catch (e: Throwable) {
        Timber.e(e)
        Result.Error(NativeText.Resource(defaultMessageRes))
    }
}

/**
 * Parse current response to [ErrorModel]
 *
 * @throws [error] when there are no [Response.errorBody] in provided Response
 */
private fun <T> parseErrorBody(response: Response<T>): ErrorModel {
    val errorBody = response.errorBody() ?: error("$response does not have errorBody")
    return json.decodeFromString(errorBody.string())
}
