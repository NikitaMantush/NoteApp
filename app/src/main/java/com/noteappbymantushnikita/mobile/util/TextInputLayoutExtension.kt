package com.noteappbymantushnikita.mobile.util

import com.google.android.material.textfield.TextInputLayout
import com.noteappbymantushnikita.mobile.util.validation.ValidationResult

fun TextInputLayout.setValidation(result: ValidationResult){
    if (result is ValidationResult.Invalid){
        error = context.getString(result.resString)
    }
    else error = null
}