package noobz69.sltc.pharmacyhubmobileclient.customerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
import noobz69.sltc.pharmacyhubmobileclient.basic.Login;
import noobz69.sltc.pharmacyhubmobileclient.classes.ToolbarNavigation;

public class EditProfile extends ToolbarNavigation {
    ImageView editProfile;
    TextView editName, editEmail, editContact;
    TextView name, email, contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editProfile = findViewById(R.id.editProfileBtn);
        editName = findViewById(R.id.editNameBtnProfile);
        editEmail = findViewById(R.id.editEmailBtnProfile);
        editContact = findViewById(R.id.editContactBtnProfile);
        name = findViewById(R.id.textviewNameProfile);
        email = findViewById(R.id.textviewEmailProfile);
        contact = findViewById(R.id.textviewContactProfile);

        FirestoreInitializer firestoreInitializer = new FirestoreInitializer();
        DocumentReference documentReference = firestoreInitializer.getFirestore().collection("customerAccounts").document(Login.UserId);

        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                Map<String, Object> customerData = new HashMap<>(value.getData());

                name.setText(customerData.get("name").toString());
                email.setText(customerData.get("email").toString());
                contact.setText(customerData.get("contactNo").toString());
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Settings.this, Signup.class);
//                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Under construction", Toast.LENGTH_SHORT).show();

            }
        });

        editName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Settings.this, Signup.class);
//                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Under construction", Toast.LENGTH_SHORT).show();

            }
        });

        editEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Settings.this, Signup.class);
//                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Under construction", Toast.LENGTH_SHORT).show();

            }
        });

        editContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Settings.this, Signup.class);
//                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Under construction", Toast.LENGTH_SHORT).show();

            }
        });
    }
}