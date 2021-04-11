package com.appcretor.wiproexercise.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.appcretor.wiproexercise.R
import com.appcretor.wiproexercise.databinding.FragmentFeedBinding
import com.appcretor.wiproexercise.databinding.ListItemFeedBinding
import com.appcretor.wiproexercise.http.Resource
import com.appcretor.wiproexercise.model.FeedsResponse
import com.appcretor.wiproexercise.ui.base.BaseFragment
import com.appcretor.wiproexercise.ui.base.GenericListAdapter
import com.appcretor.wiproexercise.utils.LocalizeTextProvider
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.android.ext.android.inject

class FeedFragment : BaseFragment() {

    private val mViewModel: FeedViewModel by viewModel()
    private lateinit var mBinding: FragmentFeedBinding
    private lateinit var genericAdapter : GenericListAdapter<FeedsResponse.Feed?, ListItemFeedBinding?>
    private val localizeTextProvider: LocalizeTextProvider by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_feed, container, false
        )
        mBinding.lifecycleOwner = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    override fun setup() {

        feedListSetup()

        mViewModel.getFeeds().observe(viewLifecycleOwner, Observer {response->
            if (mBinding.swipeRefresh.isRefreshing) {
                if (response.status != Resource.Status.LOADING)
                    mBinding.swipeRefresh.isRefreshing = false
            } else {
                if (response.status == Resource.Status.LOADING) showLoading() else hideLoading()
            }

            when (response.status) {
                Resource.Status.SUCCESS -> {
                    response.data?.let { setDataToUI(it) }
                }
                Resource.Status.NO_INTERNET -> {
                    showAlertDialogWithOK(localizeTextProvider.getErrorMessage(),localizeTextProvider.getNoInternetMessage(),null)
                }
                Resource.Status.ERROR -> {
                    response.message?.let { showSnackBar(mBinding.recyclerFeedsList, it,true) }
                }
                else->{
                    response.message?.let { showSnackBar(mBinding.recyclerFeedsList, it,true) }
                }
            }
        })
    }

    private fun feedListSetup() {
        genericAdapter = object : GenericListAdapter<FeedsResponse.Feed?, ListItemFeedBinding?>(
            requireContext()
        ) {
            override val layoutResId: Int
                get() = R.layout.list_item_feed

            override fun onBindData(
                model: FeedsResponse.Feed?,
                position: Int,
                dataBinding: ListItemFeedBinding?
            ) {
                if (dataBinding != null) {

                    dataBinding.item = model
                }
            }

            override fun onItemClick(model: FeedsResponse.Feed?, position: Int) {}
        }
        // pull to refresh setup
        mBinding.swipeRefresh.setOnRefreshListener {
            mViewModel.fetchFeedsData()
        }
        mBinding.recyclerFeedsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = genericAdapter
        }
    }

    private fun setDataToUI(it: FeedsResponse) {
       requireActivity()!!.findViewById<Toolbar>(R.id.toolbar).title = it.title
        it.rows?.let { it1 ->
            genericAdapter.submitList(it1) }
    }


}