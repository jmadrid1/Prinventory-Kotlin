package com.example.jovel.prinventory_kotlin.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.jovel.prinventory_kotlin.R
import com.example.jovel.prinventory_kotlin.database.DBHandler
import com.example.jovel.prinventory_kotlin.models.Printer

private const val ARG_ID = "id"
private const val ARG_MAKE = "make"
private const val ARG_MODEL = "model"
//private const val ARG_TMODEL = "tmodel"
private const val ARG_SERIAL = "serial"
private const val ARG_STATUS = "status"
private const val ARG_COLOR = "color"
private const val ARG_OWNER = "ownership"
private const val ARG_DEPT = "department"
private const val ARG_LOCATION = "location"
private const val ARG_FLOOR = "floor"
private const val ARG_IP = "ip"

class PrinterUpdateFragment : Fragment() {

    private var mContext: Context? = null
    private var db: DBHandler? = null
    private var mPrinter: Printer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPrinter = Printer()

        if(mContext != null){
            db = DBHandler(mContext!!)
        }

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_printer_update, container,false)

//        val iconImageView = view.findViewById(R.id.fragment_printer_update_edittext_make}) as ImageView
//        val statusIV = view.findViewById(R.id.fragment_printer_detail_imageview_status) as ImageView
//        val colorIV = view.findViewById(R.id.fragment_printer_detail_imageview_color) as ImageView

        val makeET = view.findViewById(R.id.fragment_printer_update_edittext_make) as EditText
        val modelET = view.findViewById(R.id.fragment_printer_update_edittext_model) as EditText
        val serialET = view.findViewById(R.id.fragment_printer_update_edittext_serial) as EditText
        val statusSpinner = view.findViewById(R.id.fragment_printer_update_spinner_status) as Spinner
        val colorSpinner = view.findViewById(R.id.fragment_printer_update_spinner_color) as Spinner
        val ownerET = view.findViewById(R.id.fragment_printer_update_edittext_owner) as EditText
        val deptET = view.findViewById(R.id.fragment_printer_update_edittext_dept) as EditText
        val locationET = view.findViewById(R.id.fragment_printer_update_edittext_location) as EditText
        val floorET = view.findViewById(R.id.fragment_printer_update_edittext_floor) as EditText
        val ipET = view.findViewById(R.id.fragment_printer_update_edittext_ip) as EditText

        val submitBtn = view.findViewById(R.id.fragment_printer_button_submit) as Button

        val args = arguments

        val id = args?.getInt(ARG_ID)
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

        mPrinter?.id = id!!
        mPrinter?.make = make!!
        mPrinter?.model = model!!
        mPrinter?.serial = serial!!
        mPrinter?.ownership = owner!!
        mPrinter?.department = dept!!
        mPrinter?.location = location!!
        mPrinter?.floor = floor!!
        mPrinter?.ip = ip!!

        makeET.setText(make)
        modelET.setText(model)
        serialET.setText(serial)
        ownerET.setText(owner)
        deptET.setText(dept)
        locationET.setText(location)
        floorET.setText(floor)
        ipET.setText(ip)

        val statusAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(mContext, R.array.spinner_status_values, R.layout.support_simple_spinner_dropdown_item)
        statusAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        statusSpinner.adapter = statusAdapter

        statusSpinner.setSelection(status!!)

        statusSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                mPrinter!!.status = pos
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {
            }
        }

        val colorAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(mContext, R.array.spinner_color_values, R.layout.support_simple_spinner_dropdown_item)
        colorAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        colorSpinner.adapter = colorAdapter

        colorSpinner.setSelection(color!!)

        colorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                mPrinter!!.color = pos
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {
            }
        }

//        iconImageView.setImageResource(R.drawable.ic_printer)
//
//        if (status == 0){
//            statusIV.setImageResource(R.drawable.ic_status_inactive)
//        }else{
//            statusIV.setImageResource(R.drawable.ic_status_active)
//        }
//
//        if(color == 0){
//            colorIV.setImageResource(R.drawable.ic_black)
//        }else{
//            colorIV.setImageResource(R.drawable.ic_color)
//        }

        makeET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.length > 0){
                    mPrinter!!.make = p0.toString()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {}
        })

        modelET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.length > 0){
                    mPrinter!!.model = p0.toString()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {}
        })

        serialET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.length > 0){
                    mPrinter!!.serial = p0.toString()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {}
        })

        ownerET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.length > 0){
                    mPrinter!!.ownership = p0.toString()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {}
        })

        deptET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.length > 0){
                    mPrinter!!.department = p0.toString()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {}
        })

        locationET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.length > 0){
                    mPrinter!!.location = p0.toString()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {}
        })

        floorET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.length > 0){
                    mPrinter!!.floor = p0.toString()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {}
        })

        ipET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.length > 0){
                    mPrinter!!.ip = p0.toString()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {}
        })

        submitBtn.setOnClickListener {

            checkTextField(makeET)
            checkTextField(modelET)
            checkTextField(serialET)
            checkTextField(ownerET)
            checkTextField(deptET)
            checkTextField(locationET)
            checkTextField(floorET)
            checkTextField(ipET)

            db?.updatePrinter(mPrinter!!)

            fragmentManager!!.beginTransaction().remove(this).commit()
        }

        return view
    }

    fun checkTextField(editText: EditText){
        val text: String = editText.text.toString()

        if(TextUtils.isEmpty(text)){
            editText.setText("Not Specified")
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(id: Int, make: String, model: String, serial: String, status: Int, color: Int, owner: String, dept: String, location: String, floor: String, ip: String) =
                PrinterUpdateFragment().apply {
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