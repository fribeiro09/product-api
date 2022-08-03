package com.productapi.app.util;

import javax.swing.text.MaskFormatter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class ParserUtil {

    public static boolean nonNullNonEmpty(String s) {
        return s != null && !s.isEmpty();
    }

    /**
     * Returns true if string is null or blank
     */
    public static boolean isEmpty(String s) {
        return !nonNullNonEmpty(s);
    }

    public static String redactCenter(String input, int percentage) {
        return ParserUtil.redactCenter(input, percentage, "*");
    }

    public static String redactCenter(String input, int percentage, String redactChar) {

        if (input == null) {
            return input;
        }

        if (percentage > 100) {
            percentage = 100;
        } else if (percentage < 0) {
            percentage = 0;
        }

        if (percentage == 0) {
            return input;
        }

        int length = input.length();
        int redactLength = length * percentage / 100;
        int redactStartIdx = (length - redactLength) / 2;

        String redacted = input.substring(0, redactStartIdx);
        redacted += ParserUtil.repeat(redactChar, redactLength) + input.substring(redactStartIdx + redactLength);

        return redacted;
    }

    public static String repeat(String input, int times) {

        String repeatedString = "";

        for (int i = 0; i < times; i++) {
            repeatedString += input;
        }

        return repeatedString;
    }

    public static String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    public static String formatCnpj(String cnpj) {
        try {
            MaskFormatter mask = new MaskFormatter("##.###.###/####-##");
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(cnpj);
        } catch (ParseException ex) {
            return cnpj;
        }
    }

    public static String formatCep(String cep) {
        try {
            MaskFormatter mask = new MaskFormatter("#####-###");
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(cep);
        } catch (ParseException ex) {
            return cep;
        }
    }

    public static String formatBigDecimal(BigDecimal number, Boolean returnsDecimalValue) {
        Locale br = new Locale("pt", "BR");
        NumberFormat reaisFormat = NumberFormat.getCurrencyInstance(br);

        BigDecimal numberChecked = number == null ? new BigDecimal(0) : number;
        String formattedNumber = reaisFormat.format(numberChecked).replace("\u00A0", " ");
        String formattedNumberWithoutDecimals = formattedNumber.split(",")[0];
        String numberDecimalPart = numberChecked.remainder(BigDecimal.ONE).toString().contains(".") ? numberChecked.remainder(BigDecimal.ONE).toString().split("\\.")[1] : "00";
        String formattedNumberWithCorrectDecimals = formattedNumberWithoutDecimals + "," + numberDecimalPart;
        formattedNumberWithCorrectDecimals = formattedNumberWithCorrectDecimals.split("R\\$")[1];

        if (returnsDecimalValue) {
            return formattedNumberWithCorrectDecimals;
        } else {
            return formattedNumberWithCorrectDecimals.split(",")[0];
        }
    }

}
