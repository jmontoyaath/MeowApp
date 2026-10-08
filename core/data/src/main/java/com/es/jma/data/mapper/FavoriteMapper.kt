package com.es.jma.data.mapper

import com.es.jma.database.model.CatEntity
import com.es.jma.model.CatInfo
import javax.inject.Inject

class FavoriteMapper @Inject constructor() : Mapper<CatInfo, CatEntity> {
    override fun map(params: CatInfo) = CatEntity(
        id = params.id,
        url = params.url,
        name = params.name,
        idBreed = params.idBreed,
        origin = params.origin,
        description = params.description,
        temperament = params.temperament,
    )

    fun mapEntityToDomain(params: CatEntity) = CatInfo(
        id = params.id,
        url = params.url,
        idBreed = params.idBreed,
        name = params.name,
        origin = params.origin,
        description = params.description,
        temperament = params.temperament
    )
}