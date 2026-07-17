package com.es.jma.domain.usecase

import com.es.jma.data.repository.FavoriteRepository
import com.es.jma.domain.CatFlowUseCase
import com.es.jma.domain.di.IoDispatcher
import com.es.jma.model.CatInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteCatsUseCase @Inject constructor(
    private val repository: FavoriteRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CatFlowUseCase<Unit, List<CatInfo>>(dispatcher) {

    override fun execute(parameters: Unit): Flow<List<CatInfo>> =
        repository.getFavoriteCats()
}