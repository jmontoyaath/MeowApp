package com.es.jma.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class NavigatorTest {
    private lateinit var topLevelStack: NavBackStack<NavKey>
    private lateinit var homeSubStack: NavBackStack<NavKey>
    private lateinit var searchSubStack: NavBackStack<NavKey>
    private lateinit var state: NavigationState
    private lateinit var navigator: Navigator

    @Before
    fun setUp() {
        topLevelStack = NavBackStack(HomeRoute)
        homeSubStack = NavBackStack(HomeRoute)
        searchSubStack = NavBackStack(SearchRoute)

        val subStacks = mapOf(
            HomeRoute to homeSubStack,
            SearchRoute to searchSubStack
        )

        state = NavigationState(
            startKey = HomeRoute,
            topLevelStack = topLevelStack,
            subStacks = subStacks
        )

        navigator = Navigator(state)
    }

    @Test
    fun `navigate to a new route adds key to current subStack`() {
        val detailRoute = DetailRoute(breedId = "123", catImage = "url")

        navigator.navigate(detailRoute)

        assertEquals(detailRoute, state.currentKey)
        assertEquals(2, homeSubStack.size)
    }

    @Test
    fun `navigate to an existing route in subStack moves it to top`() {
        val detail1 = DetailRoute("1", "url1")
        val detail2 = DetailRoute("2", "url2")

        navigator.navigate(detail1)
        navigator.navigate(detail2)
        navigator.navigate(detail1)

        assertEquals(detail1, state.currentKey)
        assertEquals(3, homeSubStack.size)
    }

    @Test
    fun `navigate to another top level route switches active top level key`() {
        navigator.navigate(SearchRoute)

        assertEquals(SearchRoute, state.currentTopLevelKey)
        assertEquals(SearchRoute, state.currentKey)
        assertEquals(2, topLevelStack.size)
    }

    @Test
    fun `navigate to current top level route clears its subStack`() {
        val detailRoute = DetailRoute("1", "url1")
        navigator.navigate(detailRoute)
        assertEquals(2, homeSubStack.size)

        navigator.navigate(HomeRoute)

        assertEquals(1, homeSubStack.size)
        assertEquals(HomeRoute, state.currentKey)
    }

    @Test
    fun `navigate to startKey top level resets topLevelStack`() {
        navigator.navigate(SearchRoute)
        assertEquals(2, topLevelStack.size)

        navigator.navigate(HomeRoute)

        assertEquals(1, topLevelStack.size)
        assertEquals(HomeRoute, state.currentTopLevelKey)
    }

    @Test
    fun `goBack removes last element from current subStack`() {
        val detailRoute = DetailRoute("1", "url1")
        navigator.navigate(detailRoute)

        navigator.goBack()

        assertEquals(HomeRoute, state.currentKey)
        assertEquals(1, homeSubStack.size)
    }

    @Test
    fun `goBack on top level root removes element from topLevelStack`() {
        navigator.navigate(SearchRoute)
        assertEquals(SearchRoute, state.currentTopLevelKey)

        navigator.goBack()

        assertEquals(HomeRoute, state.currentTopLevelKey)
        assertEquals(1, topLevelStack.size)
    }

    @Test
    fun `goBack on startKey throws IllegalStateException`() {
        assertThrows(IllegalStateException::class.java) {
            navigator.goBack()
        }
    }
}