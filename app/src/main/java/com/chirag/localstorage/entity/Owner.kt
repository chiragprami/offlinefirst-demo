package com.chirag.localstorage.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Owner(
    @PrimaryKey var id: String? = null,
    var login: String? = null,
    var gists_url: String? = null,
    var repos_url: String? = null,
    var followers_url: String? = null,
    var type: String? = null,
    var url: String? = null,
    var subscriptions_url: String? = null,
    var received_events_url: String? = null,
    var avatar_url: String? = null,
    var events_url: String? = null,
    var html_url: String? = null,
    var site_admin: String? = null,
    var gravatar_id: String? = null,
    var node_id: String? = null,
    var name: String? = null,
    var organizations_url: String? = null
) : RealmObject(),Serializable