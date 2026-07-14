package com.es.jma.domain.usecase

import com.es.jma.data.repository.CatRepository
import com.es.jma.domain.CatUseCase
import com.es.jma.domain.di.IoDispatcher
import com.es.jma.model.CatInfo
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetCatImagesUseCase @Inject constructor(
    private val repository: CatRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CatUseCase<GetCatImagesParams, List<CatInfo>>(dispatcher) {

    override suspend fun execute(parameters: GetCatImagesParams): Result<List<CatInfo>> {
        return repository.getCatImages(limit = parameters.limit, page = parameters.page)
    }
}

data class GetCatImagesParams(
    val limit: Int,
    val page: Int
)