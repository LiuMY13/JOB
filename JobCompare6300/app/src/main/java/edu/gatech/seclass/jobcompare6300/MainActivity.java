package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.util.ToastUtil;

public class MainActivity extends AppCompatActivity {
    //找到compare的控制件
    private Button mBtnCompareJobs;

    private JobDatabase database;
    private List<Job> allJobs;
    private Job currentJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = JobDatabase.getObj(MainActivity.this);
        allJobs = database.getAllJobs();
        currentJob = database.getCurrentJob();

        Button CurrentJobBtn = (Button) findViewById(R.id.currentJobButton);
        CurrentJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (null == currentJob) {
                    Intent intent = new Intent(MainActivity.this, Intermediate.class);
                    intent.putExtra("intermediateBanner", "Current Job Details");
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, JobOffer.class);
                    intent.putExtra("jobBanner", "Current Job Details");
                    startActivity(intent);
                }
            }
        });

        Button JobOffersBtn = (Button) findViewById(R.id.jobOffersButton);
        JobOffersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this, JobOffer.class);
                intent.putExtra("jobBanner", "Job Offer Details");
                startActivity(intent);
            }
        });

        Button ComparisonSettingsBtn = (Button) findViewById(R.id.comparisonSettingsButton);
        ComparisonSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this, comparison_settings.class);
                intent.putExtra("jobBanner", "Comparison Settings");
                startActivity(intent);
            }
        });

        //找到控制键compare
        mBtnCompareJobs = findViewById(R.id.compareOffersButton);
        mBtnCompareJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null == currentJob) {
                    String job_compare  = "Enter Job offers first";
                    ToastUtil.showMsg(MainActivity.this,job_compare);
                }
                else {
                    String job_compare  = "Enter the Job offers compare page";
                    ToastUtil.showMsg(MainActivity.this,job_compare);
                    Intent intent = null;
                    intent = new Intent(MainActivity.this,compare_offersActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

}