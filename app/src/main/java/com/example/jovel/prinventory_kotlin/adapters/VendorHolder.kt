package com.example.jovel.prinventory_kotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.jovel.prinventory_kotlin.R
import com.example.jovel.prinventory_kotlin.models.Vendor
import com.example.jovel.prinventory_kotlin.utils.OnVendorItemClickListener

class VendorHolder(view: View) : RecyclerView.ViewHolder(view){

    private var mIconIV: ImageView? = null
    private var mNameTV: TextView? = null
    private var mPhoneTV: TextView? = null
    private var mEmailTV: TextView? = null

    private var mSettingIV: ImageView = view.findViewById(R.id.row_vendor_setting)

    fun bindVendor(vendor: Vendor, onVendorItemClickListener: OnVendorItemClickListener?) {
        mIconIV = itemView.findViewById(R.id.row_vendor_icon)
        mNameTV = itemView.findViewById(R.id.row_vendor_name)
        mPhoneTV = itemView.findViewById(R.id.row_vendor_phone)
        mEmailTV = itemView.findViewById(R.id.row_vendor_email)

        mIconIV!!.setImageResource(R.drawable.ic_vendor)

        mNameTV!!.text = "Name:    " + vendor.name
        mPhoneTV!!.text = "Phone:    " + vendor.phone
        mEmailTV!!.text = "Email:    " + vendor.email

        itemView.setOnClickListener {
            onVendorItemClickListener?.onItemClick(vendor, itemView)
        }

        mSettingIV!!.setImageResource(R.drawable.ic_settings)
        mSettingIV!!.setOnClickListener {
            onVendorItemClickListener?.onItemSettingClick(vendor, itemView)
        }
    }
}