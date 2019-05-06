package com.aloineinc.movie_app.authentication.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.aloineinc.movie_app.BR;

/**
 * Created by themavencoder on 01,March,2019
 */
public class User extends BaseObservable {
  
  private String firstName;
  private String lastName;
  private String password;
  
  @Bindable
  public String getFirstName() {
    
    return firstName;
  }
  
  @Bindable
  public String getLastName() {
    
    return lastName;
  }
  
  @Bindable
  public String getPassword() {
    
    return password;
  }
  
  public void setFirstName(String firstName) {
    
    this.firstName = firstName;
    notifyPropertyChanged(BR.firstName);
    
  }
  
  public void setLastName(String lastName) {
    
    this.lastName = lastName;
    notifyPropertyChanged(BR.lastName);
  }
  
  public void setPassword(String password) {
    
    this.password = password;
    notifyPropertyChanged(BR.password);
  }
  
  public boolean checkInput(String firstName, String lastName, String password) {
    
    if (firstName == null || lastName == null || password == null)
      return false;
    return !firstName.isEmpty() && !lastName.isEmpty() && !password.isEmpty();
  }
  
}

