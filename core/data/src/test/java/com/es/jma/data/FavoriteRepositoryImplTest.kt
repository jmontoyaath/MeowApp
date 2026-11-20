package com.es.jma.data

import app.cash.turbine.test
import com.es.jma.data.mapper.FavoriteMapper
import com.es.jma.data.repository.FavoriteRepositoryImpl
import com.es.jma.database.dao.FavoriteDao
import com.es.jma.database.model.CatEntity
import com.es.jma.model.CatInfo
import com.es.jma.testing.fakeCat
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FavoriteRepositoryImplTest {
    private val repository = FavoriteRepositoryImpl(FakeFavoriteDao(), FavoriteMapper())

    @Test
    fun `saving a cat emits it in favorites`() = runTest {
        repository.getFavoriteCats().test {
            assertEquals(emptyList<CatInfo>(), awaitItem())
            repository.saveFavoriteCat(fakeCat)
            assertEquals(fakeCat.id, awaitItem().single().id)
        }
    }

    @Test
    fun `removing a cat emits an empty list`() = runTest {
        repository.saveFavoriteCat(fakeCat)
        repository.getFavoriteIds().test {
            assertEquals(listOf(fakeCat.id), awaitItem())
            repository.removeFavoriteCat(fakeCat.id)
            assertEquals(emptyList<String>(), awaitItem())
        }
    }
}

private class FakeFavoriteDao : FavoriteDao {
    private val cats = MutableStateFlow<List<CatEntity>>(emptyList())

    override fun getAllFavoritesIds() = cats.map { list -> list.map { it.id } }
    override fun getAllFavorites() = cats
    override fun isFavorite(catId: String) = cats.map { list -> list.any { it.id == catId } }
    override suspend fun insert(cat: CatEntity) { cats.update { it.filterNot { c -> c.id == cat.id } + cat } }
    override suspend fun delete(catId: String) { cats.update { it.filterNot { c -> c.id == catId } } }
}