package com.productapi.app.util;

import com.productapi.app.web.base.Code;
import com.productapi.app.web.base.CodeInt;

/**
 * Generically converts our code enum pattern from String codes to Enum values
 */
public class EnumConverter {

    public static <T extends Code> T getByCode(String code, T[] values) {
        if (!ParserUtil.nonNullNonEmpty(code)) {
            return null;
        }

        for (T val : values) {
            if (code.equals(val.getCode())) {
                return val;
            }
        }

        return null;
    }

    public static <T extends CodeInt> T getByCode(int code, T[] values) {
        if (code == 0) {
            return null;
        }

        for (T val : values) {
            if (code == val.getCode())
                return val;
        }

        return null;
    }
}
