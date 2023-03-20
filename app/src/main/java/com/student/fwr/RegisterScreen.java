package com.student.fwr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterScreen extends AppCompatActivity {
    private RegisterDbHelper dbHelper;
    public EditText edt_name, edt_address, edt_cell, edt_email, edt_password;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        dbHelper = new RegisterDbHelper(this);
        edt_name = findViewById(R.id.register_name);
        edt_address = findViewById(R.id.register_address);
        edt_cell = findViewById(R.id.register_cell_phone);
        edt_email = findViewById(R.id.register_email);
        edt_password = findViewById(R.id.register_password);
        // button
        button = findViewById(R.id.register_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edt_name.getText().toString();
                String address = edt_address.getText().toString();
                String cell = edt_cell.getText().toString();
                String password = edt_password.getText().toString();
                String email = edt_email.getText().toString();
                Log.d("Data", name + " " + address + cell + password + email);
              Boolean isEmailAvailable = dbHelper.checkUsername(email);
              if(!isEmailAvailable) {
                  long isRegistered = dbHelper.insertUser(name, address, cell, password, email);
              if (isRegistered!=-1){
                  Toast.makeText(RegisterScreen.this,"Registered Successfully",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RegisterScreen.this,Loginscreen.class);
                    startActivity(i);
                    finish();
              }
              }
            }
        });
    }
}