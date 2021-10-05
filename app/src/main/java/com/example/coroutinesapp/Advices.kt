package com.example.coroutinesapp

import com.google.gson.annotations.SerializedName

class Advices {
    @SerializedName("slip")
    var slip : randomAdvice? = null

    class randomAdvice {
     @SerializedName("id")
     var id: Int? = 0
     @SerializedName("advice")
     var advice : String? = null
    }
}