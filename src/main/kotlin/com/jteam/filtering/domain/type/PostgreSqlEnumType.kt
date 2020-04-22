package com.jteam.filtering.domain.type

import com.jteam.filtering.domain.Currency
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.type.EnumType
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Types

class PostgreSqlEnumType : EnumType<Currency>() {

    override fun nullSafeGet(
        rs: ResultSet?,
        names: Array<out String>?,
        session: SharedSessionContractImplementor?,
        owner: Any?
    ): Any {
        val currency = rs!!.getString(names!![0])
        return Currency.valueOf(currency.toUpperCase())
    }

    override fun nullSafeSet(
        st: PreparedStatement?,
        value: Any?,
        index: Int,
        session: SharedSessionContractImplementor?
    ) {
        st!!.setObject(
            index,
            if (value != null) (value as Enum<*>).name.toLowerCase() else null,
            Types.OTHER
        )
    }

}