package com.exmple.android.streetartmuseum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.exmple.android.streetartmuseum.app.LoginFragment;
import com.exmple.android.streetartmuseum.app.SignUpFragment;
import com.exmple.android.streetartmuseum.app.UserList;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginSignUpActivity extends AppCompatActivity implements SignUpFragment.Callback, LoginFragment.Callback {

    private Button btnLoginSignupSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //REDIRECTION VERS LA LISTE DES USER SI DEJA CONNECTE
        if (ParseUser.getCurrentUser() != null) {
            showUserList();
        } else {
            //region AFFICHAGE DE LA PAGE DE CONNEXION
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            LoginFragment lgf = new LoginFragment();
            lgf.setCallback(LoginSignUpActivity.this);
            ft.add(R.id.ll_loginSignup_ctn, lgf);
            ft.commit();
            //endregion
        }


    }

    //region ENREGISTREMENT DU USER
    @Override
    public void signUp(String... infos) {
        ParseUser user = new ParseUser();
        user.setEmail(infos[0]);
        user.setUsername(infos[1]);
        user.setPassword(infos[2]);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    showUserList();
                    Toast.makeText(LoginSignUpActivity.this, R.string.signUpMsg, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginSignUpActivity.this, e.getMessage().substring(e.getMessage().indexOf(" ")), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //endregion
    //region RENVOI LA PAGE D'ENREGISTREMENT
    @Override
    public void signUpPage() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        SignUpFragment suf = new SignUpFragment();
        suf.setCallback(LoginSignUpActivity.this);
        ft.replace(R.id.ll_loginSignup_ctn, suf);
        ft.commit();
    }

    //endregion
    //region LISTE USER
    private void showUserList() {
        Intent i = new Intent(getApplicationContext(), UserList.class);
        startActivity(i);
    }

    //endregion
    //region CONNEXION USER
    @Override
    public void login(String... infos) {
        ParseUser.logInInBackground(infos[0], infos[1], new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginSignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //endregion
}
