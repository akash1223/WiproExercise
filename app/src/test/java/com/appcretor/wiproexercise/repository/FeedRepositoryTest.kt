package com.appcretor.wiproexercise.repository

import com.appcretor.wiproexercise.http.Resource
import com.appcretor.wiproexercise.utils.FailureResponseDispatcher
import com.appcretor.wiproexercise.utils.SuccessResponseDispatcher
import com.appcretor.wiproexercise.utils.TestData
import com.google.gson.Gson

import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FeedRepositoryTest : BaseRepositoryTest() {


    private lateinit var feedsRepository: FeedsRepository

    @Before
    fun subSetUp() {
        feedsRepository = FeedsRepository(iHttpService, localizeTextProvider)
    }

    @Test
    fun `test_callFeed()_return_success`() {
        runBlocking {
            mockWebServer.dispatcher = SuccessResponseDispatcher.dispatcher()
            val call = feedsRepository.executeFeed()
            assertTrue(call.data != null)
            assertTrue(call.status == Resource.Status.SUCCESS)
            assertNotNull(call.data?.rows)
            assertNotNull(call.data?.title)
        }
    }

    @Test
    fun `test_callFeed()_return_401_email_or_password_incorrect`() {
        runBlocking {
            mockWebServer.dispatcher = FailureResponseDispatcher.dispatcher()
            val call = feedsRepository.executeFeed()
            assertTrue(call.status == Resource.Status.ERROR)
            assertNotNull(call.message)
        }
    }


/*
    @Test
    fun `test_executeSignInUser()`() {
        runBlocking {
            val mockJson = MockResponseFileReader("UserAuthResponse.json").content
            var authResponse = Gson().fromJson(mockJson, UserAuthResponse::class.java)
            *//*Mockito.doReturn(Resource.success(authResponse))
                .`when`(baseRepository).getResult { mockIHttpService.callSignInUser(TestData.TEST_VALID_EMAIL, TestData.TEST_VALID_PASSWORD) }
*//*
            `when`(baseRepository.getResult<UserAuthResponse>()).thenReturn(Resource.success(authResponse))

            var signInRepository = SignInRepository(mockIHttpService, localizeTextProvider)
            var call = signInRepository.executeSignInUser(
                TestData.TEST_VALID_EMAIL,
                TestData.TEST_VALID_PASSWORD,
                baseRepository
            )

                assertNotNull(call.data?.access)
                assertNotNull(call.data?.refresh)


        }
    }*/
}