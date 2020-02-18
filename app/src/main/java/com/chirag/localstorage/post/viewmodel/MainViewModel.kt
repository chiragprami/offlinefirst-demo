package com.chirag.localstorage.post.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirag.localstorage.post.entity.Post
import com.chirag.localstorage.post.repo.PostRepo
import com.chirag.localstorage.post.storage.PostRealmOperation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val postLiveData = MutableLiveData<MutableList<Post>>()

    private val repo = PostRepo()

    private val storage = PostRealmOperation()


    fun getPosts() {
        repo.getPosts { result ->
            storage.storePostsListingData(result)
        }
    }

    fun getStoredData() {
        storage.getStoredData { result ->
            postLiveData.value = result
        }
    }

    fun dbUpdateListener() {
        storage.updateStorageDataListener { result ->
            postLiveData.value = result
        }
    }

    fun observePostsLiveData(): MutableLiveData<MutableList<Post>> {
        return postLiveData
    }

}
