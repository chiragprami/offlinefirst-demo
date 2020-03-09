package com.chirag.localstorage.modules.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.chirag.localstorage.R
import com.chirag.localstorage.entity.Items
import com.chirag.localstorage.modules.owner.view.OwnerDetailFragment
import com.chirag.localstorage.modules.search.viewmodel.SerchViewModel
import kotlinx.android.synthetic.main.fragment_search_result.view.*
import kotlin.collections.ArrayList


class SearchFragment : Fragment() {


    private lateinit var viewModel: SerchViewModel

    lateinit var navController: NavController

    lateinit var vView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SerchViewModel::class.java)
        observer()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vView = inflater.inflate(R.layout.fragment_search_result, container, false)
        return vView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initData()
        initClickListener()
    }

    private fun initData() {
    }

    private fun initClickListener() {
        vView.etSearch.doAfterTextChanged {
            viewModel.searchResult(it.toString())
        }
    }

    private fun observer() {
        viewModel.observePostsLiveData().observe(this, Observer { result ->
            updateSearchResult(result)
        })
    }


    private fun updateSearchResult(list: ArrayList<Items>) {
        list?.let {
            val searchResultAdapter = SearchResultAdapter(activity!!, list)
            vView.rvPostsss.adapter = searchResultAdapter
            searchResultAdapter.addItemClickListener { post ->
                Navigation.createNavigateOnClickListener(R.id.ownerDetailFragment, null)
                viewPostDetail(post)
            }
        }

    }

    private fun viewPostDetail(repo:Items) {
        OwnerDetailFragment.start(navController,repo)
    }

}
