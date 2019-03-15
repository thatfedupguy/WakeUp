package am.tk.wakeup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    public static final String TAG="register";
    EditText etRegUsername;
    private EditText etRegEmail;

    private EditText etRegPassword;
    private Button registerRegBtn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //Firebase part
        mAuth=FirebaseAuth.getInstance();
        //Instantiating
        progressDialog=new ProgressDialog(SignupActivity.this);
        etRegUsername=findViewById(R.id.etRegUsername);
        etRegEmail=findViewById(R.id.etRegPassword);

        etRegPassword=findViewById(R.id.etRegPassword);
        registerRegBtn=findViewById(R.id.registerRegBtn);
        ConnectivityManager cm= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=cm.getActiveNetworkInfo();
        final boolean isConnected=networkInfo!=null&&networkInfo.isConnected();
        registerRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username=etRegUsername.getText().toString();
                String email=etRegEmail.getText().toString();

                String password=etRegPassword.getText().toString();

                if(!(TextUtils.isEmpty(username)||TextUtils.isEmpty(email)||TextUtils.isEmpty(password))&&isConnected){
                    progressDialog.setTitle("Registering user");
                    progressDialog.setMessage("Please ewait while you are registering");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    register_user(username,email,password);
                }
                else if(TextUtils.isEmpty(username)||TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
                    Toast.makeText(SignupActivity.this,"Blank Fields Left",Toast.LENGTH_SHORT).show();
                }
                else if(!isConnected){
                    Toast.makeText(SignupActivity.this,"Not Connected to Internet",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SignupActivity.this,"Some Error Occured",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void register_user(String username, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Intent mainIntent=new Intent(SignupActivity.this,MainActivity.class);
                            startActivity(mainIntent);
                            finish();
                        } else {
                            progressDialog.hide();
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}
