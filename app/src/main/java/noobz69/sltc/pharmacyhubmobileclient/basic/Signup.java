package noobz69.sltc.pharmacyhubmobileclient.basic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import noobz69.sltc.pharmacyhubmobileclient.R;
import noobz69.sltc.pharmacyhubmobileclient.classes.CustomerAccount;

public class Signup extends AppCompatActivity {
    EditText username, email, phoneNumber, password, confirmPassword;
    Button signupBtn;
    TextView loginBtn;

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.usernameEditTextLogin);
        email = findViewById(R.id.passwordEditTextLogin);
        phoneNumber = findViewById(R.id.phoneNumberEditText);
        password = findViewById(R.id.passwordEditText);
        confirmPassword = findViewById(R.id.confirmPasswordEditText);
        signupBtn = findViewById(R.id.loginBtnLogin);
        loginBtn = findViewById(R.id.signupPageLoginBtn);

        firestore = FirebaseFirestore.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Username = username.getText().toString();
                String Email = email.getText().toString();
                String PhoneNumber = phoneNumber.getText().toString();
                String Password = password.getText().toString();
                String ConfirmPassword = confirmPassword.getText().toString();

                if (isNotEmpty(Username, Email, PhoneNumber, Password, ConfirmPassword))
                {
                    if(!Password.equals(ConfirmPassword))
                    {
                        confirmPassword.setError("Passwords do not match!");
                    }
                    else
                    {
                        CustomerAccount account = new CustomerAccount();
                        account.setUsername(Username);
                        account.setName(Username);
                        account.setContactNo(PhoneNumber);
                        account.setEmail(Email);
                        account.setPassword(Password);

                        DocumentReference documentReference = firestore.collection("customerAccounts").document(Username);
                        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                                if (error != null)
                                {
                                    Toast.makeText(getApplicationContext(), "DB Error", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                if (value != null && value.exists())
                                {
                                    Toast.makeText(getApplicationContext(), "Account already exists!", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    firestore.collection("customerAccounts").document(Username).set(account);
                                    Toast.makeText(getApplicationContext(), "Account Created Successfully!", Toast.LENGTH_SHORT).show();
                                    clear();
                                }
                            }
                        });

                    }

                }
            }
        });
    }

    private void clear()
    {
        username.getText().clear();
        email.getText().clear();
        phoneNumber.getText().clear();
        password.getText().clear();
        confirmPassword.getText().clear();
    }

    private boolean isNotEmpty(String Username, String Email, String PhoneNumber, String Password, String ConfirmPassword)
    {
        if (TextUtils.isEmpty(Username))
        {
            username.setError("Enter Username!");
            return false;
        }
        else if (TextUtils.isEmpty(Email))
        {
            email.setError("Enter Email!");
            return false;
        }
        else if (TextUtils.isEmpty(PhoneNumber))
        {
            phoneNumber.setError("Enter Phone Number!");
            return false;
        }
        else if (TextUtils.isEmpty(Password))
        {
            password.setError("Enter Password!");
            return false;
        }
        else if (TextUtils.isEmpty(ConfirmPassword))
        {
            confirmPassword.setError("Re-enter password!");
            return false;
        }
        return true;
    }

}