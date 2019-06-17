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
//        else if (!Pattern.compile(EMAIL_PATTERN).matcher(email).matches())
//            ValidationErrorModel(R.string.invalid_email, ValidationError.EMAIL)
        else
            null
    }

    fun validateFirstName(fname: String?): ValidationErrorModel? {
        return if (isBlank(fname))
            ValidationErrorModel(R.string.blank_first_name, ValidationError.FIRST_NAME)
        else
            null
    }

    fun validateLastName(lname: String?): ValidationErrorModel? {
        return if (isBlank(lname))
            ValidationErrorModel(R.string.blank_last_name, ValidationError.LAST_NAME)
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

    fun validateConfirmPassword(password: String?, confirmPassword: String?): ValidationErrorModel? {
        return when {
            isBlank(confirmPassword) -> ValidationErrorModel(R.string.blank_confirm_password, ValidationError.CONFIRM_PASSWORD)
            password != confirmPassword -> ValidationErrorModel(R.string.invalid_confirm_password, ValidationError.CONFIRM_PASSWORD)
            else -> null
        }
    }

    private fun isBlank(text: String?): Boolean {
        return text == null || text.trim().isEmpty()
    }

    fun validateData(data: String): ValidationErrorModel? {
        return if (isBlank(data)) ValidationErrorModel(R.string.blank_data, ValidationError.DATA) else null
    }

    fun validateTelephone(phone: String): ValidationErrorModel? {
        return when {
            isBlank(phone) -> ValidationErrorModel(R.string.blank_phone, ValidationError.PHONE)
            phone.length !in 6..15 -> ValidationErrorModel(R.string.invalid_phone, ValidationError.PHONE)
            else -> null
        }
    }

    fun validateUserName(userName: String?): ValidationErrorModel? {
        return if (isBlank(userName))
            ValidationErrorModel(R.string.blank_username, ValidationError.USERNAME)
        /*else if (!Pattern.compile(EMAIL_PATTERN).matcher(userName).matches())
            ValidationErrorModel(R.string.invalid_email, ValidationError.USERNAME)*/
        else
            null
    }

    fun isBlank(editText: EditText): Boolean {
        return editText.text == null || editText.text.trim().isEmpty()
    }

    fun validateData(context: Context, str: String, strKey: String): String {
        return if (isBlank(str)) {
            context.resources.getString(R.string.invalid) + strKey
        } else ""
    }

    fun validateNumber(strNumber: String, min: Int, max: Int): Boolean {
        return strNumber.length in min..max
    }

    fun validateAllFields(data: ArrayList<String>): ValidationErrorModel? {
        for (str in data) {
            if (isBlank(str)) {
                return@validateAllFields ValidationErrorModel(R.string.msg_all_field_required, ValidationError.DATA)
            }
        }
        return null
    }
}