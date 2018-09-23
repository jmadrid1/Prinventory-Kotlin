package com.example.jovel.prinventory_kotlin.models

data class Toner(var id: Int,
                   var make: String,
                   var model: String,
                   var tModel: String,
                   var color: Int,
                   var black: Int,
                   var cyan: Int,
                   var yellow: Int,
                   var magenta: Int){

    constructor() : this(1,
            "",
            "",
            "",
            0,
            0,
            0,
            0,
            0
    )

}

