package com.chirag.localstorage

import android.app.Application
import com.facebook.stetho.Stetho
import io.realm.Realm
import io.realm.RealmConfiguration


class MyApplication : Application() {

    companion object {
        fun realmInstance(): Realm {
            return Realm.getDefaultInstance()
        }
    }

    override fun onCreate() {
        super.onCreate()
        setUpRealm()
        Stetho.initializeWithDefaults(this);
    }

    private fun setUpRealm() {
        Realm.init(this)
        val mRealmConfiguration = RealmConfiguration.Builder()
            .name("chirag.realm")
            .schemaVersion(1) // skip if you are not managing
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.getInstance(mRealmConfiguration)
        Realm.setDefaultConfiguration(mRealmConfiguration)
        realmInstance()
    }

}