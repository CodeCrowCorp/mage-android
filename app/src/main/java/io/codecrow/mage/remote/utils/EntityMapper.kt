package io.codecrow.mage.remote.utils

interface EntityMapper <Entity, DomainModel>{

    fun mapFromEntity(entity: Entity): DomainModel

    fun mapToEntity(domainModel: DomainModel): Entity
}


interface Mapper<T, U> {
    suspend fun map(input: T): U
}