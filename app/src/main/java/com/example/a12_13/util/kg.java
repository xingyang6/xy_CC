package com.example.a12_13.util;

public class kg {
    public static long lasttine;
    public static boolean isFastDoubleclick(){
        long time=System.currentTimeMillis();
        long mtime=time-lasttine;
        if (0<mtime&&mtime<800){
            return true;
        }
        lasttine=time;
        return false;
    }
}
