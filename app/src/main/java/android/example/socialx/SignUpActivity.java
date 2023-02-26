package android.example.socialx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hbb20.CountryCodePicker;

public class SignUpActivity extends AppCompatActivity {

    LinearLayout logIn, register;
    EditText edtName, edtEmail, edtContact, edtPassword;
    CountryCodePicker ccp;
    CheckBox checkBox;
    TextView tnc, signIn;
    FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        logIn = findViewById(R.id.logIn);
        edtName = findViewById(R.id.name);
        edtEmail = findViewById(R.id.email);
        ccp = findViewById(R.id.ccp);
        edtContact = findViewById(R.id.contact_no);
        edtPassword = findViewById(R.id.password);
        checkBox = findViewById(R.id.checkBox);
        tnc = findViewById(R.id.tnc);
        signIn = findViewById(R.id.signIn);
        register = findViewById(R.id.register);

        mAuth = FirebaseAuth.getInstance();

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked() && checkAllFields()){
                    register();

                }else{
                    Toast.makeText(SignUpActivity.this, "Please Accept Terms & Conditions", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkAllFields() {
        if (edtName.length() == 0) {
            edtName.setError("This field is required");
            return false;
        }

        if (edtContact.length() == 0) {
            edtContact.setError("This field is required");
            return false;
        }else if(edtContact.length() < 10){
            edtContact.setError("Please enter correct phone number");
        }

        if (edtEmail.length() == 0) {
            edtEmail.setError("Email is required");
            return false;
        }

        if (edtPassword.length() == 0) {
            edtPassword.setError("Password is required");
            return false;
        } else if (edtPassword.length() < 8) {
            edtPassword.setError("Password must be minimum 8 characters");
            return false;
        }

        // after all validation return true.
        return true;
    }

    private void register() {
        String name = edtName.getText().toString();
        String code = ccp.getSelectedCountryCode();
        String number = edtContact.getText().toString();
        String contact = code + number;
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
    }


}