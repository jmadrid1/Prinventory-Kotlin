package com.example.jovel.prinventory_kotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.jovel.prinventory_kotlin.R
import com.example.jovel.prinventory_kotlin.models.Printer
import com.example.jovel.prinventory_kotlin.utils.OnPrinterItemClickListener

class PrinterHolder(view: View) : RecyclerView.ViewHolder(view){

    private var mIconIV: ImageView? = null
    private var mMakeTV: TextView? = null
    private var mModelTV: TextView? = null
    private var mDeptTV: TextView? = null
    private var mIPTV: TextView? = null
    private var mStatusIV: ImageView? = null
    private var mColorIV: ImageView? = null
    private var mSettingIV: ImageView? = null

    fun bindPrinter(printer: Printer, onPrinterItemClickListener: OnPrinterItemClickListener?){

        mIconIV = itemView.findViewById(R.id.row_printer_icon)
        mMakeTV = itemView.findViewById(R.id.row_printer_make)
        mModelTV = itemView.findViewById(R.id.row_printer_model)
        mDeptTV = itemView.findViewById(R.id.row_printer_dept)
        mIPTV = itemView.findViewById(R.id.row_printer_ip)
        mStatusIV = itemView.findViewById(R.id.row_printer_status)
        mColorIV = itemView.findViewById(R.id.row_printer_color)
        mSettingIV = itemView.findViewById(R.id.row_printer_setting)

        mIconIV!!.setImageResource(R.drawable.ic_printer)
        mMakeTV!!.text = "Make:    " + printer.make
        mModelTV!!.text = "Model:    " + printer.model
        mDeptTV!!.text = "Dept:    " + printer.department
        mIPTV!!.text = "IP:    " + printer.ip

        val status: Int = printer.status
        val color: Int = printer.color

        if (status == 0){
            mStatusIV!!.setImageResource(R.drawable.ic_status_inactive)
        }else{
            mStatusIV!!.setImageResource(R.drawable.ic_status_active)
        }

        if(color == 0){
            mColorIV!!.setImageResource(R.drawable.ic_toner_row_bw)
        }else{
            mColorIV!!.setImageResource(R.drawable.ic_toner_row_color)
        }

        itemView.setOnClickListener{
            onPrinterItemClickListener?.onItemClick(printer, itemView)
        }

        mSettingIV!!.setImageResource(R.drawable.ic_settings)
        mSettingIV!!.setOnClickListener {
            onPrinterItemClickListener?.onItemSettingClick(printer, itemView)
        }
    }
}