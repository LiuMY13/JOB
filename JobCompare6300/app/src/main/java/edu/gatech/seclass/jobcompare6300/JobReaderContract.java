package edu.gatech.seclass.jobcompare6300;

import android.provider.BaseColumns;

public class JobReaderContract {
    private JobReaderContract() {}

    /* Job Table */
    public static class JobEntry implements BaseColumns {
        public static final String TABLE_NAME = "JOBS";
        public static final String COLUMN_NAME_TITLE = "job_title";
        public static final String COLUMN_NAME_COMPANY = "company";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_COL = "col_index";
        public static final String COLUMN_NAME_SALARY = "annual_salary";
        public static final String COLUMN_NAME_BONUS = "annual_bonus";
        public static final String COLUMN_NAME_STOCK = "stock_options";
        public static final String COLUMN_NAME_HOME = "hbp_fund";
        public static final String COLUMN_NAME_HOLIDAY = "num_holidays";
        public static final String COLUMN_NAME_INTERNET = "monthly_internet";
        public static final String COLUMN_NAME_SCORE = "job_score";
        public static final String COLUMN_NAME_CURRENT = "current_job";

    }

    public static class WeightsEntry implements BaseColumns {
        public static final String TABLE_NAME = "WEIGHTS";
        public static final String COLUMN_NAME_WSALARY = "weight_salary";
        public static final String COLUMN_NAME_WBONUS = "weight_bonus";
        public static final String COLUMN_NAME_WSTOCK = "weight_stock";
        public static final String COLUMN_NAME_WHBP = "weight_hbp";
        public static final String COLUMN_NAME_WHOLIDAY = "weight_holiday";
        public static final String COLUMN_NAME_WINTERNET = "weight_internet";
    }
}
