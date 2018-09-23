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

private const val ARG_ID = "id"
private const val ARG_MAKE = "make"
private const val ARG_MODEL = "model"
private const val ARG_TMODEL = "tmodel"
private const val ARG_COLOR = "color"
private const val ARG_BLACK = "black"
private const val ARG_CYAN = "cyan"
private const val ARG_YELLOW = "yellow"
private const val ARG_MAGENTA = "magenta"

class TonerUpdateFragment : Fragment() {

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

        val view: View = inflater.inflate(R.layout.fragment_toner_update, container,false)

//        val iconImageView = view.findViewById(R.id.fragment_toner_update_edittext_make}) as ImageView

//        val colorIV = view.findViewById(R.id.fragment_toner_detail_imageview_color) as ImageView

        val mMakeET = view.findViewById(R.id.fragment_toner_update_edittext_make) as EditText
        val mModelET = view.findViewById(R.id.fragment_toner_update_edittext_model) as EditText
        val mTModelET = view.findViewById(R.id.fragment_toner_update_edittext_tmodel) as EditText

        val mColorSpinner = view.findViewById(R.id.fragment_toner_update_spinner_color) as Spinner
        val mBlackET = view.findViewById(R.id.fragment_toner_update_edittext_black) as EditText
        val mCyanET = view.findViewById(R.id.fragment_toner_update_edittext_cyan) as EditText
        val mYellowET = view.findViewById(R.id.fragment_toner_update_edittext_yellow) as EditText
        val mMagentaET = view.findViewById(R.id.fragment_toner_update_edittext_magenta) as EditText

        val submitBtn = view.findViewById(R.id.fragment_toner_button_submit) as Button

        val args = arguments

        val id = args?.getInt(ARG_ID)
        val make = args?.getString(ARG_MAKE)
        val model = args?.getString(ARG_MODEL)
        val tModel = args?.getString(ARG_TMODEL)
        val color = args?.getInt(ARG_COLOR)
        val black = args?.getInt(ARG_BLACK)
        val cyan = args?.getInt(ARG_CYAN)
        val yellow = args?.getInt(ARG_YELLOW)
        val magenta = args?.getInt(ARG_MAGENTA)

        mToner?.id = id!!
        mToner?.make = make!!
        mToner?.model = model!!
        mToner?.tModel = tModel!!
        mToner?.color = color!!
        mToner?.black = black!!
        mToner?.cyan = cyan!!
        mToner?.yellow = yellow!!
        mToner?.magenta = magenta!!

        mMakeET.setText(make)
        mModelET.setText(model)
        mTModelET.setText(tModel)
        mBlackET.setText(black)
        mCyanET.setText(cyan)
        mYellowET.setText(yellow)
        mMagentaET.setText(magenta)

        val colorAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(mContext, R.array.spinner_color_values, R.layout.support_simple_spinner_dropdown_item)
        colorAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        mColorSpinner.adapter = colorAdapter

        mColorSpinner.setSelection(color!!)

        mColorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                mToner!!.color = pos
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {
            }
        }

//        iconImageView.setImageResource(R.drawable.ic_toner)
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

            db?.updateToner(mToner!!)

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
        fun newInstance(id: Int, make: String, model: String, tModel: String, color: Int, black: Int, cyan: Int, yellow: Int, magenta: Int) =
                TonerUpdateFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_ID, id)
                        putString(ARG_MAKE, make)
                        putString(ARG_MODEL, model)
                        putString(ARG_TMODEL, tModel)
                        putInt(ARG_COLOR, color)
                        putInt(ARG_BLACK, black)
                        putInt(ARG_CYAN, cyan)
                        putInt(ARG_YELLOW, yellow)
                        putInt(ARG_MAGENTA, magenta)
                    }
                }
    }
}