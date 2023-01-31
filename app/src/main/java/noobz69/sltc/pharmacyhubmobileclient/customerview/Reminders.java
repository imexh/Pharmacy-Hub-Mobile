package noobz69.sltc.pharmacyhubmobileclient.customerview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import noobz69.sltc.pharmacyhubmobileclient.FirestoreInitializer;
import noobz69.sltc.pharmacyhubmobileclient.R;
import noobz69.sltc.pharmacyhubmobileclient.basic.Login;
import noobz69.sltc.pharmacyhubmobileclient.classes.Reminder;
import noobz69.sltc.pharmacyhubmobileclient.classes.ToolbarNavigation;

public class Reminders extends ToolbarNavigation {
    ImageView addBtn;
    RecyclerView rview;
    List<Reminder> reminderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        rview = (RecyclerView) findViewById(R.id.rv_reminders);
        addBtn = findViewById(R.id.addBtnReminders);
        reminderList = new ArrayList<>();

        FirestoreInitializer firestoreInitializer = new FirestoreInitializer();

        firestoreInitializer.getFirestore().collection("reminders").whereEqualTo("username", Login.UserId)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Reminder reminder = document.toObject(Reminder.class);
                                reminderList.add(reminder);
                            }
                            setAdapter();
                        }
                    }
                });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Reminders.this, AddReminders.class);
                startActivity(intent);
            }
        });
    }

    private void setAdapter()
    {
        ReminderListAdapter adapter = new ReminderListAdapter(reminderList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rview.setLayoutManager(layoutManager);
        rview.setItemAnimator(new DefaultItemAnimator());
        rview.setAdapter(adapter);
    }
}