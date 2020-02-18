package com.chirag.localstorage.post.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.chirag.localstorage.R
import com.chirag.localstorage.extension.logv
import com.chirag.localstorage.post.entity.Post
import com.chirag.localstorage.post.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_post.*
import kotlinx.android.synthetic.main.fragment_post.view.*

class PostFragment : Fragment() {

    companion object {
        fun newInstance() = PostFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var postAdapter: PostAdapter

    lateinit var navController: NavController

    lateinit var vView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        observer()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vView =  inflater.inflate(R.layout.fragment_post, container, false)
        return vView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initData()
    }

    private fun initData() {
        viewModel.dbUpdateListener()
        viewModel.getStoredData()
        viewModel.getPosts()
        logv("initData")
    }

    private fun observer() {
        viewModel.observePostsLiveData().observe(activity!!, Observer { result ->
            logv("observePostsLiveData ${result.size}")
            updatePostList(result)
        })
    }

    private fun updatePostList(list: MutableList<Post>) {
        if (!::postAdapter.isInitialized) {
            postAdapter = PostAdapter(context!!, list)
            vView.rvPosts.adapter = postAdapter
            postAdapter.addItemClickListener {post->
                Navigation.createNavigateOnClickListener(R.id.postDetailFragment, null)
                viewPostDetail(post)
            }
            logv("postAdapter ${list.size}")
        } else {
            postAdapter.data = list
            postAdapter.notifyDataSetChanged()
            logv("notifyDataSetChanged ${postAdapter.data.size}")
        }
    }

    private fun viewPostDetail(post:Post){
        val bundle = bundleOf(ARG_POST to post)
        navController.navigate(R.id.action_post_detail,bundle)
    }

}
