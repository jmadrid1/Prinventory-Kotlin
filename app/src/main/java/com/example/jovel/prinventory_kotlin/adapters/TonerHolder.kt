package com.example.jovel.prinventory_kotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.jovel.prinventory_kotlin.R
import com.example.jovel.prinventory_kotlin.models.Toner
import com.example.jovel.prinventory_kotlin.utils.OnTonerItemClickListener

class TonerHolder(view: View) : RecyclerView.ViewHolder(view){

    private var mIconIV: ImageView? = null
    private var mMakeTV: TextView? = null
    private var mModelTV: TextView? = null
    private var mColorIV: ImageView? = null
    private var mSettingIV: ImageView? = null

    fun bindToner(toner: Toner, onTonerItemClickListener: OnTonerItemClickListener?){
        mIconIV = itemView.findViewById(R.id.row_printer_icon)
        mMakeTV = itemView.findViewById(R.id.row_toner_make)
        mModelTV = itemView.findViewById(R.id.row_toner_model)
        mColorIV = itemView.findViewById(R.id.row_toner_color)
        mSettingIV = itemView.findViewById(R.id.row_toner_setting)

        mIconIV!!.setImageResource(R.drawable.ic_toner)
        mMakeTV!!.text = "Make:    " + toner.make
        mModelTV!!.text = "Model:    " + toner.model

        val color: Int = toner.color

        if(color == 0){
            mColorIV!!.setImageResource(R.drawable.ic_toner_row_bw)
        }else{
            mColorIV!!.setImageResource(R.drawable.ic_toner_row_color)
        }

        itemView.setOnClickListener{
            onTonerItemClickListener?.onItemClick(toner, itemView)
        }

        mSettingIV!!.setImageResource(R.drawable.ic_settings)
        mSettingIV!!.setOnClickListener {
            onTonerItemClickListener?.onItemSettingClick(toner, itemView)
        }
    }
}