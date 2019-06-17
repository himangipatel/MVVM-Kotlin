package com.structure.kotlin.validator

/**
 * Created by Himangi Patelon 28/3/18.
 */


/**
 * An ENUM class which declares different types of validation error constants
 * , which needs to be passed in ValidationError model from the viewModel to the activity.
 */
enum class ValidationError {
    USERNAME,
    EMAIL,
    FIRST_NAME,
    LAST_NAME,
    PASSWORD,
    CONFIRM_PASSWORD,
    PHONE,
    DATA
}