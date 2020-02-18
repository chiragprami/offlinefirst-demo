package com.chirag.localstorage.post.storage

import com.chirag.localstorage.MyApplication
import com.chirag.localstorage.post.entity.Post
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults


class PostRealmOperation(var realm: Realm = MyApplication.realmInstance()) {

    fun storePostsListingData(post: ArrayList<Post>) {
        realm.executeTransaction {
            val realmList = RealmList<Post>()
            realmList.addAll(post)
            realm.insertOrUpdate(realmList)
        }
    }

    fun getStoredData(result: (MutableList<Post>) -> Unit = { }) {
        val post: RealmResults<Post> =
            realm.where(Post::class.java).findAll()
        result(post)
    }

    fun updateStorageDataListener(result: (MutableList<Post>) -> Unit = { }) {
        val post: RealmResults<Post> =
            realm.where(Post::class.java).findAll()
        post.addChangeListener { _, changeSet ->
            changeSet.insertions
            result(post)
        }
    }
}