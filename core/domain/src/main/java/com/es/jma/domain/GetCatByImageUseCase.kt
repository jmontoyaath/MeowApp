package com.es.jma.domain

import com.es.jma.data.repository.CatRepository
import com.es.jma.model.CatInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatByImageUseCase @Inject constructor(
    private val repository: CatRepository
){
    operator fun invoke(idImage: String): Flow<CatInfo> = repository.getCatByImage(idImage = idImage)
}