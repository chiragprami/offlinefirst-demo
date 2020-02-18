package com.chirag.localstorage.post.storage

import com.chirag.localstorage.MyApplication
import com.chirag.localstorage.post.entity.Post
import io.realm.RealmList
import io.realm.RealmResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRealmOperation {

    fun storePostsListingData(post: ArrayList<Post>) {
        val realm = MyApplication.realmInstance()
        realm.executeTransactionAsync {
            val realmList = RealmList<Post>()
            realmList.addAll(post)
            it.insertOrUpdate(realmList)
        }
    }

    suspend fun getStoredData(updatedData: (MutableList<Post>) -> Unit = { }) =
        withContext(Dispatchers.IO) {
            val realm = MyApplication.realmInstance()
            realm.executeTransactionAsync {

                val post: RealmResults<Post> = it.where(Post::class.java).findAllAsync()
                updatedData(post)
            }
        }

    fun updateStorageDataListener(updatedData: (MutableList<Post>) -> Unit = { }) {
        val realm = MyApplication.realmInstance()
        val post: RealmResults<Post> = realm.where(Post::class.java).findAllAsync()
        post.addChangeListener { _, changeSet ->
            changeSet.insertions // => [0] is added.
            updatedData(post)
        }

    }
}