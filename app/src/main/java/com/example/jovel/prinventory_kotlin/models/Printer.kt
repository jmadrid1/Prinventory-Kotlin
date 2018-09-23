package com.example.jovel.prinventory_kotlin.models

data class Printer(var id: Int,
                   var make: String,
                   var model: String,
                   var tModel: String,
                   var serial: String,
                   var status: Int,
                   var color: Int,
                   var ownership: String,
                   var department: String,
                   var location: String,
                   var floor: String,
                   var ip: String){

    constructor() : this(1,
            "",
            "",
            "",
            "",
            0,
            0,
            "",
            "",
            "",
            "0",
            "1"
    )

}

