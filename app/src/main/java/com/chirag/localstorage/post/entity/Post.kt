package com.chirag.localstorage.post.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Post(
    @PrimaryKey var id: Int? = null,
    var userId: Int? = null,
    var body: String? = null,
    var title: String? = null
) : RealmObject()