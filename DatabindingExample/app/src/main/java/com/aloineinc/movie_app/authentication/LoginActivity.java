package com.aloineinc.movie_app.authentication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.aloineinc.movie_app.R;
import com.aloineinc.movie_app.authentication.model.LoginHandler;
import com.aloineinc.movie_app.authentication.model.User;
import com.aloineinc.movie_app.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    
    super.onCreate(savedInstanceState);
    //inflate the layout using the predefined DataBindingUtil class
    //create an object of the generated class
    ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
    //instantiate the created object
    User user = new User();
    //parse the object to the handler constructor
    LoginHandler handler = new LoginHandler(user);
    //set the user model to the binding class
    binding.setUser(user);
    //set the handler object to the binding class
    binding.setHandler(handler);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    
    
  }
}
