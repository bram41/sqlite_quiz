package com.bram41.tugasquizbackend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bram41.tugasquizbackend.util.SessionManager;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class login extends AppCompatActivity {

    //Declaration EditTexts
    EditText editTextEmail;
    EditText editTextPassword;

    //Declaration TextInputLayout
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    //Declaration Button
    Button buttonLogin;

    // Session Manager Class
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManager(getApplicationContext());
        initViews();
        //set click event of login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check user input is correct or not
                if (validate()) {

                    //Get values from EditText fields
                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();

                    String user = "admin";
                    //Check Authentication is successful or not
                    if (user != null) {
                        Snackbar.make(buttonLogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();

                        session.createLoginSession(""+Email,""+Password);
                            // Staring home
                            Intent i = new Intent(getApplicationContext(), home.class);
                            startActivity(i);
                            finish();
                            //User Logged in Successfully Launch You home screen activity
                            Intent intent=new Intent(login.this,home.class);
                            startActivity(intent);
                            finish();


                    } else {

                        //User Logged in Failed
                        Snackbar.make(buttonLogin, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show();

                    }
                }
            }
        });


    }


    //this method is used to connect XML views to its Objects
    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

    }

    //This method is for handling fromHtml method deprecation
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();
        String mail = "admin";
        String pass = "admin";

        //Handling validation for Email field
        if (!mail.equals(Email)) {
            valid = false;
            textInputLayoutEmail.setError("Please enter valid email!");
        } else {
            valid = true;
            textInputLayoutEmail.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Please insert password!");
        } else {
            if (!mail.equals(Password)) {
                valid = false;
                textInputLayoutPassword.setError("Please enter valid password!");
            } else {
                valid = true;
                textInputLayoutPassword.setError(null);
            }
        }

        return valid;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(login.this, welcome.class);
        startActivity(intent);
        finish();
    }

}
