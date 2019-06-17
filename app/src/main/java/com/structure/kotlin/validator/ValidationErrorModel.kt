package com.structure.kotlin.validator

/**
 * Created by Himangi Patelon 28/3/18.
 */


/**
 * A data class which needs to be passed with error msg and error constant
 * from the viewModel to the activity, whenever an error occurs.
 */
data class ValidationErrorModel(val msg: Int, val error: ValidationError)