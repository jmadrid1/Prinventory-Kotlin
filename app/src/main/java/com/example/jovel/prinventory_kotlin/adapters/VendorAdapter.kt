package com.example.jovel.prinventory_kotlin.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jovel.prinventory_kotlin.R
import com.example.jovel.prinventory_kotlin.models.Vendor
import com.example.jovel.prinventory_kotlin.utils.OnVendorItemClickListener

class VendorAdapter(vendors: List<Vendor>, val context: Context) : RecyclerView.Adapter<VendorHolder>(){

    private var mContext: Context? = null
    private var mVendorList: List<Vendor>? = null
    private var onVendorItemClickListener: OnVendorItemClickListener? = null

    init {
        mContext = context
        mVendorList = vendors
        onVendorItemClickListener = context as OnVendorItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VendorHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_row_vendor, parent, false)

        return VendorHolder(view)
    }

    override fun getItemCount(): Int {
        return mVendorList!!.size
    }

    override fun onBindViewHolder(holder: VendorHolder, position: Int) {
        val vendor: Vendor = mVendorList!!.get(position)

        holder.bindVendor(vendor, onVendorItemClickListener)
    }

    fun setList(vendors: List<Vendor>){
        mVendorList = vendors
    }

}