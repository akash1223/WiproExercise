package com.appcretor.wiproexercise.ui.feed

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.appcretor.wiproexercise.R
import com.appcretor.wiproexercise.databinding.FragmentFeedBinding
import com.appcretor.wiproexercise.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedFragment : BaseFragment() {

    private val mViewModel: FeedViewModel by viewModel()
    private lateinit var mBinding: FragmentFeedBinding

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
    }

    override fun setup() {

    }


}