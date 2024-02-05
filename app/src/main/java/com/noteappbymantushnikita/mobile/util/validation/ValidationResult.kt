package com.noteappbymantushnikita.mobile.util.validation

sealed class ValidationResult {
    data object Valid : ValidationResult()
    class Invalid(val resString: Int): ValidationResult()
}