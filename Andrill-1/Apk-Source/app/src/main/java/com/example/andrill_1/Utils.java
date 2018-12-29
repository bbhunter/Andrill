package com.example.andrill_1;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by tyler on 12/27/18.
 */

public class Utils {
    public static  String BASE_URL="";


    public static void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }

    public static String getBaseUrl(Context ctx) {
        readRawTextFile(ctx,R.raw.server);
        return BASE_URL;
    }

    public static String readRawTextFile(Context ctx, int resId)
    {
        InputStream inputStream = ctx.getResources().openRawResource(resId);
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        StringBuilder text = new StringBuilder();

        try {
            while (( line = buffreader.readLine()) != null) {
                text.append(line);
//                text.append('\n');
            }
        } catch (IOException e) {
            return null;
        }
        BASE_URL=text.toString();
        return text.toString();
    }




}
