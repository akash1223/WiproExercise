package com.appcretor.wiproexercise.ui.feed

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.appcretor.wiproexercise.R
import com.appcretor.wiproexercise.databinding.FragmentFeedBinding
import com.appcretor.wiproexercise.databinding.ListItemFeedBinding
import com.appcretor.wiproexercise.model.FeedsResponse
import com.appcretor.wiproexercise.ui.base.BaseFragment
import com.appcretor.wiproexercise.ui.base.GenericListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedFragment : BaseFragment() {

    private val mViewModel: FeedViewModel by viewModel()
    private lateinit var mBinding: FragmentFeedBinding
    private lateinit var adapter :GenericListAdapter<FeedsResponse.Feed?,ListItemFeedBinding?>

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

        adapter = object :GenericListAdapter<FeedsResponse.Feed?, ListItemFeedBinding?>(
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

        mBinding.recyclerFeedsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapter
        }

        mViewModel.mFeedData.observe(viewLifecycleOwner, Observer {

        })
    }


}