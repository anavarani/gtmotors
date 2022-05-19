package com.varani.gtmotors.ui.searchresults

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.flextrade.jfixture.annotations.Fixture
import com.varani.gtmotors.BaseTest
import com.varani.gtmotors.api.NetworkResult
import com.varani.gtmotors.domain.search.Repository
import com.varani.gtmotors.domain.search.SearchResponseDomain
import com.varani.gtmotors.ui.search.SearchViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchResultsViewModelTest : BaseTest() {

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var mockRepository: Repository

    @MockK
    lateinit var uiStateObserver: Observer<SearchResultsViewModel.UiState>

    @Fixture
    lateinit var fixtSearchResponseDomain: SearchResponseDomain

    @Fixture
    lateinit var fixtFilterState: SearchViewModel.FilterState

    private lateinit var sut: SearchResultsViewModel

    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    override fun setUp() {
        Dispatchers.setMain(dispatcher)

        sut = SearchResultsViewModel(
            mockRepository
        )
        sut.uiState.observeForever(uiStateObserver)
    }

    @After
    override fun after() {
        super.after()
        Dispatchers.resetMain()
    }

    @Test
    fun `given data when init then search vehicles and emit loaded state`() = runTest {
        //given
        coEvery { mockRepository.searchVehicles(any()) } returns NetworkResult.Success(
            fixtSearchResponseDomain
        )

        //when
        sut.init(fixtFilterState)

        //then
        val slot = mutableListOf<SearchResultsViewModel.UiState>()
        verify { uiStateObserver.onChanged(capture(slot)) }
        assertTrue(slot.isNotEmpty())
        assertTrue(slot[0] is SearchResultsViewModel.UiState.Loading)
        assertTrue(slot[1] is SearchResultsViewModel.UiState.Loaded)
        assertEquals(
            fixtSearchResponseDomain.toModel(),
            (slot[1] as SearchResultsViewModel.UiState.Loaded).result
        )
    }

    @Test
    fun `given error when init then search vehicles and emit error state`() = runTest {
        //given
        val exception = Exception("My Exception")
        coEvery { mockRepository.searchVehicles(any()) } returns NetworkResult.Failure(
            exception
        )

        //when
        sut.init(fixtFilterState)

        //then
        val slot = mutableListOf<SearchResultsViewModel.UiState>()
        verify { uiStateObserver.onChanged(capture(slot)) }
        assertTrue(slot.isNotEmpty())
        assertTrue(slot[0] is SearchResultsViewModel.UiState.Loading)
        assertTrue(slot[1] is SearchResultsViewModel.UiState.Error)
        assertEquals(
            exception.message,
            (slot[1] as SearchResultsViewModel.UiState.Error).message
        )
    }
}