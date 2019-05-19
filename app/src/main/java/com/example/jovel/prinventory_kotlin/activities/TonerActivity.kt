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
import com.example.jovel.prinventory_kotlin.adapters.TonerAdapter
import com.example.jovel.prinventory_kotlin.database.DBHandler
import com.example.jovel.prinventory_kotlin.fragments.*
import com.example.jovel.prinventory_kotlin.models.Toner
import com.example.jovel.prinventory_kotlin.utils.OnTonerItemClickListener

class TonerActivity : AppCompatActivity(), OnTonerItemClickListener {

    private var mDb: DBHandler? = null
    private var mRecyclerView: RecyclerView? = null
    private var mEmptyIV: ImageView? = null
    private var mEmptyTV: TextView? = null
    private var mTonerAdapter: TonerAdapter? = null
    private var mTonerList: List<Toner> = ArrayList()

    private var mBottomNavigation: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toner)

        val actionBar = supportActionBar
        actionBar!!.setTitle(R.string.title_activity_toner)

        mDb = DBHandler(this)

        mRecyclerView = findViewById(R.id.recycler_toner)
        mEmptyIV = findViewById(R.id.empty_img_printer)
        mEmptyIV?.setImageResource(R.drawable.ic_list_empty)

        mEmptyTV = findViewById(R.id.empty_text_toner)
        mEmptyTV?.setText(R.string.list_toner_empty)

        mTonerList = (mDb)!!.toners
        mTonerAdapter = TonerAdapter(mTonerList,this)

        mRecyclerView!!.adapter = mTonerAdapter
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)

        mBottomNavigation = findViewById(R.id.navigation_toner)
        mBottomNavigation?.menu?.findItem(R.id.navigation_menu_toner)?.isChecked = true

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
        val frag: Fragment = TonerCreateFragment()

        fm.beginTransaction()
                .add(R.id.toner_activity_frag, frag)
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

    override fun onItemClick(toner: Toner, view: View) {
        val id = toner.id
        val make = toner.make
        val model = toner.model
        val tModel = toner.tModel
        val color = toner.color
        val black = toner.black
        val cyan = toner.cyan
        val yellow = toner.yellow
        val magenta = toner.magenta

        val fm: FragmentManager = supportFragmentManager
        val frag: Fragment = TonerDetailFragment.newInstance(id, make, model, tModel, color, black, cyan, yellow, magenta)

        fm.beginTransaction()
                .add(R.id.toner_activity_frag, frag)
                .commit()

    }

    override fun onItemSettingClick(toner: Toner, view: View) {
        showSettingsPopup(toner, view)
    }
    private fun showSettingsPopup(toner: Toner, view: View){
        val popUp = PopupMenu(this, view)
        val menu: MenuInflater = popUp.menuInflater
        menu.inflate(R.menu.context_menu_toner, popUp.menu)

        popUp.setOnMenuItemClickListener {

            when(it.itemId){
                R.id.menu_edit_toner -> showUpdateFragment(toner)
                R.id.menu_delete_toner -> mDb!!.deleteToner(toner.id)
                else -> true
            }
        }
        popUp.show()
    }

    private fun setupAdapter(){
        val tonerList: List<Toner> = mDb?.toners!!

        if(mTonerAdapter == null){
            mTonerAdapter = TonerAdapter(tonerList, this)
            mRecyclerView?.adapter = mTonerAdapter
        }else{
            mTonerAdapter?.setList(tonerList)
            mTonerAdapter?.notifyDataSetChanged()
        }
    }

    private fun showListStatus(){
        if(mTonerAdapter?.itemCount == 0){
            mRecyclerView?.visibility = View.INVISIBLE
            mEmptyIV?.visibility = View.VISIBLE
            mEmptyTV?.visibility = View.VISIBLE
        }else{
            mRecyclerView?.visibility = View.VISIBLE
            mEmptyIV?.visibility = View.INVISIBLE
            mEmptyTV?.visibility = View.INVISIBLE
        }
    }

    private fun showUpdateFragment(toner: Toner): Boolean {
        val id = toner.id
        val make = toner.make
        val model = toner.model
        val tModel = toner.tModel
        val color = toner.color
        val black = toner.black
        val cyan = toner.cyan
        val yellow = toner.yellow
        val magenta = toner.magenta

        val fm: FragmentManager = supportFragmentManager
        val frag: Fragment = TonerUpdateFragment.newInstance(id, make, model, tModel, color, black, cyan, yellow, magenta)

        fm.beginTransaction()
                .add(R.id.toner_activity_frag, frag)
                .commit()
        return true
    }

    private fun switchToPrinterActivity(){
        val intent = Intent(this@TonerActivity, PrinterActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

    private fun switchToTonerActivity(){
        setupAdapter()
    }

    private fun switchToVendorActivity(){
        val intent = Intent(this@TonerActivity, VendorActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

}