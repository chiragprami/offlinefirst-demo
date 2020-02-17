package com.chirag.localstorage.post.storage

import com.chirag.localstorage.MyApplication
import com.chirag.localstorage.post.entity.Post
import io.realm.RealmList
import io.realm.RealmResults


class PostRealmOperation {

    fun storePostsListingData(post: ArrayList<Post>) {
        val realm = MyApplication.realmInstance()
        realm.executeTransaction {
            var realmList = RealmList<Post>()
            realmList.addAll(post)
            realm.insertOrUpdate(realmList)
        }
    }

    fun getStoredData(updatedData: (MutableList<Post>) -> Unit = { }) {
        val realm = MyApplication.realmInstance()
        val post: RealmResults<Post> = realm.where(Post::class.java).findAll()
        updatedData(post)
    }

    fun updateStorageDataListener(updatedData: (MutableList<Post>) -> Unit = { }) {
        val realm = MyApplication.realmInstance()
        val post: RealmResults<Post> = realm.where(Post::class.java).findAll()
        post.addChangeListener { _, changeSet ->
            changeSet.insertions // => [0] is added.
            updatedData(post)
        }
    }
}