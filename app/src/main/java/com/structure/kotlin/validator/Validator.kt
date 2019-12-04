package com.structure.kotlin.validator

import android.content.Context
import android.widget.EditText
import com.structure.kotlin.R
import java.util.regex.Pattern

/**
 * Created by Himangi Patelon 30/3/18.
 */

/**
 * A utility class which contains methods with all the validation logic of whole app.
 */
object Validator {

    private const val EMAIL_PATTERN: String = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" /*+
            "\\." +
            "[a-zA-Z]{2,8}" +
            ")+"*/

    private const val PASSWORD_PATTERN: String = "^.*(?=.{6,20})(?=.*\\d)(?=.*[a-zA-Z])(^[a-zA-Z0-9._@!&$*%+-:/><#]+$)"


    fun validateEmail(email: String?): ValidationErrorModel? {
        return if (isBlank(email))
            ValidationErrorModel(R.string.blank_email, ValidationError.EMAIL)
        else if (!Pattern.compile(EMAIL_PATTERN).matcher(email).matches())
            ValidationErrorModel(R.string.invalid_email, ValidationError.EMAIL)
        else
            null
    }

    fun validatePassword(password: String?): ValidationErrorModel? {
        return when {
            isBlank(password) -> ValidationErrorModel(R.string.blank_password, ValidationError.PASSWORD)
            password!!.length < 8 -> ValidationErrorModel(R.string.invalid_password, ValidationError.PASSWORD)
        /* else if (!Pattern.compile(PASSWORD_PATTERN).matcher(password).matches())
             ValidationErrorModel(R.string.invalid_password, ValidationError.PASSWORD)*/
            else -> null
        }
    }

    private fun isBlank(text: String?): Boolean {
        return text == null || text.trim().isEmpty()
    }

    fun validateTelephone(phone: String): ValidationErrorModel? {
        return when {
            isBlank(phone) -> ValidationErrorModel(R.string.blank_phone, ValidationError.PHONE)
            phone.length !in 6..15 -> ValidationErrorModel(R.string.invalid_phone, ValidationError.PHONE)
            else -> null
        }
    }
}