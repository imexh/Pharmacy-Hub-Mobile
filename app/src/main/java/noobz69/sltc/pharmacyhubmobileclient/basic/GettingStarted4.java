package noobz69.sltc.pharmacyhubmobileclient.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import noobz69.sltc.pharmacyhubmobileclient.R;

public class GettingStarted4 extends AppCompatActivity {
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started4);

        register = findViewById(R.id.gettingStarted4RegisterBtn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GettingStarted4.this, Signup.class);
                startActivity(intent);
            }
        });
    }
}