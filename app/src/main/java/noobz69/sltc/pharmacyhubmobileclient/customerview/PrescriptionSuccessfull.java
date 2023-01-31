package noobz69.sltc.pharmacyhubmobileclient.customerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import noobz69.sltc.pharmacyhubmobileclient.MainActivity;
import noobz69.sltc.pharmacyhubmobileclient.R;
import noobz69.sltc.pharmacyhubmobileclient.basic.GetStarted;

public class PrescriptionSuccessfull extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_successfull);

        new Timer().schedule(new TimerTask(){
            public void run() {
                PrescriptionSuccessfull.this.runOnUiThread(new Runnable() {
                    public void run() {
                        startActivity(new Intent(PrescriptionSuccessfull.this, Homepage.class));
                        finish();
                    }
                });
            }
        }, 2000);
    }
}