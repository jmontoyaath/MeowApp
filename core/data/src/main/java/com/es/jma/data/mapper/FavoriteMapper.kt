package com.es.jma.data.mapper

import com.es.jma.database.model.CatEntity
import com.es.jma.model.CatInfo
import javax.inject.Inject

class FavoriteMapper @Inject constructor() : Mapper<CatInfo, CatEntity> {
    override fun map(params: CatInfo) = CatEntity(
        id = params.id,
        url = params.url,
        name = params.breed?.get(0)?.name ?: "",
        idBreed = params.breed?.get(0)?.id ?: "",
        origen = params.breed?.get(0)?.origin ?: "",
        description = params.breed?.get(0)?.description ?: "",
        temperament = params.breed?.get(0)?.temperament ?: "",
    )
}