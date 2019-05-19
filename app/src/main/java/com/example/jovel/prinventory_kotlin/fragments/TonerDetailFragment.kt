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
private const val ARG_TMODEL = "tmodel"
private const val ARG_COLOR = "color"
private const val ARG_BLACK = "black"
private const val ARG_CYAN = "cyan"
private const val ARG_YELLOW = "yellow"
private const val ARG_MAGENTA = "magenta"

class TonerDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_toner_detail, container,false)

        val iconImageView = view.findViewById(R.id.fragment_toner_detail_imageview_icon) as ImageView

        val mMakeTV = view.findViewById(R.id.fragment_toner_detail_make_textview) as TextView
        val mTModelTV = view.findViewById(R.id.fragment_toner_detail_tmodel_textview) as TextView
        val mColorTV = view.findViewById(R.id.fragment_toner_detail_color_textview) as TextView
        val mBlackTV = view.findViewById(R.id.fragment_toner_detail_black_textview) as TextView
        val mCyanTV = view.findViewById(R.id.fragment_toner_detail_cyan_textview) as TextView
        val mYellowTV = view.findViewById(R.id.fragment_toner_detail_yellow_textview) as TextView
        val mMagentaTV = view.findViewById(R.id.fragment_toner_detail_magenta_textview) as TextView

        val args = arguments

        val make = args?.getString(ARG_MAKE)
        val model = args?.getString(ARG_MODEL)
        val tModel = args?.getString(ARG_TMODEL)
        val color = args?.getInt(ARG_COLOR)
        val black = args?.getInt(ARG_BLACK).toString()
        val cyan = args?.getInt(ARG_CYAN).toString()
        val yellow = args?.getInt(ARG_YELLOW).toString()
        val magenta = args?.getInt(ARG_MAGENTA).toString()

        iconImageView.setImageResource(R.drawable.ic_toner)

        if(color == 0){
            iconImageView.setImageResource(R.drawable.ic_toner_row_bw)
        }else{
            iconImageView.setImageResource(R.drawable.ic_toner_row_color)
        }

        mMakeTV.text = make + " " + model
        mTModelTV.text = tModel
        mColorTV.text = color.toString()
        mBlackTV.text = black
        mCyanTV.text = cyan
        mYellowTV.text = yellow
        mMagentaTV.text = magenta

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(id: Int, make: String, model: String, tModel: String, color: Int, black: Int, cyan: Int, yellow: Int, magenta: Int) =
                TonerDetailFragment().apply {
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