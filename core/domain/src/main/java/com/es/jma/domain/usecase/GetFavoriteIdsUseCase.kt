package com.es.jma.domain.usecase

import com.es.jma.data.di.IoDispatcher
import com.es.jma.data.repository.FavoriteRepository
import com.es.jma.domain.CatFlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteIdsUseCase @Inject constructor(
    private val repository: FavoriteRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CatFlowUseCase<Unit, List<String>>(dispatcher) {

    override fun execute(parameters: Unit): Flow<List<String>> =
        repository.getFavoriteIds()
}