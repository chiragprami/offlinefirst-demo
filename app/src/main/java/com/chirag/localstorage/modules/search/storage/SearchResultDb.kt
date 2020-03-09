package com.chirag.localstorage.modules.search.storage

import com.chirag.localstorage.MyApplication
import com.chirag.localstorage.entity.Items
import io.realm.Case
import io.realm.RealmList
import io.realm.RealmResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchResultDb {

      fun storePostsListingData(items: ArrayList<Items>) {
        val realm = MyApplication.realmInstance()
        realm.executeTransactionAsync {
            val realmList = RealmList<Items>()
            realmList.addAll(items)
            it.insertOrUpdate(realmList)
        }
        realm.close()
    }

      suspend fun getStoredData(q:String,updatedData: (ArrayList<Items>) -> Unit = { }) =
        withContext(Dispatchers.IO) {
            val realm = MyApplication.realmInstance()
            realm.executeTransactionAsync {
                val post: RealmResults<Items> = it.where(Items::class.java).contains("full_name",q.toLowerCase(),Case.INSENSITIVE).findAll()
                val list  = it.copyFromRealm(post) as ArrayList<Items>
                updatedData(list)
            }
            realm.close()
        }


}