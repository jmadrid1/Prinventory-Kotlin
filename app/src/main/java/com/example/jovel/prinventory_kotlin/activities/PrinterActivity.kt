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
import android.support.v7.widget.RecyclerView.LayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.jovel.prinventory_kotlin.R
import com.example.jovel.prinventory_kotlin.adapters.PrinterAdapter
import com.example.jovel.prinventory_kotlin.database.DBHandler
import com.example.jovel.prinventory_kotlin.fragments.PrinterCreateFragment
import com.example.jovel.prinventory_kotlin.fragments.PrinterDetailFragment
import com.example.jovel.prinventory_kotlin.fragments.PrinterUpdateFragment
import com.example.jovel.prinventory_kotlin.models.Printer
import com.example.jovel.prinventory_kotlin.utils.OnPrinterItemClickListener

class PrinterActivity : AppCompatActivity(), OnPrinterItemClickListener {

    private var mDb: DBHandler? = null
    private var mRecyclerView: RecyclerView? = null
    private var mEmptyIV: ImageView? = null
    private var mEmptyTV: TextView? = null
    private var printerAdapter: PrinterAdapter? = null
    private var printerList: List<Printer> = ArrayList()

    private var mBottomNavigation: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_printer)

        val actionBar = supportActionBar
        actionBar!!.setTitle(R.string.title_activity_printer)

        mDb = DBHandler(this)

        mBottomNavigation = findViewById(R.id.navigation_printer)
        mBottomNavigation!!.menu.findItem(R.id.navigation_menu_printer).isChecked = true

        mRecyclerView = findViewById(R.id.recycler_printer)
        mEmptyIV = findViewById(R.id.empty_img_printer)
        mEmptyIV?.setImageResource(R.drawable.ic_list_empty)

        mEmptyTV = findViewById(R.id.empty_text_printer)
        mEmptyTV?.setText(R.string.list_printer_empty)

        printerList = (mDb)!!.printers
        printerAdapter = PrinterAdapter(printerList,this)

        mRecyclerView?.adapter = printerAdapter
//        mRecyclerView?.layoutManager = LayoutManager()

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
        val frag: Fragment = PrinterCreateFragment()

        fm.beginTransaction()
                .add(R.id.printer_activity_frag, frag)
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
        val printerList: List<Printer> = mDb?.printers!!

        if(printerAdapter == null){
            printerAdapter = PrinterAdapter(printerList, this)
            mRecyclerView?.adapter = printerAdapter
        }else{
            printerAdapter?.setList(printerList)
            printerAdapter?.notifyDataSetChanged()
        }
    }

    private fun showListStatus(){
        if(printerAdapter?.itemCount == 0){
            mRecyclerView?.visibility = View.INVISIBLE
            mEmptyIV?.visibility = View.VISIBLE
            mEmptyTV?.visibility = View.VISIBLE
        }else{
            mRecyclerView?.visibility = View.VISIBLE
            mEmptyIV?.visibility = View.INVISIBLE
            mEmptyTV?.visibility = View.INVISIBLE
        }

    }

    override fun onItemClick(printer: Printer, view: View) {

        val id = printer.id
        val make = printer.make
        val model = printer.model
        val serial = printer.serial
        val status = printer.status
        val color = printer.color
        val owner = printer.ownership
        val dept = printer.department
        val location = printer.location
        val floor = printer.floor
        val ip = printer.ip

        val fm: FragmentManager = supportFragmentManager
        val frag: Fragment = PrinterDetailFragment.newInstance(id, make, model, serial, status, color, owner, dept, location, floor, ip)

        fm.beginTransaction()
                .add(R.id.printer_activity_frag, frag)
                .commit()
    }

    override fun onItemSettingClick(printer: Printer, view: View) {
        showSettingsPopup(printer, view)
    }


    private fun showSettingsPopup(printer: Printer, view: View){
        val popUp: PopupMenu = PopupMenu(this, view)
        val menu: MenuInflater = popUp.menuInflater
        menu.inflate(R.menu.context_menu_printer, popUp.menu)

        popUp.setOnMenuItemClickListener {

            when(it.itemId){
                R.id.menu_edit_printer -> showUpdateFragment(printer)
                R.id.menu_delete_printer -> mDb!!.deletePrinter(printer.id)
                else -> true
            }
        }
        popUp.show()
    }

    private fun showUpdateFragment(printer: Printer): Boolean {
        val id = printer.id
        val make = printer.make
        val model = printer.model
        val serial = printer.serial
        val status = printer.status
        val color = printer.color
        val owner = printer.ownership
        val dept = printer.department
        val location = printer.location
        val floor = printer.floor
        val ip = printer.ip

        val fm: FragmentManager = supportFragmentManager
        val frag: Fragment = PrinterUpdateFragment.newInstance(id, make, model, serial, status, color, owner, dept, location, floor, ip)

        fm.beginTransaction()
                .add(R.id.printer_activity_frag, frag)
                .commit()
        return true
    }

    private fun switchToPrinterActivity(){
        setupAdapter()
    }

    private fun switchToTonerActivity(){
        val intent = Intent(this@PrinterActivity, TonerActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

    private fun switchToVendorActivity(){
        val intent = Intent(this@PrinterActivity, VendorActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

}