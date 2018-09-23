package com.example.jovel.prinventory_kotlin.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.jovel.prinventory_kotlin.R
import com.example.jovel.prinventory_kotlin.adapters.VendorAdapter
import com.example.jovel.prinventory_kotlin.database.DBHandler
import com.example.jovel.prinventory_kotlin.fragments.*
import com.example.jovel.prinventory_kotlin.models.Vendor
import com.example.jovel.prinventory_kotlin.utils.OnVendorItemClickListener

class VendorActivity : AppCompatActivity(), OnVendorItemClickListener {

    private var mDb: DBHandler? = null
    private var mRecyclerView: RecyclerView? = null
    private var mEmptyIV: ImageView? = null
    private var mEmptyTV: TextView? = null
    private var mVendorAdapter: VendorAdapter? = null
    private var mVendorList: List<Vendor> = ArrayList()

    private var mBottomNavigation: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendor)

        val actionBar = supportActionBar
        actionBar!!.setTitle(R.string.title_activity_vendor)

        mDb = DBHandler(this)

        mBottomNavigation = findViewById(R.id.navigation_vendor)
        mBottomNavigation!!.menu.findItem(R.id.navigation_menu_vendor).isChecked = true

        mRecyclerView = findViewById(R.id.recycler_vendor)
        mEmptyIV = findViewById(R.id.empty_img_printer)
        mEmptyIV?.setImageResource(R.drawable.ic_list_empty)

        mEmptyTV = findViewById(R.id.empty_text_vendor)
        mEmptyTV?.setText(R.string.list_vendor_empty)

        mVendorList = (mDb)!!.vendors
        mVendorAdapter = VendorAdapter(mVendorList,this)

        mRecyclerView?.adapter = mVendorAdapter
        mRecyclerView?.layoutManager = LinearLayoutManager(this)

        setupAdapter()
        showListStatus()

        mBottomNavigation!!.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_menu_printer -> {
                    switchToPrinterActivity()
                    true
                }
                R.id.navigation_menu_toner -> {
                    switchToTonerActivity()
                    true
                }
                R.id.navigation_menu_vendor -> {
                    switchToVendorActivity()
                    true
                }
                else -> {
                    true
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val fm: FragmentManager = supportFragmentManager
        val frag: Fragment = VendorCreateFragment()

        fm.beginTransaction()
                .add(R.id.frag_activity_container, frag)
                .commit()

        return super.onOptionsItemSelected(item)
    }

    //TODO: Fix removing of fragments when inside of fragment
    override fun onBackPressed() {
//        super.onBackPressed()

        fragmentManager.popBackStack()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if(hasFocus){
            setupAdapter()
            showListStatus()
        }
    }

    private fun setupAdapter(){
        val vendorList: List<Vendor> = mDb?.vendors!!

        if(mVendorAdapter == null){
            mVendorAdapter = VendorAdapter(vendorList, this)
            mRecyclerView?.adapter = mVendorAdapter
        }else{
            mVendorAdapter?.setList(vendorList)
            mVendorAdapter?.notifyDataSetChanged()
        }
    }

    private fun showListStatus(){
        if(mVendorAdapter?.itemCount == 0){
            mRecyclerView?.visibility = View.INVISIBLE
            mEmptyIV?.visibility = View.VISIBLE
            mEmptyTV?.visibility = View.VISIBLE
        }else{
            mRecyclerView?.visibility = View.VISIBLE
            mEmptyIV?.visibility = View.INVISIBLE
            mEmptyTV?.visibility = View.INVISIBLE
        }
    }

    override fun onItemClick(vendor: Vendor, view: View) {

        val id = vendor.id
        val name = vendor.name
        val phone = vendor.phone
        val email = vendor.email
        val street = vendor.street
        val city = vendor.city
        val state = vendor.state
        val zipcode = vendor.zipcode

        val fm: FragmentManager = supportFragmentManager
        val frag: Fragment = VendorDetailFragment.newInstance(id, name, phone, email, street, city, state, zipcode)

        fm.beginTransaction()
                .add(R.id.frag_activity_container, frag)
                .commit()
    }

    override fun onItemSettingClick(vendor: Vendor, view: View) {
        showSettingsPopup(vendor, view)
    }

    private fun showSettingsPopup(vendor: Vendor, view: View){
        val popUp: PopupMenu = PopupMenu(this, view)
        val menu: MenuInflater = popUp.menuInflater
        menu.inflate(R.menu.context_menu_vendor, popUp.menu)

        popUp.setOnMenuItemClickListener {

            when(it.itemId){
                R.id.menu_edit_vendor -> showUpdateFragment(vendor)
                R.id.menu_delete_vendor -> mDb!!.deleteVendor(vendor.id)
                else -> true
            }
        }
        popUp.show()
    }

    private fun showUpdateFragment(vendor: Vendor): Boolean {
        val id = vendor.id
        val name = vendor.name
        val phone = vendor.phone
        val email = vendor.email
        val street = vendor.street
        val city = vendor.city
        val state = vendor.state
        val zipcode = vendor.zipcode

        val fm: FragmentManager = supportFragmentManager
        val frag: Fragment = VendorUpdateFragment.newInstance(id, name, phone, email, street, city, state, zipcode)

        fm.beginTransaction()
                .add(R.id.frag_activity_container, frag)
                .commit()
        return true
    }

    fun switchToPrinterActivity(){
        val intent = Intent(this@VendorActivity, PrinterActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

    fun switchToTonerActivity(){
        val intent = Intent(this@VendorActivity, TonerActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

    fun switchToVendorActivity(){
        setupAdapter()
    }

}