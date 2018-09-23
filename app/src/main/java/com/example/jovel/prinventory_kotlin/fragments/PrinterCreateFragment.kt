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

class PrinterCreateFragment : Fragment() {

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

        val view: View = inflater.inflate(R.layout.fragment_printer_create, container,false)

        val iconImageView = view.findViewById(R.id.fragment_printer_create_imageview_icon) as ImageView
        val statusIV = view.findViewById(R.id.fragment_printer_create_imageview_status) as ImageView
        val colorIV = view.findViewById(R.id.fragment_printer_create_imageview_color) as ImageView

        val makeET = view.findViewById(R.id.fragment_printer_create_edittext_make) as EditText
        val modelET = view.findViewById(R.id.fragment_printer_create_edittext_model) as EditText
        val serialET = view.findViewById(R.id.fragment_printer_create_edittext_serial) as EditText
        val statusSpinner = view.findViewById(R.id.fragment_printer_create_spinner_status) as Spinner
        val colorSpinner = view.findViewById(R.id.fragment_printer_create_spinner_color) as Spinner
        val ownerET = view.findViewById(R.id.fragment_printer_create_edittext_owner) as EditText
        val deptET = view.findViewById(R.id.fragment_printer_create_edittext_dept) as EditText
        val locationET = view.findViewById(R.id.fragment_printer_create_edittext_location) as EditText
        val floorET = view.findViewById(R.id.fragment_printer_create_edittext_floor) as EditText
        val ipET = view.findViewById(R.id.fragment_printer_create_edittext_ip) as EditText

        val submitBtn = view.findViewById(R.id.fragment_printer_button_submit) as Button

        val statusAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(mContext, R.array.spinner_status_values, R.layout.support_simple_spinner_dropdown_item)
        statusAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        statusSpinner.adapter = statusAdapter

        statusSpinner.setSelection(0)
        statusSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                mPrinter!!.status = pos

                if (pos == 0){
                    statusIV.setImageResource(R.drawable.ic_status_inactive)
                }else{
                    statusIV.setImageResource(R.drawable.ic_status_active)
                }
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {
            }
        }

        val colorAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(mContext, R.array.spinner_color_values, R.layout.support_simple_spinner_dropdown_item)
        colorAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        colorSpinner.adapter = colorAdapter

        colorSpinner.setSelection(0)
        colorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                mPrinter!!.color = pos

                if(pos == 0){
                    colorIV.setImageResource(R.drawable.ic_toner_row_bw)
                }else{
                    colorIV.setImageResource(R.drawable.ic_toner_row_color)
                }
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {
            }
        }

        iconImageView.setImageResource(R.drawable.ic_printer)

        if (statusSpinner.selectedItem == 0){
            statusIV.setImageResource(R.drawable.ic_status_inactive)
        }else{
            statusIV.setImageResource(R.drawable.ic_status_active)
        }

        if(colorSpinner.selectedItem == 0){
            colorIV.setImageResource(R.drawable.ic_toner_row_bw)
        }else{
            colorIV.setImageResource(R.drawable.ic_toner_row_color)
        }

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

            db?.addPrinter(mPrinter!!)

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

}