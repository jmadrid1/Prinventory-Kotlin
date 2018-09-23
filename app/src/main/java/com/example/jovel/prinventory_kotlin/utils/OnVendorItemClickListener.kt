package com.example.jovel.prinventory_kotlin.utils

import android.view.View
import com.example.jovel.prinventory_kotlin.models.Vendor

interface OnVendorItemClickListener {
    fun onItemClick(vendor: Vendor, view: View)

    fun onItemSettingClick(vendor: Vendor, view: View)
}