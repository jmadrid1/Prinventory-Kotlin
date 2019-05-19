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
import com.example.jovel.prinventory_kotlin.models.Toner

class TonerCreateFragment : Fragment() {

    private var mContext: Context? = null
    private var db: DBHandler? = null
    private var mToner: Toner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mToner = Toner()

        if(mContext != null){
            db = DBHandler(mContext!!)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_toner_create, container,false)

        val mMakeET = view.findViewById(R.id.fragment_toner_create_edittext_make) as EditText
        val mModelET = view.findViewById(R.id.fragment_toner_create_edittext_model) as EditText
        val mTModelET = view.findViewById(R.id.fragment_toner_create_edittext_tmodel) as EditText

        val mColorSpinner = view.findViewById(R.id.fragment_toner_create_spinner_color) as Spinner
        val mBlackET = view.findViewById(R.id.fragment_toner_create_edittext_black) as EditText
        val mCyanET = view.findViewById(R.id.fragment_toner_create_edittext_cyan) as EditText
        val mYellowET = view.findViewById(R.id.fragment_toner_create_edittext_yellow) as EditText
        val mMagentaET = view.findViewById(R.id.fragment_toner_create_edittext_magenta) as EditText

        val submitBtn = view.findViewById(R.id.fragment_toner_create_button_submit) as Button

        val colorAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(mContext, R.array.spinner_color_values, R.layout.support_simple_spinner_dropdown_item)
        colorAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        mColorSpinner.adapter = colorAdapter
        mColorSpinner.setSelection(0)

        mColorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                mToner!!.color = pos
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {
            }
        }

        mMakeET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.length > 0){
                    mToner!!.make = p0.toString()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {}
        })

        mModelET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.length > 0){
                    mToner!!.model = p0.toString()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {}
        })

        mTModelET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.length > 0){
                    mToner!!.tModel = p0.toString()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {}
        })

        mBlackET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.length > 0){
                    mToner!!.black = Integer.parseInt(p0.toString())
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {}
        })

        mCyanET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.length > 0){
                    mToner!!.cyan = Integer.parseInt(p0.toString())
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {}
        })

        mYellowET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.length > 0){
                    mToner!!.yellow = Integer.parseInt(p0.toString())
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {}
        })

        mMagentaET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.length > 0){
                    mToner!!.black = Integer.parseInt(p0.toString())
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {}
        })

        submitBtn.setOnClickListener {

            checkTextField(mMakeET)
            checkTextField(mModelET)
            checkTextField(mTModelET)
            checkTextField(mBlackET)
            checkTextField(mCyanET)
            checkTextField(mYellowET)
            checkTextField(mMagentaET)

            db?.addToner(mToner!!)

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