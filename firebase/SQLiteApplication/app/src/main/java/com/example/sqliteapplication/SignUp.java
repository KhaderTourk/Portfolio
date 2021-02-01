package com.example.sqliteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText et_username;
    private EditText et_userid;
    private EditText et_password;
    private EditText et_confpassword;
    private Button btn_create;
    private Button btn_back_to_login;
    private Validation inputValidation;
    private DBHelper db;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }
    private void initViews() {
        et_username = (EditText) findViewById(R.id.et_username);
        et_userid = (EditText) findViewById(R.id.et_userid);
        et_password = (EditText) findViewById(R.id.et_password);
        et_confpassword = (EditText) findViewById(R.id.et_confpassword);
        btn_create = (Button) findViewById(R.id.btn_create);
        btn_back_to_login = (Button) findViewById(R.id.btn_back_to_login);
    }
    private void initListeners() {
        btn_create.setOnClickListener(this);
        btn_back_to_login.setOnClickListener(this);
    }
    private void initObjects() {
        inputValidation = new Validation(getApplicationContext());
        db = new DBHelper(getApplicationContext());
        user = new User();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create:
                postDataToSQLite();
                break;
            case R.id.btn_back_to_login:
                Intent intentRegister = new Intent(getApplicationContext(), MainActivity.class);
                finishAfterTransition();
                startActivity(intentRegister);
                break;
        }
    }

    private void postDataToSQLite() {
        if (!inputValidation.isFilled(et_username, "Name is required!")) {
            return;
        }
        if (!inputValidation.isFilled(et_userid, "ID is required!")) {
            return;
        }
        if (!inputValidation.isID(et_userid )) {
            return;
        }
        if (!inputValidation.isFilled(et_password ,"Password is required!")) {
            return;
        }
        if (!inputValidation.isMatches(et_password, et_confpassword)) {
            return;
        }
        if (!db.checkUser(et_userid.getText().toString().trim(),et_password.getText().toString().trim())) {
            user.setUsername(et_username.getText().toString().trim());
            user.setUserID(et_userid.getText().toString().trim());
            user.setPassword(et_password.getText().toString().trim());
            db.addUser(user);
            // Snack Bar to show success message that record saved successfully
            Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_LONG).show();
            emptyInputEditText();
            Intent intentRegister = new Intent(getApplicationContext(), MainActivity.class);
            finishAfterTransition();
            startActivity(intentRegister);
        } else {
            // Snack Bar to show error message that record already exists
            Toast.makeText(getApplicationContext(),"ID is not exist",Toast.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText() {
        et_username.setText(null);
        et_userid.setText(null);
        et_password.setText(null);
        et_confpassword.setText(null);
    }
}