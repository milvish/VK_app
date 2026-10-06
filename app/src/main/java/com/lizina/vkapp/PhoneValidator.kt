package com.lizina.vkapp

import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber

object PhoneValidator {

    private val util: PhoneNumberUtil = PhoneNumberUtil.getInstance()

    /**
     * Проверяет, является ли номер валидным для указанной страны.
     * @param rawNumber номер как ввёл пользователь (без кода страны)
     * @param isoCode   ISO-код страны, например "RU"
     */
    fun isValid(rawNumber: String, isoCode: String): Boolean {
        if (rawNumber.isBlank()) return false
        return try {
            val number = util.parse(rawNumber, isoCode.uppercase())
            util.isValidNumber(number)
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Возвращает номер в формате E.164 ("+79991234567")
     * или null, если номер невозможно распарсить.
     */
    fun toE164(rawNumber: String, isoCode: String): String? {
        return try {
            val number = util.parse(rawNumber, isoCode.uppercase())
            util.format(number, PhoneNumberUtil.PhoneNumberFormat.E164)
        } catch (e: Exception) {
            null
        }
    }
}
