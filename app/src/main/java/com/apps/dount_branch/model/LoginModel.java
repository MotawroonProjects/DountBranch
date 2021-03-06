package com.apps.dount_branch.model;

import android.content.Context;
import android.util.Log;
import android.util.Patterns;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.apps.dount_branch.BR;
import com.apps.dount_branch.R;

import java.io.Serializable;

public class LoginModel extends BaseObservable implements Serializable {
    private String email;
    private String password;
    public ObservableField<String> error_email = new ObservableField<>();
    public ObservableField<String> error_password = new ObservableField<>();

    public LoginModel() {
        email = "";
        password = "";
    }

    public boolean isDataValid(Context context) {
        if (!email.isEmpty() &&
                Patterns.EMAIL_ADDRESS.matcher(email).matches()
                &&!password.isEmpty()
//                password.length() >= 6
        ) {
            error_email.set(null);
            error_password.set(null);


            return true;
        } else {

          //  Log.e("dsds","dd");
            if (email.isEmpty()) {
                error_email.set(context.getString(R.string.field_required));

            }
            else if( !Patterns.EMAIL_ADDRESS.matcher(email).matches() ){
                error_email.set(context.getResources().getString(R.string.inv_email));
            }
            else {
                error_email.set(null);

            }

            if (password.isEmpty()) {
                error_password.set(context.getString(R.string.field_required));

            }
//            else if (password.length() < 6) {
//                error_password.set(context.getString(R.string.pass_short));
//
//            }
            else {
                error_password.set(null);

            }


            return false;
        }
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);

    }


}