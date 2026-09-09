package com.es.jma.database

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.es.jma.database.dao.FavoriteDao
import com.es.jma.database.model.CatEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException
import kotlin.jvm.java

@RunWith(AndroidJUnit4::class)
class FavoriteDaoTest {
    private lateinit var db: MeowDataBase
    private lateinit var dao: FavoriteDao

    private val fakeCatEntity = CatEntity(id = "1", name = "Pirulina", idBreed = "1", description = "", origen = "", url = "", temperament = "", addedAt = 100)
    private val fakeCatOtherEntity = CatEntity(id = "2", name = "Mayillo", idBreed = "2", description = "", origen = "", url = "", temperament = "", addedAt = 100)

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, MeowDataBase::class.java).build()
        dao = db.favoriteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndReadFavorite_emitInTheFlow() = runTest {
        dao.insert(cat = fakeCatEntity)
        val favorites = dao.getAllFavorites().first()
        assertEquals(1, favorites.size)
    }

    @Test
    fun isFavorite_returnsFalseWhenCatDoesNotExist() = runTest {
        dao.isFavorite("99").test {
            assertFalse(awaitItem())
        }
    }

    @Test
    fun getAllFavorites_returnsCatsOrderedByAddedAtDesc() = runTest {
        dao.insert(fakeCatEntity)
        dao.insert(fakeCatOtherEntity)

        dao.getAllFavorites().test {
            val list = awaitItem()
            assertEquals(2, list.size)
            assertEquals("1", list[0].id)
            assertEquals("2", list[1].id)
        }
    }

    @Test
    fun getAllFavoritesIds_returnsIdsOrderedByAddedAtDesc() = runTest {
        dao.insert(fakeCatEntity)
        dao.insert(fakeCatOtherEntity)

        dao.getAllFavoritesIds().test {
            assertEquals(listOf("1", "2"), awaitItem())
        }
    }

    @Test
    fun delete_removesCatFromFavorites() = runTest {
        dao.insert(fakeCatEntity)
        dao.delete("1")
        dao.isFavorite("1").test {
            assertFalse(awaitItem())
        }
    }
}