package com.chirag.localstorage.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Post(
    @PrimaryKey var id: Int? = null,
    var userId: Int? = null,
    var body: String? = null,
    var title: String? = null
) : RealmObject(),Serializable