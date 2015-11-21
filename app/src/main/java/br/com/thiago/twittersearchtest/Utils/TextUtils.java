package br.com.thiago.twittersearchtest.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Thiago on 20/11/2015.
 */
public class TextUtils {

    public static final String FONT_CUTE_CARTOON = "fonts/CuteCartoon.ttf";

    public static Typeface getTypeface(Context ctx, String font){
       return Typeface.createFromAsset(ctx.getAssets(), font);
    }
    public static void hideKeyboard(Context context, View editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

}
