package edu.gatech.seclass.jobcompare6300;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.google.android.material.textfield.TextInputEditText;


import edu.gatech.seclass.jobcompare6300.util.ToastUtil;

public class JobOffer extends Activity {
    private JobDatabase database;
    private Job currentJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_offers);
        setFieldsEditable(false);

        database = JobDatabase.getObj(JobOffer.this);
        currentJob = database.getCurrentJob();

        TextView JobBanner_TxtView = findViewById(R.id.JobBanner);
        Intent intent = getIntent();
        if (intent != null) {
            String jobBanner_str = intent.getStringExtra("jobBanner");
            if (jobBanner_str != null) {
                JobBanner_TxtView.setText(jobBanner_str);
            }
        }

        String jobBanner_text = (String) JobBanner_TxtView.getText();
        if (Objects.equals(jobBanner_text, "Current Job Details")){
            CurrentJobOffer();
        }
        else if (Objects.equals(jobBanner_text, "Job Offer Details")) {
            NewJobOffer();
        }


    }

    public void CurrentJobOffer() {
        if (null != currentJob) {
            ((TextView) findViewById(R.id.Title_value)).setText(currentJob.getJob_title());
            ((TextView) findViewById(R.id.Company_value)).setText(currentJob.getCompany());
            ((TextView) findViewById(R.id.Location_value)).setText(currentJob.getLocation());
            ((TextView) findViewById(R.id.CostOfLiving_value)).setText(String.valueOf(currentJob.getCol_index()));
            ((TextView) findViewById(R.id.YearlySalary_value)).setText(String.valueOf(currentJob.getAnnual_salary()));
            ((TextView) findViewById(R.id.YearlyBonus_value)).setText(String.valueOf(currentJob.getAnnual_bonus()));
            ((TextView) findViewById(R.id.StockOptions_value)).setText(String.valueOf(currentJob.getStock_options()));
            ((TextView) findViewById(R.id.HomeBuyProgFund_value)).setText(String.valueOf(currentJob.getHbp_fund()));
            ((TextView) findViewById(R.id.PersonalChoiceHolidays_value)).setText(String.valueOf(currentJob.getNum_holidays()));
            ((TextView) findViewById(R.id.MonthlyInternetStipend_value)).setText(String.valueOf(currentJob.getMonthly_internet()));
        }
        Button CancelBtn = findViewById(R.id.cancelButton);
        CancelBtn.setVisibility(View.GONE);
        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                showConfirmationDialog();
            }
        });

        Button EditBtn = findViewById(R.id.editButton);
        EditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String text = (String) EditBtn.getText();
                if(Objects.equals(text, "Edit")){
                    setFieldsEditable(true);
                    String save = "Save";
                    EditBtn.setText(save);
                    CancelBtn.setVisibility(View.VISIBLE);
                }
                else if ((Objects.equals(text, "Save"))){
                    if(SaveJob(true)) {
                        Intent intent = new Intent(JobOffer.this, MainActivity.class);
                        startActivity(intent);
                        String job_saved = "Job Details Saved!";
                        ToastUtil.showMsg(JobOffer.this, job_saved);
                    }
                }
            }
        });
    }

    public void NewJobOffer() {
        Button CancelBtn = findViewById(R.id.cancelButton);
        String cancel = "Cancel";
        CancelBtn.setText(cancel);
        CancelBtn.setVisibility(View.VISIBLE);
        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String text = (String) CancelBtn.getText();
                if(Objects.equals(text, "Compare Against Current")){
                    //To compare current job, new job offer
                    //I will modify it later. create a new compare_offer_currentjobActivity.java
                    Intent intent = new Intent(JobOffer.this, compare_offers_currentjobActivity.class);

                    List<String> currentJobDetails = new ArrayList<>();
                    currentJobDetails.add(currentJob.getJob_title());
                    currentJobDetails.add(currentJob.getCompany());
                    currentJobDetails.add(currentJob.getLocation());
                    currentJobDetails.add(String.valueOf(currentJob.getCol_index()));
                    currentJobDetails.add(String.valueOf(currentJob.getAnnual_salary()));
                    currentJobDetails.add(String.valueOf(currentJob.getAnnual_bonus()));
                    currentJobDetails.add(String.valueOf(currentJob.getStock_options()));
                    currentJobDetails.add(String.valueOf(currentJob.getHbp_fund()));
                    currentJobDetails.add(String.valueOf(currentJob.getNum_holidays()));
                    currentJobDetails.add(String.valueOf(currentJob.getMonthly_internet()));

                    List<String> job_offer = new ArrayList<>();
                    // 获取 TextInputEditText 对象
                    TextInputEditText titleEditText = findViewById(R.id.Title_value);
                    // 获取 TextInputEditText 中的文本内容
                    String title = titleEditText.getText().toString();
                    // 将文本内容添加到 job_offer 中
                    job_offer.add(title);
                    // 获取 Company_value 的文本内容并添加到 job_offer 中
                    TextInputEditText companyEditText = findViewById(R.id.Company_value);
                    String company = companyEditText.getText().toString();
                    job_offer.add(company);


                    TextInputEditText locationEditText = findViewById(R.id.Location_value);
                    String location = locationEditText.getText().toString();
                    job_offer.add(location);

                    TextInputEditText costOfLivingEditText = findViewById(R.id.CostOfLiving_value);
                    String costOfLiving = costOfLivingEditText.getText().toString();
                    job_offer.add(costOfLiving);

                    TextInputEditText yearlySalaryEditText = findViewById(R.id.YearlySalary_value);
                    String yearlySalary = yearlySalaryEditText.getText().toString();
                    job_offer.add(yearlySalary);

                    TextInputEditText yearlyBonusEditText = findViewById(R.id.YearlyBonus_value);
                    String yearlyBonus = yearlyBonusEditText.getText().toString();
                    job_offer.add(yearlyBonus);

                    TextInputEditText stockOptionsEditText = findViewById(R.id.StockOptions_value);
                    String stockOptions = stockOptionsEditText.getText().toString();
                    job_offer.add(stockOptions);

                    TextInputEditText homeBuyProgFundEditText = findViewById(R.id.HomeBuyProgFund_value);
                    String homeBuyProgFund = homeBuyProgFundEditText.getText().toString();
                    job_offer.add(homeBuyProgFund);

                    TextInputEditText personalChoiceHolidaysEditText = findViewById(R.id.PersonalChoiceHolidays_value);
                    String personalChoiceHolidays = personalChoiceHolidaysEditText.getText().toString();
                    job_offer.add(personalChoiceHolidays);

                    TextInputEditText monthlyInternetStipendEditText = findViewById(R.id.MonthlyInternetStipend_value);
                    String monthlyInternetStipend = monthlyInternetStipendEditText.getText().toString();
                    job_offer.add(monthlyInternetStipend);

                    //job_offer.add(String.valueOf(findViewById(R.id.Title_value)));
                    /**
                    job_offer.add(String.valueOf(findViewById(R.id.Company_value)));
                    job_offer.add(String.valueOf(findViewById(R.id.Location_value)));
                    job_offer.add(String.valueOf(findViewById(R.id.CostOfLiving_value)));
                    job_offer.add(String.valueOf(findViewById(R.id.YearlySalary_value)));
                    job_offer.add(String.valueOf(findViewById(R.id.YearlyBonus_value)));
                    job_offer.add(String.valueOf(findViewById(R.id.StockOptions_value)));
                    job_offer.add(String.valueOf(findViewById(R.id.HomeBuyProgFund_value)));
                    job_offer.add(String.valueOf(findViewById(R.id.PersonalChoiceHolidays_value)));
                    job_offer.add(String.valueOf(findViewById(R.id.MonthlyInternetStipend_value)));
                     **/

                    intent.putStringArrayListExtra("currentJob", new ArrayList<>(currentJobDetails));
                    intent.putStringArrayListExtra("job_offer", new ArrayList<>(job_offer));

                    startActivity(intent);
                }
                else {
                    showConfirmationDialog();
                }
            }
        });

        Button EditBtn = findViewById(R.id.editButton);
        String save = "Save";
        EditBtn.setText(save);
        setFieldsEditable(true);
        EditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String text = (String) EditBtn.getText();
                if (Objects.equals(text, "Save")) {
                    if (SaveJob(false)) {
                        setFieldsEditable(false);
                        String edit_another = "Edit Another Offer";
                        EditBtn.setText(edit_another);
                        String Compare = "Compare Against Current";
                        CancelBtn.setText(Compare);
                        String job_saved = "Job Details Saved!";
                        ToastUtil.showMsg(JobOffer.this, job_saved);
                    }
                } else if ((Objects.equals(text, "Edit Another Offer"))) {
                    Intent intent = new Intent(JobOffer.this, JobOffer.class);
                    intent.putExtra("jobBanner", "Job Offer Details");
                    startActivity(intent);
                }
            }
        });
    }

    public boolean SaveJob(boolean current) {
        TextView Title = findViewById(R.id.Title_value);
        TextView Company = findViewById(R.id.Company_value);
        TextView Location = findViewById(R.id.Location_value);
        TextView CostOfLiving = findViewById(R.id.CostOfLiving_value);
        TextView YearlySalary = findViewById(R.id.YearlySalary_value);
        TextView YearlyBonus = findViewById(R.id.YearlyBonus_value);
        TextView StockOptions = findViewById(R.id.StockOptions_value);
        TextView HomeBuyProgFund = findViewById(R.id.HomeBuyProgFund_value);
        TextView PersonalChoiceHolidays = findViewById(R.id.PersonalChoiceHolidays_value);
        TextView MonthlyInternetStipend = findViewById(R.id.MonthlyInternetStipend_value);

        String job_title = Title.getText().toString().trim();
        boolean ok = true;
        if ("".equals(job_title)) {
            Title.setError("Invalid Job Title");
            ok = false;
        }

        String job_company = Company.getText().toString().trim();
        if ("".equals(job_company)) {
            Company.setError("Invalid Company");
            ok = false;
        }

        String job_location = Location.getText().toString().trim();
        if ("".equals(job_location)) {
            Location.setError("Invalid Location");
            ok = false;
        }

        compute_index(job_location);

        int job_col_int = 0;
//        String job_col = CostOfLiving.getText().toString().trim();
//        try {
//            job_col_int = Integer.parseInt(job_col);
//            if (job_col_int < 0) {
//                CostOfLiving.setError("Invalid Cost of Living Index");
//                ok = false;
//            }
//        } catch (Exception e) {
//            CostOfLiving.setError("Invalid Cost of Living Index");
//            ok = false;
//        }

        double job_salary_dbl = 0.0;
        String job_salary = YearlySalary.getText().toString().trim();
        try {
            job_salary_dbl = Double.parseDouble(job_salary);
            if (job_salary_dbl < 0) {
                YearlySalary.setError("Invalid Yearly Salary");
                ok = false;
            }
        } catch (Exception e) {
            YearlySalary.setError("Invalid Yearly Salary");
            ok = false;
        }

        double job_bonus_dbl = 0.0;
        String job_bonus = YearlyBonus.getText().toString().trim();
        try {
            job_bonus_dbl = Double.parseDouble(job_bonus);
            if (job_bonus_dbl < 0) {
                YearlyBonus.setError("Invalid Yearly Bonus");
                ok = false;
            }
        } catch (Exception e) {
            YearlyBonus.setError("Invalid Yearly Bonus");
            ok = false;
        }

        double job_stock_dbl = 0.0;
        String job_stock = StockOptions.getText().toString().trim();
        try {
            job_stock_dbl = Double.parseDouble(job_stock);
            if (job_stock_dbl < 0) {
                StockOptions.setError("Invalid Stock Options");
                ok = false;
            }
        } catch (Exception e) {
            StockOptions.setError("Invalid Stock Options");
            ok = false;
        }

        double job_hbp_dbl = 0.0;
        String job_hbp = HomeBuyProgFund.getText().toString().trim();
        try {
            job_hbp_dbl = Double.parseDouble(job_hbp);
            if (job_hbp_dbl < 0 || (job_salary_dbl >= 0 && job_hbp_dbl > 0.15 * job_salary_dbl)) {
                HomeBuyProgFund.setError("Invalid Home Buying Fund");
                ok = false;
            }
        } catch (Exception e) {
            HomeBuyProgFund.setError("Invalid Home Buying Fund");
            ok = false;
        }

        int job_holiday_int = 0;
        String job_holiday = PersonalChoiceHolidays.getText().toString().trim();
        try {
            job_holiday_int = Integer.parseInt(job_holiday);
            if (job_holiday_int < 0 || job_holiday_int > 20) {
                PersonalChoiceHolidays.setError("Invalid Holidays");
                ok = false;
            }
        } catch (Exception e) {
            PersonalChoiceHolidays.setError("Invalid Holidays");
            ok = false;
        }

        double job_internet_dbl = 0.0;
        String job_internet = MonthlyInternetStipend.getText().toString().trim();
        try {
            job_internet_dbl = Double.parseDouble(job_internet);
            if (job_internet_dbl < 0 || job_internet_dbl > 75) {
                MonthlyInternetStipend.setError("Invalid Internet Stipend");
                ok = false;
            }
        } catch (Exception e) {
            MonthlyInternetStipend.setError("Invalid Internet Stipend");
            ok = false;
        }

        if (ok) {
             // Clear errors first
             Title.setError(null);
             Company.setError(null);
             Location.setError(null);
             CostOfLiving.setError(null);
             YearlySalary.setError(null);
             YearlyBonus.setError(null);
             StockOptions.setError(null);
             HomeBuyProgFund.setError(null);
             PersonalChoiceHolidays.setError(null);
             MonthlyInternetStipend.setError(null);
             
             // now save the job
             Job new_job = new Job(job_title, job_company, job_location, job_col_int, job_salary_dbl,
                     job_bonus_dbl, job_stock_dbl, job_hbp_dbl, job_holiday_int, job_internet_dbl, current);
             new_job.compute_score(database.getComparisonSettings());
             long uuid = database.addNewJob(current ? 1 : 0, job_title, job_company, job_location, job_salary_dbl,
                     job_bonus_dbl, job_stock_dbl, job_hbp_dbl, job_holiday_int, job_internet_dbl, new_job.getJob_score() );
             if (-1 == uuid) {
                 String msg = "Error in saving " + (current ? "current job" : "job offer");
                 ToastUtil.showMsg(JobOffer.this, msg);
                 ok = false;
             } else {
                 new_job.setUuid(uuid);
             }
        }
        return ok;

    }


    public void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Cancel?");
        builder.setMessage("Are you sure? Details will not be saved!");
        builder.setPositiveButton("Yes, exit.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(JobOffer.this, MainActivity.class);
                intent.putExtra("intermediateBanner", "Current Job Details");
                dialog.dismiss();
                startActivity(intent);
                String job_not_saved  = "Job Details Not Saved!";
                ToastUtil.showMsg(JobOffer.this,job_not_saved);
            }
        });

        builder.setNegativeButton("No, take me back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String job_not_saved  = "Continue to edit!";
                ToastUtil.showMsg(JobOffer.this,job_not_saved);
                dialog.dismiss(); // Close the dialog
            }
        });
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setFieldsEditable(boolean editable) {
        TextView Title = findViewById(R.id.Title_value);
        TextView Company = findViewById(R.id.Company_value);
        TextView Location = findViewById(R.id.Location_value);
        TextView CostOfLiving = findViewById(R.id.CostOfLiving_value);
        TextView YearlySalary = findViewById(R.id.YearlySalary_value);
        TextView YearlyBonus = findViewById(R.id.YearlyBonus_value);
        TextView StockOptions = findViewById(R.id.StockOptions_value);
        TextView HomeBuyProgFund = findViewById(R.id.HomeBuyProgFund_value);
        TextView PersonalChoiceHolidays = findViewById(R.id.PersonalChoiceHolidays_value);
        TextView MonthlyInternetStipend = findViewById(R.id.MonthlyInternetStipend_value);
        Title.setEnabled(editable);
        Company.setEnabled(editable);
        Location.setEnabled(editable);
        CostOfLiving.setEnabled(false);
        YearlySalary.setEnabled(editable);
        YearlyBonus.setEnabled(editable);
        StockOptions.setEnabled(editable);
        HomeBuyProgFund.setEnabled(editable);
        PersonalChoiceHolidays.setEnabled(editable);
        MonthlyInternetStipend.setEnabled(editable);
    }

    private static class FetchHtmlTask extends AsyncTask<String, Void, String> {
        private String location;
        private JobDatabase database;


        public FetchHtmlTask(JobDatabase database,String location) {
            this.location = location;
            this.database = database;
        }
        @Override
        protected String doInBackground(String... urls) {
            try {
                URL apiUrl = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) apiUrl.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                        StringBuilder content = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            content.append(line).append("\n");
                        }
                        return content.toString();
                    }
                } else {
                    System.out.println("Error response code: " + urlConnection.getResponseCode());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String htmlResponse) {
            // Handle the HTML response, e.g., update UI or call another method
            if (htmlResponse != null) {
                Document document = Jsoup.parse(htmlResponse);
                Elements rows = document.select("table.city-index tr.odd, table.city-index tr.even");
                for (Element row : rows) {
                    String city = row.select("td.city-name a").text();
                    String priceIndexStr = row.select("td.price-index").text();
                    if (city.matches("(?i).*" + location  + ".*")) {
                        try {
                            System.out.println("Price Index for " + location  + ": " + Integer.parseInt(priceIndexStr));
//                            job_col_int = Integer.parseInt(priceIndexStr);
                            database.updateCostOfLiving(location,Integer.parseInt(priceIndexStr));
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void compute_index(String loc) {
        // Call AsyncTask to fetch HTML content in the background
        new FetchHtmlTask(database, loc).execute("https://www.expatistan.com/cost-of-living/index");
    }
}