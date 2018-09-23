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

private const val ARG_ID = "id"
private const val ARG_NAME = "name"
private const val ARG_PHONE = "phone"
private const val ARG_EMAIL = "email"
private const val ARG_STREET = "street"
private const val ARG_CITY = "city"
private const val ARG_STATE = "state"
private const val ARG_ZIPCODE = "zipcode"

class VendorUpdateFragment : Fragment() {

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

        val view: View = inflater.inflate(R.layout.fragment_vendor_update, container,false)

//        val iconImageView = view.findViewById(R.id.fragment_vendor_update_edittext_make}) as ImageView

        val mNameET = view.findViewById(R.id.fragment_vendor_update_edittext_name) as EditText
        val mPhoneET = view.findViewById(R.id.fragment_vendor_update_edittext_phone) as EditText
        val mEmailET = view.findViewById(R.id.fragment_vendor_update_edittext_email) as EditText
        val mStreetET = view.findViewById(R.id.fragment_vendor_update_edittext_street) as EditText
        val mCityET = view.findViewById(R.id.fragment_vendor_update_edittext_city) as EditText
        val mStatesSpinner = view.findViewById(R.id.fragment_vendor_update_spinner_states) as Spinner
        val mZipcodeET = view.findViewById(R.id.fragment_vendor_update_edittext_zipcode) as EditText

        val submitBtn = view.findViewById(R.id.fragment_vendor_update_button_submit) as Button

        val args = arguments

        val id = args?.getInt(ARG_ID)
        val name = args?.getString(ARG_NAME)
        val phone = args?.getString(ARG_PHONE)
        val email = args?.getString(ARG_EMAIL)
        val street = args?.getString(ARG_STREET)
        val city = args?.getString(ARG_CITY)
        val state = args?.getString(ARG_STATE)
        val zipcode = args?.getString(ARG_ZIPCODE)

        mVendor?.id = id!!
        mVendor?.name = name!!
        mVendor?.phone = phone!!
        mVendor?.email = email!!
        mVendor?.street = street!!
        mVendor?.city = city!!
        mVendor?.state = state!!
        mVendor?.zipcode = zipcode!!

        mNameET.setText(name)
        mPhoneET.setText(phone)
        mEmailET.setText(email)
        mStreetET.setText(street)
        mCityET.setText(city)
        mZipcodeET.setText(zipcode)

        val statesAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(mContext, R.array.spinner_status_values, R.layout.support_simple_spinner_dropdown_item)
        statesAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        mStatesSpinner.adapter = statesAdapter

        val statePosition = statesAdapter.getPosition(state)
        mStatesSpinner.setSelection(statePosition)

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

            db?.updateVendor(mVendor!!)

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
        fun newInstance(id: Int, name: String, phone: String, email: String, street: String, city: String, state: String, zipcode: String) =
                VendorUpdateFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_ID, id)
                        putString(ARG_NAME, name)
                        putString(ARG_PHONE, phone)
                        putString(ARG_EMAIL, email)
                        putString(ARG_STREET, street)
                        putString(ARG_CITY, city)
                        putString(ARG_STATE, state)
                        putString(ARG_ZIPCODE, zipcode)
                    }
                }
    }

}