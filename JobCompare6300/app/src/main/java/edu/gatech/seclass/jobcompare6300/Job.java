package edu.gatech.seclass.jobcompare6300;

import android.os.AsyncTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class Job {
    private long uuid;
    private String job_title;
    private String company;
    private String location;
    private int col_index;
    private double annual_salary;
    private double annual_bonus;
    private double stock_options;
    private double hbp_fund;
    private int num_holidays;
    private double monthly_internet;
    private double job_score;
    private boolean current_job;

    public Job(String job_title, String company, String location, int col_index, double annual_salary, double annual_bonus, double stock_options, double hbp_fund, int num_holidays, double monthly_internet, boolean current_job) {
        // Check for invalid inputs like A3
        if (annual_salary < 0) throw new IllegalArgumentException("Yearly Salary less than 0");
        if (annual_bonus < 0) throw new IllegalArgumentException("Yearly Bonus less than 0");
        if (stock_options < 0) throw new IllegalArgumentException("Stock Options less than 0");
        if (hbp_fund < 0 || hbp_fund > 0.15 * annual_salary) throw new IllegalArgumentException("Home Buying Fund not in range of [0, 0.15*Annual Salary]");
        if (num_holidays < 0 || num_holidays > 20) throw new IllegalArgumentException("Personal Choice Holidays not in range of [0, 20]");
        if (monthly_internet < 0 || monthly_internet > 75) throw new IllegalArgumentException("Monthly Internet Stipend not in range of [0, 75]");

        this.job_title = job_title;
        this.company = company;
        this.location = location;
        this.col_index = col_index;
        this.annual_salary = annual_salary;
        this.annual_bonus = annual_bonus;
        this.stock_options = stock_options;
        this.hbp_fund = hbp_fund;
        this.num_holidays = num_holidays;
        this.monthly_internet = monthly_internet;
        this.current_job = current_job;
        this.job_score = 0;
    }

    public String getJob_title() {
        return job_title;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public int getCol_index() { return col_index; }

    public double getAnnual_salary() {
        return annual_salary;
    }

    public double getAnnual_bonus() {
        return annual_bonus;
    }

    public double getStock_options() {
        return stock_options;
    }

    public double getHbp_fund() {
        return hbp_fund;
    }

    public int getNum_holidays() {
        return num_holidays;
    }

    public double getMonthly_internet() {
        return monthly_internet;
    }

    public double getJob_score() {
        return job_score;
    }

    public boolean isCurrent_job() {
        return current_job;
    }

    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }

    public void setJob_score(double job_score) {
        this.job_score = job_score;
    }

    public void compute_score(ComparisonSettings weights) {
        double salary_score = annual_salary * col_index * weights.getSalaryWeight();
        double bonus_score = annual_bonus * col_index * weights.getBonusWeight();
        double stock_score = (stock_options / 3.0) * weights.getStockWeight();
        double hbp_score = hbp_fund * weights.getHomeBuyingProgramFundWeight();
        double holiday_score = ((double) (num_holidays * annual_salary * col_index) / 260.0) * weights.getPersonalChoiceHolidaysWeight();
        double internet_score = (monthly_internet * 12.0) * weights.getInternetStipendWeight();

        double total_score = salary_score + bonus_score + stock_score + hbp_score + holiday_score + internet_score;
        double total_weights = weights.getSum();
        this.job_score = total_score / total_weights;
    }

}
