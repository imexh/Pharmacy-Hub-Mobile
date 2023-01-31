package noobz69.sltc.pharmacyhubmobileclient.customerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import noobz69.sltc.pharmacyhubmobileclient.R;
import noobz69.sltc.pharmacyhubmobileclient.basic.Login;
import noobz69.sltc.pharmacyhubmobileclient.classes.ToolbarNavigation;

public class Homepage extends ToolbarNavigation {

    TextView usernameView;
    ImageView prescriptionBtn, pharmaciesBtn, groceriesBtn, profileBtn, chatsBtn, settingsBtn;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        activity = this;

        usernameView = findViewById(R.id.usernameTextViewHomepage);
        prescriptionBtn = findViewById(R.id.uploadprescriptionBtnHomepage);
        pharmaciesBtn = findViewById(R.id.pharmaciesBtnHomepage);
        groceriesBtn = findViewById(R.id.groceriesBtnHomepage);
        profileBtn = findViewById(R.id.profileBtnHomepage);
//        chatsBtn = findViewById(R.id.chatsBtnHomepage);
        settingsBtn = findViewById(R.id.settingsBtnHomepage);

        usernameView.setText(Login.UserId);

        prescriptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, PrescriptionDetails.class);
                startActivity(intent);
            }
        });

        pharmaciesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, Pharmacies.class);
                startActivity(intent);
            }
        });

        groceriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, AllMedicines.class);
                startActivity(intent);
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, EditProfile.class);
                startActivity(intent);
            }
        });

//        chatsBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Homepage.this, Signup.class);
//                startActivity(intent);
//                Toast.makeText(getApplicationContext(), "Under construction", Toast.LENGTH_SHORT).show();
//
//            }
//        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, Settings.class);
                startActivity(intent);
            }
        });
    }


}