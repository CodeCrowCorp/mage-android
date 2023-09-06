package io.codecrow.mage.data.datasource

import io.codecrow.mage.data.DataException
import io.codecrow.mage.data.Either
import io.codecrow.mage.model.AuthUser
import io.codecrow.mage.model.Channel

interface AuthRepo {
    suspend fun getCurrentUser(headerMap : HashMap<String,String>): Either<DataException, AuthUser?>
}