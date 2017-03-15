package com.exmple.android.streetartmuseum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    private Button btnMainLogin;
    private TextView tvMainHello;
    private Button btnMainLogout;
    private EditText username;
    private EditText pswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.et_login_username);
        pswd = (EditText) findViewById(R.id.et_login_password);
        btnMainLogout = (Button) findViewById(R.id.btn_main_logout);
        btnMainLogin = (Button) findViewById(R.id.btn_main_login);
        tvMainHello = (TextView) findViewById(R.id.tv_main_hello);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser user = ParseUser.getCurrentUser();
            tvMainHello.setText("Hello " + user.getUsername());
        }

        btnMainLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.getCurrentUser().logOut();
                tvMainHello.setText("no user logged");
            }
        });
        btnMainLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginSignUpActivity.class);
                startActivity(i);
            }
        });
    }
}
