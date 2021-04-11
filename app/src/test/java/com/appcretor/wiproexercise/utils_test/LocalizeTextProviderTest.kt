package com.appcretor.wiproexercise.utils_test


import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.appcretor.wiproexercise.R
import com.appcretor.wiproexercise.utils.LocalizeTextProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LocalizeTextProviderTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    lateinit var localizeTextProvider: LocalizeTextProvider
    private val stringVerifyValue = "Resource data"

    @Mock
    private lateinit var mockContext: Context

    @Before
    fun setUp() {
        mockContext = mock(Context::class.java)
        localizeTextProvider = LocalizeTextProvider(mockContext)
    }

    @Test
    fun notNullCheck() {
        assertNotNull(mockContext)
        assertNotNull(localizeTextProvider)
    }

    @Test
    fun verify_getNoInternetMessage() {
        `when`(mockContext.getString(R.string.no_internet)).thenReturn(stringVerifyValue)
        val result = localizeTextProvider.getNoInternetMessage()
        assertEquals(result, stringVerifyValue)
    }

    @Test
    fun verify_getSomethingWrongMessage() {
        `when`(mockContext.getString(R.string.something_went_wrong)).thenReturn(stringVerifyValue)
        val result = localizeTextProvider.getSomethingWrongMessage()
        assertEquals(result, stringVerifyValue)
    }
}