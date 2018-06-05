package com.example.hp.sales;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity implements View.OnClickListener {
    EditText name, email, password, cpassword, phone;
    Button regis;
    private ProgressDialog progressDialog;
    public static final String URL = "http://127.0.0.1/appli/register.php";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String PHONE = "phone";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressDialog = new ProgressDialog(this);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        cpassword = (EditText) findViewById(R.id.cpassword);
        phone = (EditText) findViewById(R.id.phone);
        regis = (Button) findViewById(R.id.regi);
        regis.setOnClickListener(this);
    }


    private void registerUser() {
        final String naam = name.getText().toString().trim();
        final String emaail = email.getText().toString().trim();
        final String pass = password.getText().toString().trim();
        final String cpass = cpassword.getText().toString().trim();
        final String mobile = phone.getText().toString().trim();
        if (naam.isEmpty()) {
            name.setError("Name Required");
            name.requestFocus();
            return;
        } else if (emaail.isEmpty()) {
            email.setError("Email Required");
            email.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emaail).matches()) {
            email.setError("Enter a valid Email ");
            email.requestFocus();
            return;
        } else if (pass.isEmpty()) {
            password.setError("Password Required");
            password.requestFocus();
            return;
        } else if (pass.length() < 6) {
            password.setError("Password shoul be atleast 6 characters long");
            password.requestFocus();
            return;
        } else if (cpass.isEmpty()) {
            cpassword.setError("Password Required");
            cpassword.requestFocus();
            return;
        } else if (!(pass.equals(cpass))) {
            Toast.makeText(Register.this, "Password doesnot match", Toast.LENGTH_SHORT).show();
        } else if (mobile.isEmpty()) {
            phone.setError("Number Required");
            phone.requestFocus();
            return;
        } else if (mobile.length() != 10) {
            phone.setError("Enter a valid phone number");
            phone.requestFocus();
            return;
        }
        else {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(Register.this, response, Toast.LENGTH_SHORT).show();
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Register.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            })

            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(NAME, naam);
                    params.put(EMAIL, emaail);
                    params.put(PASSWORD, pass);
                    params.put(PHONE, mobile);
                    return params;
                }


            };
            requestQueue.add(stringRequest);
        }

    }
    @Override
    public void onClick (View v){
        switch (v.getId()) {
            case R.id.regi:
                registerUser();
                break;

        }
    }

}