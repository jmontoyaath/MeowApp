package com.es.jma.data

import com.es.jma.data.mapper.CatMapper
import com.es.jma.data.repository.CatRepositoryImpl
import com.es.jma.network.CatApiDataSource
import com.es.jma.network.model.CatBreed
import com.es.jma.network.model.RandomCat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import java.io.IOException

class CatRepositoryImplTest {
    private val dataSource = mockk<CatApiDataSource>()
    private val repository = CatRepositoryImpl(dataSource, CatMapper())

    @Test
    fun `getCatImages maps network cats to domain models`() = runTest {
        val network = RandomCat(
            id = "1", url = "https://img",
            breeds = listOf(CatBreed(id = "abys", name = "Abyssinian", countryCodes = "EG"))
        )
        coEvery { dataSource.getRandomCats(limit = 10, page = 0) } returns Result.success(listOf(network))

        val cat = repository.getCatImages(limit = 10, page = 0).getOrThrow().single()

        assertEquals("abys", cat.idBreed)
        assertEquals("EG", cat.origin)
    }

    @Test
    fun `getCatImages propagates network failure`() = runTest {
        coEvery { dataSource.getRandomCats(any(), any()) } returns Result.failure(IOException())

        assertTrue(repository.getCatImages().isFailure)
    }
}