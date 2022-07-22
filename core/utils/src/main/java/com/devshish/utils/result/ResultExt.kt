package com.devshish.utils.result

/**
 * Map success result of type [S] to some other type [R]
 */
inline fun <S, E, R> Result<S, E>.mapSuccess(block: (S) -> R): Result<R, E> =
    when (this) {
        is Result.Success -> Result.Success(result = block(this.result))
        is Result.Error -> Result.Error(result = this.result)
    }

/**
 * Map error result of type [E] to some other type [R]
 */
inline fun <S, E, R> Result<S, E>.mapError(block: (E) -> R): Result<S, R> =
    when (this) {
        is Result.Success -> Result.Success(result = this.result)
        is Result.Error -> Result.Error(result = block(this.result))
    }

/**
 * Some action done after [Result.Success] completion of action
 */
inline fun <S, E> Result<S, E>.doOnSuccess(block: (S) -> Unit): Result<S, E> {
    if (this is Result.Success) {
        block(this.result)
    }
    return this
}

/**
 * Some action done after [Result.Error] completion of action
 */
inline fun <S, E> Result<S, E>.doOnError(block: (E) -> Unit): Result<S, E> {
    if (this is Result.Error) {
        block(this.result)
    }
    return this
}
