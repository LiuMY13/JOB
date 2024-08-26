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

public class compare_offers_currentjobActivity extends Activity {
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

    private List<String> currentJob;
    private List<String> job_offer;
    private JobDatabase jobDatabase;
    //= getIntent().getStringArrayListExtra("selectedItems");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_offers_table);

        Intent intent = getIntent();
        currentJob = intent.getStringArrayListExtra("currentJob");
        job_offer = intent.getStringArrayListExtra("job_offer");

        if (currentJob != null) {
            for (String item : currentJob) {
                Log.d("Selected Item", item);
            }
        } else {
            Log.e("Error", "No selected items found");
        }
        if (job_offer != null) {
            for (String item : job_offer) {
                Log.d("job_offer", item);
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
        mBtnCompareJobsTableBackCompare.setVisibility(View.GONE);

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

        //String[] compare_job_1 = selectedItems.get(0).split(",");
        //String[] compare_job_2 = selectedItems.get(1).split(",");
        /**
         0:job_offer.add(String.valueOf(findViewById(R.id.Title_value)));
         1:job_offer.add(String.valueOf(findViewById(R.id.Company_value)));
         2:job_offer.add(String.valueOf(findViewById(R.id.Location_value)));
         3:job_offer.add(String.valueOf(findViewById(R.id.CostOfLiving_value)));
         4:job_offer.add(String.valueOf(findViewById(R.id.YearlySalary_value)));
         5:job_offer.add(String.valueOf(findViewById(R.id.YearlyBonus_value)));
         6:job_offer.add(String.valueOf(findViewById(R.id.StockOptions_value)));
         7:job_offer.add(String.valueOf(findViewById(R.id.HomeBuyProgFund_value)));
         8:job_offer.add(String.valueOf(findViewById(R.id.PersonalChoiceHolidays_value)));
         9:job_offer.add(String.valueOf(findViewById(R.id.MonthlyInternetStipend_value)));
         **/
        //1是current job, 2是job offer
        compare_job_table_title_1.setText(currentJob.get(0)+"*");
        compare_job_table_title_2.setText(job_offer.get(0));
        compare_job_table_company_1.setText(currentJob.get(1)+"*");
        compare_job_table_company_2.setText(job_offer.get(1));

        compare_job_table_location_1.setText(currentJob.get(2));
        compare_job_table_location_2.setText(job_offer.get(2));

        compare_job_table_yearly_salary_1.setText(currentJob.get(4));
        compare_job_table_yearly_salary_2.setText(job_offer.get(4));

        compare_job_table_yearly_bonus_1.setText(currentJob.get(5));
        compare_job_table_yearly_bonus_2.setText(job_offer.get(5));

        compare_job_table_stock_options_1.setText(currentJob.get(6));
        compare_job_table_stock_options_2.setText(job_offer.get(6));

        compare_job_table_hbp_fund_1.setText(currentJob.get(7));
        compare_job_table_hbp_fund_2.setText(job_offer.get(7));

        compare_job_table_num_holidays_1.setText(currentJob.get(8));
        compare_job_table_num_holidays_2.setText(job_offer.get(8));

        compare_job_table_monthly_interval_1.setText(currentJob.get(9));
        compare_job_table_monthly_interval_2.setText(job_offer.get(9));



        mBtnCompareJobsTableBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String job_compare_back = "Back to the main menu";
                ToastUtil.showMsg(compare_offers_currentjobActivity.this, job_compare_back);
                Intent intent = null;
                intent = new Intent(compare_offers_currentjobActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
/**
        mBtnCompareJobsTableBackCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 设置按钮不可见
                mBtnCompareJobsTableBackCompare.setVisibility(View.GONE);

                String job_compare_back = "Compare again";
                ToastUtil.showMsg(compare_offers_currentjobActivity.this, job_compare_back);
                Intent intent = new Intent(compare_offers_currentjobActivity.this, JobOffer.class);
                startActivity(intent);
            }
        });
**/
    }


}
