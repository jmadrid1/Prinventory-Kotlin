package com.example.jovel.prinventory_kotlin.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jovel.prinventory_kotlin.R
import com.example.jovel.prinventory_kotlin.models.Toner
import com.example.jovel.prinventory_kotlin.utils.OnTonerItemClickListener

class TonerAdapter (toners: List<Toner>, val context: Context) : RecyclerView.Adapter<TonerHolder>(){

    private var mContext: Context? = null
    private var mTonerList: List<Toner>? = null
    private var onTonerItemClickListener: OnTonerItemClickListener? = null

    init {
        mContext = context
        mTonerList = toners
        onTonerItemClickListener = context as OnTonerItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TonerHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_row_toner, parent, false)

        return TonerHolder(view)
    }

    override fun getItemCount(): Int {
        return mTonerList!!.size
    }

    override fun onBindViewHolder(holder: TonerHolder, position: Int) {
        val toner: Toner = mTonerList!!.get(position)

        holder.bindToner(toner, onTonerItemClickListener)
    }

    fun setList(toners: List<Toner>) {
        mTonerList = toners
    }

}
