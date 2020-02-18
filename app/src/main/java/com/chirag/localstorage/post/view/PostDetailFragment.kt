package com.chirag.localstorage.post.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chirag.localstorage.R
import com.chirag.localstorage.post.entity.Post
import kotlinx.android.synthetic.main.fragment_post_detail.view.*

const val ARG_POST = "ARG_POST"

class PostDetailFragment : Fragment() {
    private var post: Post? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            post = it.getSerializable(ARG_POST) as Post?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        post?.let {
            view.tvTitle.text = it.title
        }
    }
}
