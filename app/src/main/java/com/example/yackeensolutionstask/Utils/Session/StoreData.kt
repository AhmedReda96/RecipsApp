package com.example.yackeensolutionstask.Utils.Session

import android.content.Context
import android.content.SharedPreferences


class StoreData {
    private lateinit var context: Context

    private var index: Int = 0
    private lateinit var sharedPreferences: SharedPreferences


    constructor(context: Context) {
        this.context = context
        sharedPreferences = context.getSharedPreferences("index", Context.MODE_PRIVATE)
    }


    fun setIndex(index: Int) {
       this.index = index
        sharedPreferences.edit().putInt("index", index).commit()
    }


    fun getIndex(): Int? {
        index = sharedPreferences.getInt("index", index)
        return index
    }


    fun removeId() {
        index = 0
    }


}