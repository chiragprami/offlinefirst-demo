package com.chirag.localstorage.modules.search.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirag.localstorage.entity.Items
import com.chirag.localstorage.repo.SearchRepo
import com.chirag.localstorage.modules.search.storage.SearchResultDb
import kotlinx.coroutines.launch

class SerchViewModel : ViewModel() {

    private val searchLiveData = MutableLiveData<ArrayList<Items>>()

    private val repo = SearchRepo()

    private val storage = SearchResultDb()

    private var query: String = ""

    fun searchResult(q: String) {
        this.query = q
        viewModelScope.launch {
            repo.searchGitHubRepo(query)
            getStoredData()
        }
    }

    private fun getStoredData() {
        viewModelScope.launch {
            storage.getStoredData(query) { result ->
                searchLiveData.postValue(result)
            }
        }
    }

    fun observePostsLiveData(): MutableLiveData<ArrayList<Items>> {
        return searchLiveData
    }

}
