package com.varani.gtmotors.domain.search

import com.flextrade.jfixture.annotations.Fixture
import com.varani.gtmotors.BaseTest
import com.varani.gtmotors.api.NetworkResult
import com.varani.gtmotors.api.VehicleApi
import com.varani.gtmotors.dto.SearchResponseDto
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchRepositoryTest : BaseTest() {

    @MockK
    private lateinit var mockVehicleApi: VehicleApi

    @Fixture
    private lateinit var fixtSearchRequestDomain: SearchRequestDomain

    @Fixture
    private lateinit var fixtSearchResponseDto: SearchResponseDto

    private lateinit var sut: SearchRepository

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    override fun setUp() {
        super.setUp()
        sut = SearchRepository(
            mockVehicleApi,
            testDispatcher
        )
    }

    @Test
    fun `given error when search vehicles then return network failure`() = runTest {
        //given
        val exception = Exception("My Exception")

        coEvery {
            mockVehicleApi.searchVehicles(
                fixtSearchRequestDomain.make,
                fixtSearchRequestDomain.model,
                fixtSearchRequestDomain.year,
            )
        } throws exception

        //when
        sut.searchVehicles(fixtSearchRequestDomain).also {
            assertTrue(it is NetworkResult.Failure)
            assertEquals(exception.message, (it as NetworkResult.Failure).throwable.message)
        }
    }

    @Test
    fun `given data when search vehicles then return network success`() = runTest {
        //given
        coEvery {
            mockVehicleApi.searchVehicles(
                fixtSearchRequestDomain.make,
                fixtSearchRequestDomain.model,
                fixtSearchRequestDomain.year,
            )
        } returns fixtSearchResponseDto

        //when
        sut.searchVehicles(fixtSearchRequestDomain).also {
            assertTrue(it is NetworkResult.Success)
            assertEquals(fixtSearchResponseDto.toDomain(), (it as NetworkResult.Success).value)
        }
    }
}