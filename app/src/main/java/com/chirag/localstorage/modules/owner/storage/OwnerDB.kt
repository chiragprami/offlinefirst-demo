package com.chirag.localstorage.modules.owner.storage

import com.chirag.localstorage.MyApplication
import com.chirag.localstorage.entity.Items
import com.chirag.localstorage.entity.Owner
import io.realm.Case
import io.realm.RealmList
import io.realm.RealmResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OwnerDB {

      fun updateRepo(items: Items) {
        val realm = MyApplication.realmInstance()
        realm.executeTransactionAsync {
            realm.insertOrUpdate(items)
        }
        realm.close()
    }

      suspend fun getOwner(id:String,result: (Owner) -> Unit = { }) =
        withContext(Dispatchers.IO) {
            val realm = MyApplication.realmInstance()
            realm.executeTransactionAsync {
                val dbResult  = it.where(Owner::class.java).equalTo("id",id).findFirst()
                dbResult?.let {
                    result(dbResult)
                }
            }
            realm.close()
        }


}