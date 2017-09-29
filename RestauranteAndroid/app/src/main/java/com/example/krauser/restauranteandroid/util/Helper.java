package com.example.krauser.restauranteandroid.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
    private static final String format = "dd/MM/yyyy - HH:mm";

    public static String dateToString(Date data){
        return new SimpleDateFormat(format).format(data);
    }

    public static Date stringToDate(String data){
        try {
            return new SimpleDateFormat(format).parse(data);
        }catch(Exception ex){
            return new Date();
        }
    }
}
