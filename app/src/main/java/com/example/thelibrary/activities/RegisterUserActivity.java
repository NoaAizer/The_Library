package com.example.thelibrary.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thelibrary.R;
import com.example.thelibrary.fireBase.model.FireBaseDBUser;
import com.example.thelibrary.fireBase.model.mAuthUser;

public class RegisterUserActivity extends AppCompatActivity {

    private EditText firstNameEditText,lastNameEditText, addressEditText, phoneEditText, emailEditText,passwordEditText;
    private Button register;
    FireBaseDBUser fireBaseUser = new FireBaseDBUser();
    mAuthUser auth = new mAuthUser();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        firstNameEditText=(EditText)findViewById(R.id.firstName);
        lastNameEditText=(EditText)findViewById(R.id.lastName);
        addressEditText=(EditText)findViewById(R.id.address);
        phoneEditText=(EditText)findViewById(R.id.phone);
        emailEditText=(EditText)findViewById(R.id.emailReg);
        passwordEditText=(EditText)findViewById(R.id.passReg);
        register = (Button)findViewById(R.id.reg);


        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String fName=firstNameEditText.getText().toString().trim();
                String lName=lastNameEditText.getText().toString().trim();
                String address=addressEditText.getText().toString().trim();
                String phone=phoneEditText.getText().toString().trim();
                String email=emailEditText.getText().toString().trim();
                String password=passwordEditText.getText().toString().trim();
                if (fName.isEmpty()) {
                    firstNameEditText.setError("First Name is required");
                }
                if (lName.isEmpty()) {
                    lastNameEditText.setError("Last Name is required");
                }
                if (address.isEmpty()) {
                    addressEditText.setError("Address is required");
                }
                if (phone == null||!Patterns.PHONE.matcher(phone).matches() ||phone.length() < 9 || phone.length()> 13) {
                    Toast.makeText(getApplicationContext(),"Invalid phone",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password))
                {
                    Toast.makeText(getApplicationContext(),"Enter email or password",Toast.LENGTH_LONG).show();
                    return;
                }
                if (email == null||!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(getApplicationContext(),"Invalid email",Toast.LENGTH_LONG).show();
                    return;
                }
                if(password == null || password.trim().length() <= 5)
                {
                    Toast.makeText(getApplicationContext(),"Password length must be at least 6",Toast.LENGTH_LONG).show();
                    return;
                }
                auth.registerUserToDB(email,password,RegisterUserActivity.this);
                fireBaseUser.addUserToDB (fName,lName,email,password,address,phone);
                Intent loginIntent=new Intent(RegisterUserActivity.this, LoginUserActivity.class);
                startActivity(loginIntent);
            }
        });

    }//onCreate

}