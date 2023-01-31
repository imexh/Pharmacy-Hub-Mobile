package noobz69.sltc.pharmacyhubmobileclient.customerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import noobz69.sltc.pharmacyhubmobileclient.R;
import noobz69.sltc.pharmacyhubmobileclient.classes.ToolbarNavigation;

public class Contactus extends ToolbarNavigation {

    ImageView backBtn;
    Button send;
    EditText text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        backBtn = findViewById(R.id.backBtnContactus);
        send = findViewById(R.id.sendBtnContactUs);
        text = findViewById(R.id.editTextTitle);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = text.getText().toString();
                Toast.makeText(getApplicationContext(), "Under construction", Toast.LENGTH_SHORT).show();
            }
        });


    }
}