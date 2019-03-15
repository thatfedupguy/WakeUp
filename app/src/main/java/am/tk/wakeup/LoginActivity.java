package am.tk.wakeup;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG ="Login" ;
    EditText etLoginEmail;
    EditText etLoginPassword;
    EditText etLoginUsername;
    Button loginBtn;
    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    Toolbar login_app_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Firebase
        mAuth = FirebaseAuth.getInstance();
        //Create instance
        progressDialog=new ProgressDialog(this);
        login_app_bar=findViewById(R.id.login_app_bar);
        etLoginUsername=findViewById(R.id.et_login_username);
        etLoginEmail=findViewById(R.id.et_login_email);
        etLoginPassword=findViewById(R.id.et_login_password);
        loginBtn=findViewById(R.id.loginBtn);
        //real code

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager cm= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=cm.getActiveNetworkInfo();
                boolean isConnected=networkInfo!=null&&networkInfo.isConnected();

                String email=etLoginEmail.getText().toString();
                String password=etLoginPassword.getText().toString();
                if(!(TextUtils.isEmpty(email)||TextUtils.isEmpty(password))&&isConnected){
                    progressDialog.setTitle("Logging In");
                    progressDialog.setMessage("Please wait while you are logging In");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    login_user(email,password);
                }
                 else if (TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this,"Blank Fields Left",Toast.LENGTH_SHORT).show();
                }
                else if(!isConnected){
                    Toast.makeText(LoginActivity.this,"Not Connected to Internet",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this,"Some Error Occured",Toast.LENGTH_SHORT).show();
                }

            }
        });
        setSupportActionBar(login_app_bar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void login_user(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            progressDialog.dismiss();
                            Intent mainIntent=new Intent(LoginActivity.this,MainActivity.class);
                            mainIntent.putExtra("Username",etLoginUsername.getText().toString());
                            mainIntent.putExtra("Email",etLoginEmail.getText().toString());
                            startActivity(mainIntent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            progressDialog.hide();
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}
