package noobz69.sltc.pharmacyhubmobileclient.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import noobz69.sltc.pharmacyhubmobileclient.R;

public class GettingStarted3 extends AppCompatActivity {
    TextView skipBtn;
    ImageView nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started3);

        skipBtn = findViewById(R.id.skipBtn);
        nextBtn = findViewById(R.id.nextBtn);

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GettingStarted3.this, GettingStarted4.class);
                startActivity(intent);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GettingStarted3.this, GettingStarted4.class);
                startActivity(intent);
            }
        });
    }
}