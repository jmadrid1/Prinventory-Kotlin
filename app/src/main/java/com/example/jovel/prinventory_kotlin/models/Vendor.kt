package com.example.jovel.prinventory_kotlin.models

data class Vendor(var id: Int,
                  var name: String,
                  var phone: String,
                  var email: String,
                  var street: String,
                  var city: String,
                  var state: String,
                  var zipcode: String){

    constructor() : this(1,
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    )

}