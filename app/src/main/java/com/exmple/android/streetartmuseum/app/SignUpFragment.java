package com.exmple.android.streetartmuseum.app;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.exmple.android.streetartmuseum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    private Context context;
    private EditText pswd2;
    private EditText pswd;
    private EditText username;
    private EditText email;
    private Button signUpButton;

    public SignUpFragment() {
        // Required empty public constructor
    }

    public interface Callback {
        void signUp(String... s);
    }

    public void setCallback(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);
        email = (EditText) v.findViewById(R.id.et_signup_email);
        username = (EditText) v.findViewById(R.id.et_signup_username);
        pswd = (EditText) v.findViewById(R.id.et_signup_password);
        pswd2 = (EditText) v.findViewById(R.id.et_signup_password2);
        signUpButton = (Button) v.findViewById(R.id.btn_signup_signup);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] infos = {
                        email.getText().toString(),
                        username.getText().toString(),
                        pswd.getText().toString(),
                };
                ((Callback) context).signUp(infos);
            }
        });
        return v;
    }

}
