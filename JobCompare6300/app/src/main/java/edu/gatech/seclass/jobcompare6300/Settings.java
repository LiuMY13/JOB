package edu.gatech.seclass.jobcompare6300;

public class Settings {
    private int weight_annual_salary;
    private int weight_annual_bonus;
    private int weight_stock_options;
    private int weight_hbp_fund;
    private int weight_num_holiday;
    private int weight_monthly_internet;

    public Settings(int weight_annual_salary, int weight_annual_bonus, int weight_stock_options, int weight_hbp_fund, int weight_num_holiday, int weight_monthly_internet) {
        this.weight_annual_salary = weight_annual_salary;
        this.weight_annual_bonus = weight_annual_bonus;
        this.weight_stock_options = weight_stock_options;
        this.weight_hbp_fund = weight_hbp_fund;
        this.weight_num_holiday = weight_num_holiday;
        this.weight_monthly_internet = weight_monthly_internet;
    }

    public Settings() {
        weight_annual_salary = 1;
        weight_annual_bonus = 1;
        weight_stock_options = 1;
        weight_hbp_fund = 1;
        weight_num_holiday = 1;
        weight_monthly_internet = 1;
    }

    public int getWeight_annual_salary() {
        return weight_annual_salary;
    }

    public int getWeight_annual_bonus() {
        return weight_annual_bonus;
    }

    public int getWeight_stock_options() {
        return weight_stock_options;
    }

    public int getWeight_hbp_fund() {
        return weight_hbp_fund;
    }

    public int getWeight_num_holiday() {
        return weight_num_holiday;
    }

    public int getWeight_monthly_internet() {
        return weight_monthly_internet;
    }
}
