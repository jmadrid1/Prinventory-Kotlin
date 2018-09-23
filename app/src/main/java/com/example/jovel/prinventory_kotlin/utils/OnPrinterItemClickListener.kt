package com.example.jovel.prinventory_kotlin.utils

import android.view.View
import com.example.jovel.prinventory_kotlin.models.Printer

interface OnPrinterItemClickListener {
    fun onItemClick(printer: Printer, view: View)

    fun onItemSettingClick(printer: Printer, view: View)
}