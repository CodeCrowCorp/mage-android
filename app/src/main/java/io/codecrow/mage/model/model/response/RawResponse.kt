package io.codecrow.mage.model.response

class RawResponse<T>(
val status: String?,

val data: List<T>?,

val errorMessage: String?
)