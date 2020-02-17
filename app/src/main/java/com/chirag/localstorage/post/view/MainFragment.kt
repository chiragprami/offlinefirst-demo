package com.chirag.localstorage.post.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.chirag.localstorage.R
import com.chirag.localstorage.post.entity.Post
import com.chirag.localstorage.post.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var postAdapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        observer()
        initData()
    }

    private fun initData() {
        viewModel.dbUpdateListener()
        viewModel.getStoredData()
        viewModel.getPosts()
    }

    private fun observer() {
        viewModel.observePostsLiveData().observe(activity!!, Observer { result ->
            updatePostList(result)
        })
    }

    private fun updatePostList(list: MutableList<Post>) {
        if (!::postAdapter.isInitialized) {
            postAdapter = PostAdapter(context!!, list)
            rvPosts.adapter = postAdapter
        } else {
            postAdapter.data = list
            postAdapter.notifyDataSetChanged()
        }
    }

}
