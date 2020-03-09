package com.chirag.localstorage.modules.owner.view

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
import com.chirag.localstorage.entity.Items
import com.chirag.localstorage.entity.Owner
import com.chirag.localstorage.extension.toast
import com.chirag.localstorage.modules.owner.viewmodel.OwnerViewModel
import com.chirag.localstorage.modules.search.view.SearchResultAdapter
import kotlinx.android.synthetic.main.fragment_owner_detail.*
import kotlinx.android.synthetic.main.fragment_search_result.view.*


class OwnerDetailFragment : Fragment() {


    companion object{
        val argItems = "Items"
       fun start(navController:NavController,item:Items){
            val bundle = bundleOf(argItems to item)
            navController.navigate(R.id.action_search_detail, bundle)
        }
    }

    private lateinit var viewModel: OwnerViewModel
    lateinit var navController: NavController
    lateinit var vView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OwnerViewModel::class.java)
        viewModel.item = this.arguments!!.getSerializable(argItems) as Items?
        observer()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vView = inflater.inflate(R.layout.fragment_owner_detail, container, false)
        return vView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initData()
    }

    private fun initData() {
        updateDetail(items = viewModel!!.item!!)
        viewModel.getRepoContributor()
    }

    private fun observer() {
        viewModel.observePostsLiveData().observe(this, Observer { result ->
            context!!.toast(result.size.toString())

            result?.let {
                val searchResultAdapter = OwnerAdapter(activity!!, it!!)
                vView.rvPostsss.adapter = searchResultAdapter
                searchResultAdapter.addItemClickListener { post ->
                }
            }
        })
    }

    private fun updateDetail(items: Items) {
        tvName.text = items.full_name
        tvFullName.text = items.owner!!.name
        tvDescription.text = items.description
    }


}
