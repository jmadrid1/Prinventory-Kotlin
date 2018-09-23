package com.example.jovel.prinventory_kotlin.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.jovel.prinventory_kotlin.R
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri


private const val ARG_ID = "id"
private const val ARG_NAME = "name"
private const val ARG_PHONE = "phone"
private const val ARG_EMAIL = "email"
private const val ARG_STREET = "street"
private const val ARG_CITY = "city"
private const val ARG_STATE = "state"
private const val ARG_ZIP = "zipcode"

class VendorDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_vendor_detail, container,false)

        val mIconIV = view.findViewById(R.id.fragment_vendor_detail_imageview_icon) as ImageView
        val mEmailIV = view.findViewById(R.id.fragment_vendor_detail_imageview_email) as ImageView
        val mCallIV = view.findViewById(R.id.fragment_vendor_detail_imageview_call) as ImageView

        val mNameTV = view.findViewById(R.id.fragment_vendor_detail_name_textview) as TextView
        val mPhoneTV = view.findViewById(R.id.fragment_vendor_detail_phone_textview) as TextView
        val mEmailTV = view.findViewById(R.id.fragment_vendor_detail_email_textview) as TextView
        val mStreetTV = view.findViewById(R.id.fragment_vendor_detail_street_textview) as TextView
        val mCityTV = view.findViewById(R.id.fragment_vendor_detail_city_textview) as TextView
        val mStateTV = view.findViewById(R.id.fragment_vendor_detail_state_textview) as TextView
        val mZipTV = view.findViewById(R.id.fragment_vendor_detail_zipcode_textview) as TextView

        val args = arguments

        val name = args?.getString(ARG_NAME)
        val phoneNumber = args?.getString(ARG_PHONE)
        val email = args?.getString(ARG_EMAIL)
        val street = args?.getString(ARG_STREET)
        val city = args?.getString(ARG_CITY)
        val state = args?.getString(ARG_STATE)
        val zipcode = args?.getString(ARG_ZIP)

        mIconIV.setImageResource(R.drawable.ic_vendor)

        mEmailIV.setImageResource(R.drawable.ic_message)
        mCallIV.setImageResource(R.drawable.ic_phone)

        mCallIV.setOnClickListener {
            val phoneIntent = Intent(Intent.ACTION_DIAL)
            phoneIntent.data = Uri.parse("tel:" + phoneNumber)
            try {
                startActivity(phoneIntent)
            } catch (e: Error) {
                val dialog = AlertDialog.Builder(activity)
                dialog
                        .setTitle(R.string.dialog_title_call_vendor)
                        .setMessage(R.string.dialog_message_call_vendor)
                        .setPositiveButton(R.string.btn_close, null)

                        .create()
                        .show()
            }

        }

        mEmailIV.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse("mailto:" + email)

            try {
                startActivity(emailIntent)
            } catch (e: ActivityNotFoundException) {
                val dialog = AlertDialog.Builder(activity)
                dialog
                        .setTitle(R.string.dialog_title_email_vendor)
                        .setMessage(R.string.dialog_message_email_vendor)
                        .setPositiveButton(R.string.btn_close, null)

                        .create()
                        .show()
            }

        }

        mNameTV.text = name
        mPhoneTV.text = phoneNumber
        mEmailTV.text = email
        mStreetTV.text = street
        mCityTV.text = city
        mStateTV.text = state
        mZipTV.text = zipcode

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(id: Int, name: String, phoneNumber: String, email: String, street: String, city: String, state: String, zipcode: String) =
                VendorDetailFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_ID, id)
                        putString(ARG_NAME, name)
                        putString(ARG_PHONE, phoneNumber)
                        putString(ARG_EMAIL, email)
                        putString(ARG_STREET, street)
                        putString(ARG_CITY, city)
                        putString(ARG_STATE, state)
                        putString(ARG_ZIP, zipcode)
                    }
                }
    }

}