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
import com.example.jovel.prinventory_kotlin.models.Vendor

class VendorCreateFragment : Fragment() {

    private var mContext: Context? = null
    private var db: DBHandler? = null
    private var mVendor: Vendor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mVendor = Vendor()

        if(mContext != null){
            db = DBHandler(mContext!!)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_vendor_create, container,false)

        val mNameET = view.findViewById(R.id.fragment_vendor_create_edittext_name) as EditText
        val mPhoneET = view.findViewById(R.id.fragment_vendor_create_edittext_phone) as EditText
        val mEmailET = view.findViewById(R.id.fragment_vendor_create_edittext_email) as EditText
        val mStreetET = view.findViewById(R.id.fragment_vendor_create_edittext_street) as EditText
        val mCityET = view.findViewById(R.id.fragment_vendor_create_edittext_city) as EditText
        val mStatesSpinner = view.findViewById(R.id.fragment_vendor_create_spinner_states) as Spinner
        val mZipcodeET = view.findViewById(R.id.fragment_vendor_create_edittext_zipcode) as EditText

        val submitBtn = view.findViewById(R.id.fragment_vendor_create_button_submit) as Button

        val statesAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(mContext, R.array.states_array, R.layout.support_simple_spinner_dropdown_item)
        statesAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        mStatesSpinner.adapter = statesAdapter

        mStatesSpinner.setSelection(0)

        mStatesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                val state = mStatesSpinner.selectedItem.toString()
                mVendor!!.state = state
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {
            }
        }

        mNameET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.length > 0){
                    mVendor!!.name = p0.toString()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {}
        })

        mPhoneET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.length > 0){
                    mVendor!!.phone = p0.toString()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {}
        })

        mEmailET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.length > 0){
                    mVendor!!.email = p0.toString()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {}
        })

        mStreetET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.length > 0){
                    mVendor!!.street = p0.toString()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {}
        })

        mCityET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.length > 0){
                    mVendor!!.city = p0.toString()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {}
        })

        mZipcodeET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.length > 0){
                    mVendor!!.zipcode = p0.toString()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {}
        })

        submitBtn.setOnClickListener {

            checkTextField(mNameET)
            checkTextField(mPhoneET)
            checkTextField(mEmailET)
            checkTextField(mStreetET)
            checkTextField(mCityET)
            checkTextField(mZipcodeET)

            db?.addVendor(mVendor!!)

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