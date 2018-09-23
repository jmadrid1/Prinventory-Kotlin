package com.example.jovel.prinventory_kotlin.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.jovel.prinventory_kotlin.models.Printer
import com.example.jovel.prinventory_kotlin.models.Toner
import com.example.jovel.prinventory_kotlin.models.Vendor

class DBHandler(context: Context) : SQLiteOpenHelper(context, DBHandler.DB_NAME, null, DBHandler.DB_VERSION) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_TABLE_PRINTER = "CREATE TABLE $TABLE_NAME_PRINTERS (" +
                                                                ID + " INTEGER PRIMARY KEY," +
                                                                PRINTER_MAKE + " TEXT," +
                                                                PRINTER_MODEL + " TEXT," +
                                                                PRINTER_TMODEL + " TEXT," +
                                                                PRINTER_SERIAL + " TEXT," +
                                                                PRINTER_STATUS + " INTEGER," +
                                                                PRINTER_COLOR + " INTEGER," +
                                                                PRINTER_OWNER + " TEXT," +
                                                                PRINTER_DEPT + " TEXT," +
                                                                PRINTER_LOCATION + " TEXT," +
                                                                PRINTER_FLOOR + " TEXT," +
                                                                PRINTER_IP + " TEXT);"

        val CREATE_TABLE_TONER = "CREATE TABLE $TABLE_NAME_TONERS (" +
                                                                ID + " INTEGER PRIMARY KEY," +
                                                                TONER_MAKE + " TEXT," +
                                                                TONER_MODEL + " TEXT," +
                                                                TONER_TMODEL + " TEXT," +
                                                                TONER_COLOR + " TEXT," +
                                                                TONER_BLACK + " INTEGER," +
                                                                TONER_CYAN + " INTEGER," +
                                                                TONER_YELLOW + " INTEGER," +
                                                                TONER_MAGENTA + " INTEGER);"

        val CREATE_TABLE_VENDOR = "CREATE TABLE $TABLE_NAME_VENDORS (" +
                                                                ID + " INTEGER PRIMARY KEY," +
                                                                VENDOR_NAME + " TEXT," +
                                                                VENDOR_PHONE + " TEXT," +
                                                                VENDOR_EMAIL + " TEXT," +
                                                                VENDOR_STREET + " TEXT," +
                                                                VENDOR_CITY + " TEXT," +
                                                                VENDOR_STATE + " TEXT," +
                                                                VENDOR_ZIPCODE + " TEXT);"

        if (p0 != null) {
            p0.execSQL(CREATE_TABLE_PRINTER)
            p0.execSQL(CREATE_TABLE_TONER)
            p0.execSQL(CREATE_TABLE_VENDOR)
        }
    }


    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val DROP_TABLE_PRINTER = "DROP TABLE IF EXISTS " + TABLE_NAME_PRINTERS
        val DROP_TABLE_TONER = "DROP TABLE IF EXISTS " + TABLE_NAME_TONERS
        val DROP_TABLE_VENDOR = "DROP TABLE IF EXISTS " + TABLE_NAME_VENDORS
        if (p0 != null) {
            p0.execSQL(DROP_TABLE_PRINTER)
            p0.execSQL(DROP_TABLE_TONER)
            p0.execSQL(DROP_TABLE_VENDOR)
        }
        onCreate(p0)
    }

    companion object {

        private val DB_VERSION = 1
        private val DB_NAME = "Prinventory"
        private val TABLE_NAME_PRINTERS = "Printers"
        private val TABLE_NAME_TONERS = "Toners"
        private val TABLE_NAME_VENDORS = "Vendors"


        private val ID = "ID"

        private val PRINTER_MAKE = "Make"
        private val PRINTER_MODEL = "Model"
        private val PRINTER_TMODEL = "TModel"
        private val PRINTER_SERIAL = "Serial"
        private val PRINTER_STATUS = "Status"
        private val PRINTER_COLOR = "Color"
        private val PRINTER_OWNER = "Owner"
        private val PRINTER_DEPT = "Department"
        private val PRINTER_LOCATION = "Location"
        private val PRINTER_FLOOR = "Floor"
        private val PRINTER_IP = "IP"

        private val TONER_MAKE = "Make"
        private val TONER_MODEL = "Model"
        private val TONER_TMODEL = "TModel"
        private val TONER_COLOR = "Color"
        private val TONER_BLACK = "Black"
        private val TONER_CYAN = "Cyan"
        private val TONER_YELLOW = "Yellow"
        private val TONER_MAGENTA = "Magenta"

        private val VENDOR_NAME = "Name"
        private val VENDOR_PHONE = "Phone"
        private val VENDOR_EMAIL = "Email"
        private val VENDOR_STREET = "Street"
        private val VENDOR_CITY = "City"
        private val VENDOR_STATE = "State"
        private val VENDOR_ZIPCODE = "Zipcode"
    }


    val printers: List<Printer>
        get() {
            val printerList = ArrayList<Printer>()
            val db = writableDatabase
            val selectQuery = "SELECT  * FROM $TABLE_NAME_PRINTERS"

            val cursor = db.rawQuery(selectQuery, null)
            if (cursor != null) {
                cursor.moveToFirst()
                while (cursor.moveToNext()) {
                    val printer = Printer()

                    printer.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                    printer.make = cursor.getString(cursor.getColumnIndex(PRINTER_MAKE))
                    printer.model = cursor.getString(cursor.getColumnIndex(PRINTER_MODEL))
                    printer.tModel = cursor.getString(cursor.getColumnIndex(PRINTER_TMODEL))
                    printer.serial = cursor.getString(cursor.getColumnIndex(PRINTER_SERIAL))
                    printer.status = cursor.getInt(cursor.getColumnIndex(PRINTER_STATUS))
                    printer.color = cursor.getInt(cursor.getColumnIndex(PRINTER_COLOR))
                    printer.ownership = cursor.getString(cursor.getColumnIndex(PRINTER_OWNER))
                    printer.department = cursor.getString(cursor.getColumnIndex(PRINTER_DEPT))
                    printer.location = cursor.getString(cursor.getColumnIndex(PRINTER_LOCATION))
                    printer.floor = cursor.getString(cursor.getColumnIndex(PRINTER_FLOOR))
                    printer.ip = cursor.getString(cursor.getColumnIndex(PRINTER_IP))

                    printerList.add(printer)
                }
            }
            cursor.close()

            return printerList
        }

    fun addPrinter(printer: Printer): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(PRINTER_MAKE, printer.make)
        values.put(PRINTER_MODEL, printer.model)
        values.put(PRINTER_TMODEL, printer.tModel)
        values.put(PRINTER_SERIAL, printer.serial)
        values.put(PRINTER_STATUS, printer.status)
        values.put(PRINTER_COLOR, printer.color)
        values.put(PRINTER_OWNER, printer.ownership)
        values.put(PRINTER_DEPT, printer.department)
        values.put(PRINTER_LOCATION, printer.location)
        values.put(PRINTER_FLOOR, printer.floor)
        values.put(PRINTER_IP, printer.ip)

        val _success = db.insert(TABLE_NAME_PRINTERS, null, values)
        db.close()

        return (Integer.parseInt("$_success") != -1)
    }

    fun updatePrinter(printer: Printer): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(PRINTER_MAKE, printer.make)
        values.put(PRINTER_MODEL, printer.model)
        values.put(PRINTER_TMODEL, printer.tModel)
        values.put(PRINTER_SERIAL, printer.serial)
        values.put(PRINTER_STATUS, printer.status)
        values.put(PRINTER_COLOR, printer.color)
        values.put(PRINTER_OWNER, printer.ownership)
        values.put(PRINTER_DEPT, printer.department)
        values.put(PRINTER_LOCATION, printer.location)
        values.put(PRINTER_FLOOR, printer.floor)
        values.put(PRINTER_IP, printer.ip)

        val _success = db.update(TABLE_NAME_PRINTERS, values, ID + "=?", arrayOf(printer.id.toString())).toLong()
        db.close()

        return Integer.parseInt("$_success") != -1
    }

    fun deletePrinter(_id: Int): Boolean {
        val db = this.writableDatabase
        val _success = db.delete(TABLE_NAME_PRINTERS, ID + "=?", arrayOf(_id.toString())).toLong()

        db.close()
        return Integer.parseInt("$_success") != -1
    }

    ///////TONER-----------------------------------------------------

    val toners: List<Toner>
        get() {
            val tonerList = ArrayList<Toner>()
            val db = writableDatabase
            val selectQuery = "SELECT  * FROM $TABLE_NAME_TONERS"

            val cursor = db.rawQuery(selectQuery, null)
            if (cursor != null) {
                cursor.moveToFirst()
                while (cursor.moveToNext()) {
                    val toner = Toner()

                    toner.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                    toner.make = cursor.getString(cursor.getColumnIndex(TONER_MAKE))
                    toner.model = cursor.getString(cursor.getColumnIndex(TONER_MODEL))
                    toner.tModel = cursor.getString(cursor.getColumnIndex(TONER_TMODEL))
                    toner.color = cursor.getInt(cursor.getColumnIndex(TONER_COLOR))
                    toner.black = cursor.getInt(cursor.getColumnIndex(TONER_BLACK))
                    toner.cyan = cursor.getInt(cursor.getColumnIndex(TONER_CYAN))
                    toner.yellow = cursor.getInt(cursor.getColumnIndex(TONER_YELLOW))
                    toner.magenta = cursor.getInt(cursor.getColumnIndex(TONER_MAGENTA))

                    tonerList.add(toner)
                }
            }
            cursor.close()
            return tonerList
        }

    fun addToner(toner: Toner): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(TONER_MAKE, toner.make)
        values.put(TONER_MODEL, toner.model)
        values.put(TONER_TMODEL, toner.tModel)
        values.put(TONER_COLOR, toner.color)
        values.put(TONER_BLACK, toner.black)
        values.put(TONER_CYAN, toner.cyan)
        values.put(TONER_YELLOW, toner.yellow)
        values.put(TONER_MAGENTA, toner.magenta)

        val _success = db.insert(TABLE_NAME_TONERS, null, values)
        db.close()

        return (Integer.parseInt("$_success") != -1)
    }

    fun updateToner(toner: Toner): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(TONER_MAKE, toner.make)
        values.put(TONER_MODEL, toner.model)
        values.put(TONER_TMODEL, toner.tModel)
        values.put(TONER_COLOR, toner.color)
        values.put(TONER_BLACK, toner.black)
        values.put(TONER_CYAN, toner.cyan)
        values.put(TONER_YELLOW, toner.yellow)
        values.put(TONER_MAGENTA, toner.magenta)

        val _success = db.update(TABLE_NAME_TONERS, values, ID + "=?", arrayOf(toner.id.toString())).toLong()
        db.close()

        return Integer.parseInt("$_success") != -1
    }

    fun deleteToner(_id: Int): Boolean {
        val db = this.writableDatabase
        val _success = db.delete(TABLE_NAME_TONERS, ID + "=?", arrayOf(_id.toString())).toLong()

        db.close()

        return Integer.parseInt("$_success") != -1
    }

    ///-----------VENDOR------------------------------------------------------

    val vendors: List<Vendor>
        get() {
            val vendorList = ArrayList<Vendor>()
            val db = writableDatabase
            val selectQuery = "SELECT  * FROM $TABLE_NAME_VENDORS"

            val cursor = db.rawQuery(selectQuery, null)
            if (cursor != null) {
                cursor.moveToFirst()
                while (cursor.moveToNext()) {
                    val vendor = Vendor()

                    vendor.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                    vendor.name = cursor.getString(cursor.getColumnIndex(VENDOR_NAME))
                    vendor.phone = cursor.getString(cursor.getColumnIndex(VENDOR_PHONE))
                    vendor.email = cursor.getString(cursor.getColumnIndex(VENDOR_EMAIL))
                    vendor.street = cursor.getString(cursor.getColumnIndex(VENDOR_STREET))
                    vendor.city = cursor.getString(cursor.getColumnIndex(VENDOR_CITY))
                    vendor.state = cursor.getString(cursor.getColumnIndex(VENDOR_STATE))
                    vendor.zipcode = cursor.getString(cursor.getColumnIndex(VENDOR_ZIPCODE))

                    vendorList.add(vendor)
                }
            }
            cursor.close()
            return vendorList
        }

    fun addVendor(vendor: Vendor): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(VENDOR_NAME, vendor.name)
        values.put(VENDOR_PHONE, vendor.phone)
        values.put(VENDOR_EMAIL, vendor.email)
        values.put(VENDOR_STREET, vendor.street)
        values.put(VENDOR_CITY, vendor.city)
        values.put(VENDOR_STATE, vendor.state)
        values.put(VENDOR_ZIPCODE, vendor.zipcode)

        val _success = db.insert(TABLE_NAME_VENDORS, null, values)
        db.close()

        return (Integer.parseInt("$_success") != -1)
    }

    fun updateVendor(vendor: Vendor): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(VENDOR_NAME, vendor.name)
        values.put(VENDOR_PHONE, vendor.phone)
        values.put(VENDOR_EMAIL, vendor.email)
        values.put(VENDOR_STREET, vendor.street)
        values.put(VENDOR_CITY, vendor.city)
        values.put(VENDOR_STATE, vendor.state)
        values.put(VENDOR_ZIPCODE, vendor.zipcode)

        val _success = db.update(TABLE_NAME_VENDORS, values, ID + "=?", arrayOf(vendor.id.toString())).toLong()
        db.close()

        return Integer.parseInt("$_success") != -1
    }

    fun deleteVendor(_id: Int): Boolean {
        val db = this.writableDatabase
        val _success = db.delete(TABLE_NAME_VENDORS, ID + "=?", arrayOf(_id.toString())).toLong()

        db.close()

        return Integer.parseInt("$_success") != -1
    }

}