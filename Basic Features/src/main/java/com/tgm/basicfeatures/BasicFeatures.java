package com.tgm.basicfeatures;

import android.content.Context;
import android.widget.Toast;

public class BasicFeatures {


    public static void print(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

}
