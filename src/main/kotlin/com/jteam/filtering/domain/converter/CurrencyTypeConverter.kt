package com.jteam.filtering.domain.converter

import com.jteam.filtering.domain.Currency
import javax.persistence.AttributeConverter

class CurrencyTypeConverter : AttributeConverter<Currency, String> {

    override fun convertToDatabaseColumn(attribute: Currency?): String {
        return attribute.toString().toLowerCase()
    }

    override fun convertToEntityAttribute(dbData: String?): Currency? {
        return if (dbData == null) null else Currency.valueOf(dbData.toUpperCase())
    }

}