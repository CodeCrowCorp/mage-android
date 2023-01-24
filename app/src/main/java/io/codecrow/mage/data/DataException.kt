package io.codecrow.mage.data


sealed class DataException : RuntimeException {

    constructor() : super()

    constructor(message: String) : super(message)

    object Network : DataException()

    object Conversion : DataException()

    object Database : DataException()

    data class Api(
        override val message: String,
        val status: String?
    ) : DataException(message)
}