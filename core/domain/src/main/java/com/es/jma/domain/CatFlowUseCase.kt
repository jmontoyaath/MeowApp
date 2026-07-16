package com.es.jma.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class CatFlowUseCase<in Params, out Results>(
    private val coroutineDispatcher: CoroutineDispatcher
) {
    operator fun invoke(parameters: Params): Flow<Results> =
        execute(parameters).flowOn(coroutineDispatcher)

    protected abstract fun execute(parameters: Params): Flow<Results>
}