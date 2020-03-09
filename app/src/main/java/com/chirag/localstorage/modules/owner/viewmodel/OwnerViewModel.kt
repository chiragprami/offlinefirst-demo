package com.chirag.localstorage.modules.owner.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirag.localstorage.entity.Items
import com.chirag.localstorage.entity.Owner
import com.chirag.localstorage.modules.owner.storage.OwnerDB
import com.chirag.localstorage.repo.SearchRepo
import com.chirag.localstorage.modules.search.storage.SearchResultDb
import com.chirag.localstorage.repo.OwnerRepo
import kotlinx.coroutines.launch

class OwnerViewModel : ViewModel() {

     val contributor = MutableLiveData<ArrayList<Owner>>()
    private val repo = OwnerRepo()
     var item:Items? = null

    fun getRepoContributor() {
        viewModelScope.launch {
            item?.let {
                val result = repo.getContributers(item!!.name!!,item!!.owner!!.login!!){
                    contributor.postValue(it)
                }
            }

        }
    }

    fun observePostsLiveData(): MutableLiveData<ArrayList<Owner>> {
        return contributor
    }

}
