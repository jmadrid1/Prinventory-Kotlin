package com.example.jovel.prinventory_kotlin.utils

import android.view.View
import com.example.jovel.prinventory_kotlin.models.Toner

interface OnTonerItemClickListener {
    fun onItemClick(toner: Toner, view: View)

    fun onItemSettingClick(toner: Toner, view: View)
}