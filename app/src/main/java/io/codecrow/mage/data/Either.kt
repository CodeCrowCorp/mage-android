package io.codecrow.mage.data


/**
 * Represents a value of one of two possible types (a disjoint union).
 * Instances of [Either] are either an instance of [Left] or [Right].
 * FP Convention dictates that:
 *      [Left] is used for "failure".
 *      [Right] is used for "success".
 *
 * @see Left
 * @see Right
 */
sealed class Either<out L, out R> {
    /** * Represents the left side of [Either] class which by convention is a "Failure". */
    data class Left<out L>(val value: L) : Either<L, Nothing>()

    /** * Represents the right side of [Either] class which by convention is a "Success". */
    data class Right<out R>(val value: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>

    val isLeft get() = this is Left<L>

    fun <C> either(fnL: (L) -> C, fnR: (R) -> C): C =
        when (this) {
            is Left -> fnL(value)
            is Right -> fnR(value)
        }

    fun get(): Pair<L?, R?> {
        return Pair(
            leftOrNull()?.value,
            rightOrNull()?.value
        )
    }

    suspend fun <C> suspendEither(fnL: suspend (L) -> C, fnR: suspend (R) -> C): C =
        when (this) {
            is Left -> fnL(value)
            is Right -> fnR(value)
        }

    fun leftOrNull(): Left<L>? = if (isLeft) {
        this as Left<L>
    } else {
        null
    }

    fun rightOrNull(): Right<R>? = if (isRight) {
        this as Right<R>
    } else {
        null
    }
}