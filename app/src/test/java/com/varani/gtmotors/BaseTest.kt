package com.varani.gtmotors

import com.flextrade.jfixture.FixtureAnnotations
import com.flextrade.jfixture.JFixture
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import org.junit.After
import org.junit.Before

abstract class BaseTest {

    lateinit var fixture: JFixture

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        fixture = JFixture()
        FixtureAnnotations.initFixtures(this, fixture)
        setUp()
    }

    open fun setUp() {}

    @After
    open fun after() {
        clearAllMocks()
    }
}