package com.example.sqliteapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class Validation {

    private final Context context;

    public  Validation(Context context) {
        this.context = context;
    }

    public boolean isFilled(EditText fild, String message) {
        String value = fild.getText().toString().trim();
        if (value.isEmpty()) {
            Toast.makeText(context,message ,Toast.LENGTH_SHORT).show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                hideKeyboardFrom(fild);
            }
            return false;
        }
        return true;
    }

    public boolean isID(EditText idFild) {
        String value = idFild.getText().toString().trim();
        if (value.length() != 9) {
            Toast.makeText(context,"Invalid ID" ,Toast.LENGTH_SHORT).show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                hideKeyboardFrom(idFild);
            }
            return false;
        }
        return true;
    }

    public boolean isMatches(EditText passwordFild, EditText confPasswordFild) {
        String pass = passwordFild.getText().toString().trim();
        String coPass = confPasswordFild.getText().toString().trim();
        if (!pass.contentEquals(coPass)) {
            Toast.makeText(context,"Confirm password not equal password" ,Toast.LENGTH_SHORT).show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                hideKeyboardFrom(confPasswordFild);
            }
            return false;
        }
        return true;
    }

    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
