package noobz69.sltc.pharmacyhubmobileclient.customerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import noobz69.sltc.pharmacyhubmobileclient.FirestoreInitializer;
import noobz69.sltc.pharmacyhubmobileclient.R;
import noobz69.sltc.pharmacyhubmobileclient.basic.Login;
import noobz69.sltc.pharmacyhubmobileclient.classes.Reminder;
import noobz69.sltc.pharmacyhubmobileclient.classes.ToolbarNavigation;

public class AddReminders extends ToolbarNavigation {
    ImageView backBtn;
    EditText title, description, date, time;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminders);

        backBtn = findViewById(R.id.backBtnAddReminders);
        saveBtn = findViewById(R.id.saveBtnAddReminders);
        title = findViewById(R.id.editTextTitleAddReminders);
        description = findViewById(R.id.editTextDescriptionAddReminders);
        date = findViewById(R.id.editTextDateAddReminders);
        time = findViewById(R.id.editTextTimeAddReminders);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Title = title.getText().toString();
                String Desc = description.getText().toString();
                String Date = date.getText().toString();
                String Time = time.getText().toString();

                Reminder reminder = new Reminder();
                reminder.setUsername(Login.UserId);
                reminder.setTitle(Title);
                reminder.setDescription(Desc);
                reminder.setDate(Date);
                reminder.setTime(Time);

                FirestoreInitializer firestoreInitializer = new FirestoreInitializer();
                firestoreInitializer.getFirestore().collection("reminders").add(reminder).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Added Successfully!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
                    }
                });
                clear();
            }
        });

    }
    void clear()
    {
        title.getText().clear();
        description.getText().clear();
        date.getText().clear();
        time.getText().clear();
    }
}