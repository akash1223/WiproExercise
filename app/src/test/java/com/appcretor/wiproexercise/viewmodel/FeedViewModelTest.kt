package com.appcretor.wiproexercise.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.appcretor.wiproexercise.http.Resource
import com.appcretor.wiproexercise.model.FeedsResponse
import com.appcretor.wiproexercise.repository.FeedsRepository
import com.appcretor.wiproexercise.ui.feed.FeedViewModel
import com.appcretor.wiproexercise.utils.LocalizeTextProvider
import com.appcretor.wiproexercise.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FeedViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var feedsRepository: FeedsRepository

    @Mock
    private lateinit var localizeTextProvider: LocalizeTextProvider

    @Mock
    private lateinit var apiObserver: Observer<Resource<FeedsResponse>>


    @Before
    fun setUp() {

    }

    @Test
    fun test_objects_not_null() {
        Assert.assertNotNull(feedsRepository)
        Assert.assertNotNull(localizeTextProvider)
        Assert.assertNotNull(apiObserver)
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(Resource.success(FeedsResponse()))
                .`when`(feedsRepository).executeFeed()

            val coroutineViewModel = FeedViewModel(feedsRepository)
            coroutineViewModel.getFeeds().observeForever(apiObserver)
            verify(feedsRepository, times(1)).executeFeed()
            verify(apiObserver).onChanged(Resource.success(FeedsResponse()))
            coroutineViewModel.getFeeds().removeObserver(apiObserver)
        }
    }
    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message For You"
            doReturn(
                Resource.error(
                    RuntimeException(errorMessage).toString(), null
                )
            )
                .`when`(feedsRepository)
                .executeFeed()
            val coroutineViewModel = FeedViewModel(feedsRepository)
            coroutineViewModel.getFeeds().observeForever(apiObserver)
            verify(feedsRepository).executeFeed()
            verify(apiObserver).onChanged(
                Resource.error(
                    RuntimeException(errorMessage).toString(),
                    null
                )
            )
            coroutineViewModel.getFeeds().removeObserver(
                apiObserver
            )
        }
    }

}



