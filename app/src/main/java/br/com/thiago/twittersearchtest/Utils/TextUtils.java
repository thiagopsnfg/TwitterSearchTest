package br.com.thiago.twittersearchtest.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Thiago on 20/11/2015.
 */
public class TextUtils {

    public static final String CUTE_CARTOON = "fonts/CuteCartoon.ttf";

    public static void setFont(Context ctx,TextView txtView, String font){
        txtView.setTypeface(Typeface.createFromAsset(ctx.getAssets(), font));
    }
    public static void setButtonFont(Context ctx,Button btnView, String font){
        btnView.setTypeface(Typeface.createFromAsset(ctx.getAssets(), font));
    }
}
