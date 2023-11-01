package cl.daravena.simple_firebase_crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText etUsuarioEmail, etUsuarioPassword;
    Button btnLogin;
    Switch swCreateAccount;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        etUsuarioEmail = findViewById(R.id.etUsuarioEmail);
        etUsuarioPassword = findViewById(R.id.etUsuarioPassword);
        btnLogin = findViewById(R.id.btnLogin);
        swCreateAccount = findViewById(R.id.swCreateAccount);

        btnLogin.setOnClickListener(view -> {

            if (validateFields()) {
                String email = etUsuarioEmail.getText().toString();
                String password = etUsuarioPassword.getText().toString();
                if (swCreateAccount.isChecked()) {
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        proceed(getApplicationContext());
                                    } else {
                                        Log.e("Create Account", "Hubo un error", task.getException());
                                    }
                                }
                            });
                } else {
                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        proceed(getApplicationContext());
                                    } else {
                                        Log.e("Sign In", "Hubo un error", task.getException());
                                    }
                                }
                            });
                }
            }

        });
    }

    private void proceed(Context context) {
        Intent i = new Intent(context, MainActivity.class);
        startActivity(i);
    }

    private boolean validateFields() {
        return !etUsuarioEmail.getText().toString().isEmpty()
                && !etUsuarioPassword.getText().toString().isEmpty();
    }

}