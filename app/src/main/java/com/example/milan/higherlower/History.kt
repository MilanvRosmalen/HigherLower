package com.example.milan.higherlower

import android.widget.BaseAdapter

class History(private var mHistoryText: String) {

    override fun toString(): String {
        return mHistoryText
    }

    fun getmHistoryText(): String {
        return mHistoryText
    }

    fun setmHistoryText(mHistoryText: String) {
        this.mHistoryText = mHistoryText
    }
}
