package com.takeaway.eventservice.util;

import java.util.Objects;

public class Strings {

    public static boolean hasText(final String text){
        if(Objects.isNull(text)){
            return false;
        }
        return !text.isBlank();
    }


}
