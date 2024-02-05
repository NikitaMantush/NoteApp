//package com.noteappbymantushnikita.mobile.util.validation
//
//import android.content.Context
//import com.google.android.material.textfield.TextInputLayout
//import com.noteappbymantushnikita.mobile.R
//
//fun validateNote(context: Context, editText: TextInputLayout?, text: String): Boolean {
//    return when {
//        text.trim().isEmpty() -> {
//            setError(editText, context.getString(R.string.field_empty))
//            false
//        }
//        else -> {
//            clearError(editText)
//            true
//        }
//    }
//}
//fun validateEmail(context: Context, editText: TextInputLayout?, text: String): Boolean {
//    val emailPattern = Regex("[a-zA-Z\\d._-]+@[a-z]+\\.+[a-z]+")
//    return when {
//        text.trim().isEmpty() -> {
//            setError(editText, context.getString(R.string.email_empty))
//            false
//        }
//        !text.trim().matches(emailPattern) -> {
//            setError(editText, context.getString(R.string.email_incorrect_format))
//            false
//        }
//        else -> {
//            clearError(editText)
//            true
//        }
//    }
//}
//
//fun validatePassword(context: Context, editText: TextInputLayout?, text: String): Boolean {
//    val passwordPattern =
//        Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*()_+{}\\[\\]:;<>,.?~\\\\/-])")
//    return when {
//        text.trim().isEmpty() -> {
//            setError(editText, context.getString(R.string.password_empty))
//            false
//        }
//        text.length < 6 || text.length > 50 -> {
//            setError(editText, context.getString(R.string.password_length))
//            false
//        }
//        !passwordPattern.containsMatchIn(text) -> {
//            setError(editText, context.getString(R.string.password_special_symbol))
//            false
//        }
//        else -> {
//            clearError(editText)
//            true
//        }
//    }
//}
//
//fun validateName(context: Context, editText: TextInputLayout?, text: String): Boolean {
//    return when {
//        text.trim().isEmpty() -> {
//            setError(editText, context.getString(R.string.field_empty))
//            false
//        }
//        text.length < 3 || text.length > 255 -> {
//            setError(editText, context.getString(R.string.field_length))
//            false
//        }
//        else -> {
//            clearError(editText)
//            true
//        }
//    }
//}
//
//private fun setError(editText: TextInputLayout?, error: String) {
//    editText?.error = error
//}
//
//private fun clearError(editText: TextInputLayout?) {
//    editText?.error = null
//}
