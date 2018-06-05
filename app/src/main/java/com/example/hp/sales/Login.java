package com.example.hp.sales;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Button b1, b2;
    private EditText email, password;
    TextView forgetPass;
    private ProgressDialog progressDialog;
    public static  final String Login_URL = "http://127.0.0.1/appli/login.php";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);


        b1 = (Button) findViewById(R.id.button2);
        b2 = (Button) findViewById(R.id.button1);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        forgetPass = (TextView) findViewById(R.id.forgett);
        forgetPass.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }


    private void next() {
        Intent i = new Intent(Login.this, Register.class);
        startActivity(i);
    }



    private void userLogin() {
        final String emmail = email.getText().toString().trim();
        final String pass = password.getText().toString().trim();
        if (emmail.equals("") || pass.equals("")) {
            Toast.makeText(Login.this,"All fields are required",Toast.LENGTH_LONG).show();
            return;
        }
        else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Login_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try{
                        JSONObject jsonObject = new JSONObject(response);
                        boolean responsestatus = jsonObject.getBoolean("success");
                        if (responsestatus){
                            String name = jsonObject.getString("name");
                            Intent intent = new Intent(Login.this,Empty.class);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Login.this, "Unsuccessful", Toast.LENGTH_LONG).show();
                }
            })

            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(KEY_EMAIL, emmail);
                    params.put(KEY_PASSWORD, pass);

                    return params;
                }


            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                userLogin();
                break;
            case R.id.button2:
                next();
                break;

        }
    }
}