package com.student.fwr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Loginscreen extends AppCompatActivity {
    private RegisterDbHelper dbHelper;
    EditText edt_email,edt_password;
    Button edt_Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);
        dbHelper = new RegisterDbHelper(this);
        edt_email = findViewById(R.id.emailEditText);
        edt_password = findViewById(R.id.passwordEditText);
        edt_Button = findViewById(R.id.loginButton);

        edt_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email =edt_email.getText().toString();
                String password = edt_password.getText().toString();
                if(email.length()==0) {
                    Toast.makeText(Loginscreen.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()==0){
                    Toast.makeText(Loginscreen.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                dbHelper.checkUsername(email);

               int isLoggedIn= dbHelper.checkUsernameAndPassword(email,password);
                Toast.makeText(Loginscreen.this, "Logged in "+isLoggedIn, Toast.LENGTH_SHORT).show();
            }
        });

    }
}