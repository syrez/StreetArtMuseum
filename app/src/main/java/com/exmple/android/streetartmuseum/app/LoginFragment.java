package com.exmple.android.streetartmuseum.app;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.exmple.android.streetartmuseum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment  {


    private Button loginButton;
    private EditText pswd;
    private EditText username;
    private Context context;
    private TextView signUpLink;

    public LoginFragment() {
        // Required empty public constructor
    }


    public interface Callback {
        void login(String... s);
        void signUpPage();
    }

    public void setCallback(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        username = (EditText) v.findViewById(R.id.et_login_username);
        pswd = (EditText) v.findViewById(R.id.et_login_password);
        signUpLink = (TextView) v.findViewById(R.id.tv_login_register);
        loginButton = (Button) v.findViewById(R.id.btn_login_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] infos = {
                        username.getText().toString(),
                        pswd.getText().toString(),
                };
                ((Callback) context).login(infos);
            }
        });

        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Callback) context).signUpPage();
            }
        });
        return v;
    }

}
