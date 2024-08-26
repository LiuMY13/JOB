package edu.gatech.seclass.jobcompare6300;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.gatech.seclass.jobcompare6300.util.ToastUtil;

public class compare_offers_tableActivity extends Activity {
    private Button mBtnCompareJobsTableBackMain;
    private Button mBtnCompareJobsTableBackCompare;
    private TextView compare_job_table_title_1;
    private TextView compare_job_table_title_2;
    private TextView compare_job_table_company_1;
    private TextView compare_job_table_company_2;
    private TextView compare_job_table_location_1;
    private TextView compare_job_table_location_2;
    private TextView compare_job_table_yearly_salary_1;
    private TextView compare_job_table_yearly_salary_2;
    private TextView compare_job_table_yearly_bonus_1;
    private TextView compare_job_table_yearly_bonus_2;
    private TextView compare_job_table_stock_options_1;
    private TextView compare_job_table_stock_options_2;
    private TextView compare_job_table_hbp_fund_1;
    private TextView compare_job_table_hbp_fund_2;
    private TextView compare_job_table_num_holidays_1;
    private TextView compare_job_table_num_holidays_2;
    private TextView compare_job_table_monthly_interval_1;
    private TextView compare_job_table_monthly_interval_2;
    //private TextView radioText=(TextView)view.findViewById(R.id.tv_check_text);

    private List<String> selectedItems;
    //= getIntent().getStringArrayListExtra("selectedItems");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_offers_table);
        Intent intent = getIntent();
        selectedItems = intent.getStringArrayListExtra("selectedItems");
        if (selectedItems != null) {
            for (String item : selectedItems) {
                Log.d("Selected Item", item);
            }
        } else {
            Log.e("Error", "No selected items found");
        }
        //0序号，1:title，2:company,3:score,4:location,5:col_index,6:annual_salary,7:annual_bonus
        //8:stock_options,9:hbp_fund,10:num_holidays,11:monthly_interval,12:current_job
        /**
        listText.add(""+i+","+allJobs.get(i).getJob_title()+","+allJobs.get(i).getCompany()+","+allJobs.get(i).getJob_score()
                +","+allJobs.get(i).getLocation()+","+allJobs.get(i).getCol_index()+","+allJobs.get(i).getAnnual_salary()
                +","+allJobs.get(i).getAnnual_bonus()+","+allJobs.get(i).getStock_options()+","+allJobs.get(i).getHbp_fund()
                +","+allJobs.get(i).getNum_holidays()+","+allJobs.get(i).getMonthly_internet()+","+allJobs.get(i).isCurrent_job()
        );
         **/
        mBtnCompareJobsTableBackMain = findViewById(R.id.compare_job_table_back_main);
        mBtnCompareJobsTableBackCompare = findViewById(R.id.compare_job_table_back_compare);

        compare_job_table_title_1 = findViewById(R.id.compare_job_table_title1);
        compare_job_table_title_2 = findViewById(R.id.compare_job_table_title2);

        compare_job_table_company_1 = findViewById(R.id.compare_job_table_company1);
        compare_job_table_company_2 = findViewById(R.id.compare_job_table_company2);

        compare_job_table_location_1 = findViewById(R.id.compare_job_table_location1);
        compare_job_table_location_2 = findViewById(R.id.compare_job_table_location2);

        compare_job_table_yearly_salary_1 = findViewById(R.id.compare_job_table_yearly_salary1);
        compare_job_table_yearly_salary_2 = findViewById(R.id.compare_job_table_yearly_salary2);

        compare_job_table_yearly_bonus_1 = findViewById(R.id.compare_job_table_yearly_bonus1);
        compare_job_table_yearly_bonus_2 = findViewById(R.id.compare_job_table_yearly_bonus2);

        compare_job_table_stock_options_1 = findViewById(R.id.compare_job_table_stock_options1);
        compare_job_table_stock_options_2 = findViewById(R.id.compare_job_table_stock_options2);

        compare_job_table_hbp_fund_1 = findViewById(R.id.compare_job_table_hbp_fund1);
        compare_job_table_hbp_fund_2 = findViewById(R.id.compare_job_table_hbp_fund2);

        compare_job_table_num_holidays_1 = findViewById(R.id.compare_job_table_num_holidays1);
        compare_job_table_num_holidays_2 = findViewById(R.id.compare_job_table_num_holidays2);

        compare_job_table_monthly_interval_1 = findViewById(R.id.compare_job_table_monthly_interval1);
        compare_job_table_monthly_interval_2 = findViewById(R.id.compare_job_table_monthly_interval2);

        String[] compare_job_1 = selectedItems.get(0).split(",");
        String[] compare_job_2 = selectedItems.get(1).split(",");
        /**
         listText.add(""+i+","+allJobs.get(i).getJob_title()+","+allJobs.get(i).getCompany()+","+allJobs.get(i).getJob_score()
         +","+allJobs.get(i).getLocation()+","+allJobs.get(i).getCol_index()+","+allJobs.get(i).getAnnual_salary()
         +","+allJobs.get(i).getAnnual_bonus()+","+allJobs.get(i).getStock_options()+","+allJobs.get(i).getHbp_fund()
         +","+allJobs.get(i).getNum_holidays()+","+allJobs.get(i).getMonthly_internet()+","+allJobs.get(i).isCurrent_job()
         );
         //0序号，1:title，2:company,3:score,4:location,5:col_index,6:annual_salary,7:annual_bonus
         //8:stock_options,9:hbp_fund,10:num_holidays,11:monthly_interval,12:current_job
         **/
        compare_job_table_title_1.setText(compare_job_1[1]);
        compare_job_table_title_2.setText(compare_job_2[1]);
        compare_job_table_company_1.setText(compare_job_1[2]);
        compare_job_table_company_2.setText(compare_job_2[2]);

        compare_job_table_location_1.setText(compare_job_1[4]);
        compare_job_table_location_2.setText(compare_job_2[4]);

        compare_job_table_yearly_salary_1.setText(compare_job_1[6]);
        compare_job_table_yearly_salary_2.setText(compare_job_2[6]);

        compare_job_table_yearly_bonus_1.setText(compare_job_1[7]);
        compare_job_table_yearly_bonus_2.setText(compare_job_2[7]);

        compare_job_table_stock_options_1.setText(compare_job_1[8]);
        compare_job_table_stock_options_2.setText(compare_job_2[8]);

        compare_job_table_hbp_fund_1.setText(compare_job_1[9]);
        compare_job_table_hbp_fund_2.setText(compare_job_2[9]);

        compare_job_table_num_holidays_1.setText(compare_job_1[10]);
        compare_job_table_num_holidays_2.setText(compare_job_2[10]);

        compare_job_table_monthly_interval_1.setText(compare_job_1[11]);
        compare_job_table_monthly_interval_2.setText(compare_job_2[11]);



        mBtnCompareJobsTableBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String job_compare_back = "Back to the main menu";
                ToastUtil.showMsg(compare_offers_tableActivity.this, job_compare_back);
                Intent intent = null;
                intent = new Intent(compare_offers_tableActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mBtnCompareJobsTableBackCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String job_compare_back = "Compare again";
                ToastUtil.showMsg(compare_offers_tableActivity.this, job_compare_back);
                Intent intent = null;
                intent = new Intent(compare_offers_tableActivity.this, compare_offersActivity.class);
                startActivity(intent);
            }
        });
    }

}
