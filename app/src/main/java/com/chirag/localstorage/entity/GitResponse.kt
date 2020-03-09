package com.chirag.localstorage.entity

import java.io.Serializable


class GitResponse : Serializable {
    var total_count: String? = null
    var incomplete_results: String? = null
    var items: ArrayList<Items>? = null
}