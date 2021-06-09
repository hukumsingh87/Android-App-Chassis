package com.framework.application.modal.data

import java.io.Serializable

class AppData : Serializable {
    var isSucceed = true
    var data : Any? = null
    var errorCode : Int = 0
    set(value) {
        isSucceed = false
        field = value
    }
    var errorMessage : String = ""
        set(value) {
            isSucceed = false
            field = value
        }
}
