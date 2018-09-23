package com.example.jovel.prinventory_kotlin.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.jovel.prinventory_kotlin.R

private const val ARG_ID = "id"
private const val ARG_MAKE = "make"
private const val ARG_MODEL = "model"
private const val ARG_SERIAL = "serial"
private const val ARG_STATUS = "status"
private const val ARG_COLOR = "color"
private const val ARG_OWNER = "owner"
private const val ARG_DEPT = "dept"
private const val ARG_LOCATION = "location"
private const val ARG_FLOOR = "floor"
private const val ARG_IP = "ip"

class PrinterDetailFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_printer_detail, container,false)

        val iconImageView = view.findViewById(R.id.fragment_printer_detail_imageview_icon) as ImageView
        val statusIV = view.findViewById(R.id.fragment_printer_detail_imageview_status) as ImageView
        val colorIV = view.findViewById(R.id.fragment_printer_detail_imageview_color) as ImageView

        val makeTextView = view.findViewById(R.id.fragment_printer_detail_make_textview) as TextView
        val modelTextView = view.findViewById(R.id.fragment_printer_detail_model_textview) as TextView
        val serialTextView = view.findViewById(R.id.fragment_printer_detail_serial_textview) as TextView
        val ownerTextView = view.findViewById(R.id.fragment_printer_detail_owner_textview) as TextView
        val deptTextView = view.findViewById(R.id.fragment_printer_detail_dept_textview) as TextView
        val locationTextView = view.findViewById(R.id.fragment_printer_detail_location_textview) as TextView
        val floorTextView = view.findViewById(R.id.fragment_printer_detail_floor_textview) as TextView
        val ipTextView = view.findViewById(R.id.fragment_printer_detail_ip_textview) as TextView

        val args = arguments

        val make = args?.getString(ARG_MAKE)
        val model = args?.getString(ARG_MODEL)
        val serial = args?.getString(ARG_SERIAL)
        val status = args?.getInt(ARG_STATUS)
        val color = args?.getInt(ARG_COLOR)
        val owner = args?.getString(ARG_OWNER)
        val dept = args?.getString(ARG_DEPT)
        val location = args?.getString(ARG_LOCATION)
        val floor = args?.getString(ARG_FLOOR)
        val ip = args?.getString(ARG_IP)

        iconImageView.setImageResource(R.drawable.ic_printer)

        if (status == 0){
            statusIV.setImageResource(R.drawable.ic_status_inactive)
        }else{
            statusIV.setImageResource(R.drawable.ic_status_active)
        }

        if(color == 0){
            colorIV.setImageResource(R.drawable.ic_toner_row_bw)
        }else{
            colorIV.setImageResource(R.drawable.ic_toner_row_color)
        }

        makeTextView.text = make
        modelTextView.text = model
        serialTextView.text = serial
        ownerTextView.text = owner
        deptTextView.text = dept
        locationTextView.text = location
        floorTextView.text = floor
        ipTextView.text = ip

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(id: Int, make: String, model: String, serial: String, status: Int, color: Int, owner: String, dept: String, location: String, floor: String, ip: String) =
                PrinterDetailFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_ID, id)
                        putString(ARG_MAKE, make)
                        putString(ARG_MODEL, model)
                        putString(ARG_SERIAL, serial)
                        putInt(ARG_STATUS, status)
                        putInt(ARG_COLOR, color)
                        putString(ARG_OWNER, owner)
                        putString(ARG_DEPT, dept)
                        putString(ARG_LOCATION, location)
                        putString(ARG_FLOOR, floor)
                        putString(ARG_IP, ip)
                    }
                }
    }
}