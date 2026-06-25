package com.es.jma.domain

import com.es.jma.data.repository.CatRepository
import com.es.jma.model.CatInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatImagesUseCase @Inject constructor(
    private val repository: CatRepository
){
    operator fun invoke(limit: Int): Flow<List<CatInfo>> = repository.getCatImages(limit = limit)
}