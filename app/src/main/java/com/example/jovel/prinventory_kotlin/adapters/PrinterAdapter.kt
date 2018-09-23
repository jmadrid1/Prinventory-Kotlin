package com.example.jovel.prinventory_kotlin.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jovel.prinventory_kotlin.R
import com.example.jovel.prinventory_kotlin.models.Printer
import com.example.jovel.prinventory_kotlin.utils.OnPrinterItemClickListener

class PrinterAdapter(printers: List<Printer>, val context: Context) : RecyclerView.Adapter<PrinterHolder>(){

    private var mContext: Context? = null
    private var mPrinterList: List<Printer>? = null
    private var onPrinterItemClickListener: OnPrinterItemClickListener? = null

    init {
        mContext = context
        mPrinterList = printers
        onPrinterItemClickListener = context as OnPrinterItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrinterHolder {

        val view: View = LayoutInflater.from(context).inflate(R.layout.list_row_printer, parent, false)

        return PrinterHolder(view)
    }

    override fun getItemCount(): Int {
        return mPrinterList!!.size
    }

    override fun onBindViewHolder(holder: PrinterHolder, position: Int) {
        val printer: Printer = mPrinterList!!.get(position)

        holder.bindPrinter(printer, onPrinterItemClickListener)
    }

    fun setList(printers: List<Printer>){
        mPrinterList = printers
    }

}