package edu.gatech.seclass.jobcompare6300;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Intermediate extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intermediate);

        TextView intermediateBanner_TxtView = findViewById(R.id.intermediateBanner);
        Intent intent = getIntent();
        if (intent != null) {
            String intermediateBanner_str = intent.getStringExtra("intermediateBanner");
            if (intermediateBanner_str != null) {
                intermediateBanner_TxtView.setText(intermediateBanner_str);
            }
        }

        Button ProceedBtn = (Button) findViewById(R.id.proceedButton);
        ProceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intermediate.this, JobOffer.class);
                intent.putExtra("jobBanner", "Current Job Details");
                startActivity(intent);
            }
        });
        Button backsBtn = (Button) findViewById(R.id.backButton);
        backsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intermediate.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void handleClick(View view) {
        // Handle button click here
    }
}
