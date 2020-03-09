package com.chirag.localstorage.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable


open class Items(
    @PrimaryKey var id: String? = null,
    var node_id: String? = null,
    var full_name: String? = null,
    var html_url: String? = null,
    var collaborators_url: String? = null,
    var name: String? = null,
    var contributors_url: String? = null,
    var description: String? = null,
    var watchers: Long = 0,
    var keys_url: String? = null,
    var deployments_url: String? = null,
    var isHas_projects: Boolean = false,
    var isHas_pages: Boolean = false,
    var owner: Owner? = null
): RealmObject(), Serializable