package com.bali.personal_trainer.components;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Constants
{
    public static ArrayList<String> noAuthenticationRequests = new ArrayList<>(Arrays.asList("/user/login","/user/signUp"));

    public static ArrayList<String> adminAuthenticationRequests = new ArrayList<>(Arrays.asList("/*/all", "/transaction/find/totalPrice"));

    /** Get the current date and time **/
    public static LocalDateTime nowLocalDateTime = LocalDateTime.now();

    /** Define the format **/
    public static DateTimeFormatter timeStampFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}
