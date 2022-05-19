package com.varani.gtmotors.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.varani.gtmotors.BaseTest
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest : BaseTest() {

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var searchFilterStateObserver: Observer<SearchViewModel.FilterState>

    private lateinit var sut: SearchViewModel

    @Before
    override fun setUp() {
        sut = SearchViewModel()
        sut.searchFilterState.observeForever(searchFilterStateObserver)
    }

    @Test
    fun `when year is set, then emit new state value`() {
        //given
        val year = fixture.create(String::class.java)

        //when
        sut.setYear(year)

        //then
        val slot = mutableListOf<SearchViewModel.FilterState>()
        verify { searchFilterStateObserver.onChanged(capture(slot)) }
        assertTrue(slot.isNotEmpty())
        assertEquals(year, slot[0].year)
    }

    @Test
    fun `when make is set, then emit new state value`() {
        //given
        val make = fixture.create(String::class.java)
        val model = fixture.create(String::class.java)

        //when
        sut.setMakeModel(make, model)

        //then
        val slot = mutableListOf<SearchViewModel.FilterState>()
        verify { searchFilterStateObserver.onChanged(capture(slot)) }
        assertTrue(slot.isNotEmpty())
        assertEquals(make, slot[0].make)
        assertEquals(model, slot[0].model)
    }

    @Test
    fun `when reset, then emit new empty state value`() {
        //when
        sut.reset()

        //then
        val slot = mutableListOf<SearchViewModel.FilterState>()
        verify { searchFilterStateObserver.onChanged(capture(slot)) }
        assertTrue(slot.isNotEmpty())
        assertTrue(slot[0].make.isEmpty())
        assertTrue(slot[0].model.isEmpty())
        assertTrue(slot[0].year.isEmpty())
    }
}