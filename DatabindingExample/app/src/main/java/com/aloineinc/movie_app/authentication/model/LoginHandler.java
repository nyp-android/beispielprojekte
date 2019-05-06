package com.aloineinc.movie_app.authentication.model;

import android.view.View;
import android.widget.Toast;

/**
 * Created by themavencoder on 02,March,2019
 */
public class LoginHandler {
    private User user;

    public LoginHandler(User user) {
        this.user = user;
    }

    public void buttonLoginClick(View view) {
        Toast.makeText(view.getContext(), "First name is: " + user.getFirstName() + "Last name is " + user.getLastName() + "and password is " + user.getPassword(), Toast.LENGTH_SHORT).show();
    }
}
