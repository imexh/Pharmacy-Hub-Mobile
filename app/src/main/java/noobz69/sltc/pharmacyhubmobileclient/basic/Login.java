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
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import noobz69.sltc.pharmacyhubmobileclient.FirestoreInitializer;
import noobz69.sltc.pharmacyhubmobileclient.R;
import noobz69.sltc.pharmacyhubmobileclient.customerview.Homepage;

public class Login extends AppCompatActivity {

    public static String UserId;

    Button loginBtn;
    EditText username, password;
    TextView signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.loginBtnLogin);
        username = findViewById(R.id.usernameEditTextLogin);
        password = findViewById(R.id.passwordEditTextLogin);
        signupBtn = findViewById(R.id.signupPageLoginBtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username = username.getText().toString();
                String Password = password.getText().toString();

                if (isNotEmpty(Username, Password))
                {
                    FirestoreInitializer firestoreInitializer = new FirestoreInitializer();
                    DocumentReference documentReference = firestoreInitializer.getFirestore().collection("customerAccounts").document(Username);

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
                                Map<String, Object> customerData = new HashMap<>(value.getData());
                                if (customerData.get("password").equals(Password))
                                {
                                    UserId = Username;
                                    clear();
                                    Intent intent = new Intent(Login.this, Homepage.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "Passwords do not match!", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Account not found!", Toast.LENGTH_SHORT).show();
                                clear();
                            }
                        }
                    });
                }
            }
        });
    }

    private void clear()
    {
        username.getText().clear();
        password.getText().clear();
    }

    private boolean isNotEmpty(String Username, String Password)
    {
        if (TextUtils.isEmpty(Username))
        {
            username.setError("Enter Username!");
            return false;
        }
        else if (TextUtils.isEmpty(Password))
        {
            password.setError("Enter Password!");
            return false;
        }
        return true;
    }


}