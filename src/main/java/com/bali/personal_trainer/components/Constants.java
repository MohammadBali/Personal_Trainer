package com.bali.personal_trainer.components;

import java.util.ArrayList;
import java.util.Arrays;

public class Constants
{
    public static ArrayList<String> noAuthenticationRequests = new ArrayList<>(Arrays.asList("/user/login","/user/signUp"));

    public static ArrayList<String> adminAuthenticationRequests = new ArrayList<>(Arrays.asList("/user/all"));
}
