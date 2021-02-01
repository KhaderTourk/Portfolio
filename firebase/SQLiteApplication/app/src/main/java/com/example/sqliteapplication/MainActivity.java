package com.example.sqliteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText et_login_ID;
    private EditText et_login_password;

    private Button btn_Signin;
    private Button btn_go_to_signup;

    private Validation inputValidation;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews() {
        et_login_ID = (EditText) findViewById(R.id.et_login_ID);
        et_login_password = (EditText) findViewById(R.id.et_login_password);
        btn_Signin = (Button) findViewById(R.id.btn_Signin);
        btn_go_to_signup = (Button) findViewById(R.id.btn_go_to_signup);
    }

    private void initListeners() {
        btn_Signin.setOnClickListener(this);
        btn_go_to_signup.setOnClickListener(this);
    }

    private void initObjects() {
        db = new DBHelper(getApplicationContext());
        inputValidation = new Validation(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Signin:
                verifyFromSQLite();
                break;
            case R.id.btn_go_to_signup:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), SignUp.class);
                finishAfterTransition();
                startActivity(intentRegister);
                break;
        }
    }

    private void verifyFromSQLite() {
        if (!inputValidation.isFilled(et_login_ID, "ID is required!")) {
            return;
        }
        if (!inputValidation.isFilled(et_login_password, "Password is required!")) {
            return;
        }
        if (!inputValidation.isID(et_login_ID )) {
            return;
        }

        if (db.checkUser(et_login_ID.getText().toString().trim()
                , et_login_password.getText().toString().trim())) {
            Intent accountsIntent = new Intent(getApplicationContext(), Home.class);
            accountsIntent.putExtra("EMAIL", et_login_ID.getText().toString().trim());
            emptyInputEditText();
            finishAfterTransition();
            startActivity(accountsIntent);
        } else {
            // Snack Bar to show success message that record is wrong
            Toast.makeText(getApplicationContext() , "ID or password is wrong",Toast.LENGTH_SHORT).show();
        }
    }

    private void emptyInputEditText() {
        et_login_ID.setText(null);
        et_login_password.setText(null);
    }
}