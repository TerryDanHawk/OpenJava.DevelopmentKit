package com.openjava.mvc.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Operator {

    public  static String Encode(String text) throws UnsupportedEncodingException {
        String result=text;
        Base64.Encoder encoder=Base64.getEncoder();
        result=encoder.encodeToString(text.getBytes("UTF-8"));
        return  result;
    }

    public static String Decode(String text) throws UnsupportedEncodingException {
        String result=text;
        Base64.Decoder decoder=Base64.getDecoder();
        result=new String(decoder.decode(text),"UTF-8");
        return  result;
    }
}
