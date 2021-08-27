package com.bycorders.finance.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {

    private FileUtil(){}

    private static final Integer[] TYPE         = {0,   1};
    private static final Integer[] DATA         = {1,   9};
    private static final Integer[] VALUE        = {9,  19};
    private static final Integer[] CPF          = {19, 30};
    private static final Integer[] CREDIT_CARD  = {30, 42};
    private static final Integer[] HOUR         = {42, 48};
    private static final Integer[] SHOP_OWNER   = {48, 62};
    private static final Integer[] SHOP_NAME    = {62, 80};

    public static Long getTransactionType(String transaction) {
        return Long.valueOf(transaction.substring(TYPE[0], TYPE[1]));
    }

    public static Date getTransactionDate(String transaction) throws ParseException {
        return new SimpleDateFormat("yyyyMMdd").parse(transaction.substring(DATA[0], DATA[1]));
    }

    public static BigDecimal getTransactionValue(String transaction) {
        return new BigDecimal(transaction.substring(VALUE[0], VALUE[1]));
    }

    public static String getTransactionCpf(String transaction) {
        return transaction.substring(CPF[0], CPF[1]).trim();
    }

    public static String getTransactionCreditCard(String transaction) {
        return transaction.substring(CREDIT_CARD[0], CREDIT_CARD[1]).trim();
    }

    public static Date getTransactionHour(String transaction) throws ParseException {
        return new SimpleDateFormat("HHmmss").parse(transaction.substring(HOUR[0], HOUR[1]));
    }

    public static String getTransactionStoreOwner(String transaction) {
        return transaction.substring(SHOP_OWNER[0], SHOP_OWNER[1]).trim();
    }

    public static String getTransactionStoreName(String transaction) {
        return transaction.substring(SHOP_NAME[0], SHOP_NAME[1]).trim();
    }

    public static String[] getBody(MultipartFile file) throws IOException {
        return new String(file.getBytes(), StandardCharsets.UTF_8).split("\n");
    }

}
