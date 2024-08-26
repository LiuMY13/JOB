package edu.gatech.seclass.jobcompare6300;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

// Adapted from SQLite Android Docs: https://developer.android.com/training/data-storage/sqlite
public class JobDatabase extends SQLiteOpenHelper {
    private static JobDatabase dbObj;
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "jobs_db";

    private static final String SQL_CREATE_JOB_TABLE =
            "CREATE TABLE " + JobReaderContract.JobEntry.TABLE_NAME + " (" +
                    JobReaderContract.JobEntry._ID + " INTEGER PRIMARY KEY," +
                    JobReaderContract.JobEntry.COLUMN_NAME_TITLE + " TEXT," +
                    JobReaderContract.JobEntry.COLUMN_NAME_COMPANY + " TEXT," +
                    JobReaderContract.JobEntry.COLUMN_NAME_LOCATION + " TEXT," +
                    JobReaderContract.JobEntry.COLUMN_NAME_COL + " INT," +
                    JobReaderContract.JobEntry.COLUMN_NAME_SALARY + " REAL," +
                    JobReaderContract.JobEntry.COLUMN_NAME_BONUS + " REAL," +
                    JobReaderContract.JobEntry.COLUMN_NAME_STOCK + " REAL," +
                    JobReaderContract.JobEntry.COLUMN_NAME_HOME + " REAL," +
                    JobReaderContract.JobEntry.COLUMN_NAME_HOLIDAY + " INT," +
                    JobReaderContract.JobEntry.COLUMN_NAME_INTERNET + " REAL," +
                    JobReaderContract.JobEntry.COLUMN_NAME_SCORE + " REAL," +
                    JobReaderContract.JobEntry.COLUMN_NAME_CURRENT + " INT" +
                    ")";

    private static final String SQL_CREATE_WEIGHT_TABLE =
            "CREATE TABLE " + JobReaderContract.WeightsEntry.TABLE_NAME + " (" +
                    JobReaderContract.WeightsEntry._ID + " INTEGER PRIMARY KEY," +
                    JobReaderContract.WeightsEntry.COLUMN_NAME_WSALARY + " INT," +
                    JobReaderContract.WeightsEntry.COLUMN_NAME_WBONUS + " INT," +
                    JobReaderContract.WeightsEntry.COLUMN_NAME_WSTOCK + " INT," +
                    JobReaderContract.WeightsEntry.COLUMN_NAME_WHBP + " INT," +
                    JobReaderContract.WeightsEntry.COLUMN_NAME_WHOLIDAY + " INT," +
                    JobReaderContract.WeightsEntry.COLUMN_NAME_WINTERNET + " INT" +
                    ")";
    private static final String SQL_INIT_WEIGHT_TABLE =
            "INSERT INTO " + JobReaderContract.WeightsEntry.TABLE_NAME + " VALUES " +
                    " ( NULL, 1, 1, 1, 1, 1, 1 )";

    private static final String SQL_DELETE_JOB_TABLE =
            "DROP TABLE IF EXISTS " + JobReaderContract.JobEntry.TABLE_NAME;

    private static final String SQL_DELETE_WEIGHT_TABLE =
            "DROP TABLE IF EXISTS " + JobReaderContract.JobEntry.TABLE_NAME;

    public JobDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static JobDatabase getDbObj() {
        return dbObj;
    }

    public static void setDbObj(JobDatabase dbObj) {
        JobDatabase.dbObj = dbObj;
    }

    public static synchronized JobDatabase getObj(Context context) {
        if (null == dbObj) {
            dbObj = new JobDatabase(context.getApplicationContext());
        }
        return dbObj;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_JOB_TABLE);
        db.execSQL(SQL_CREATE_WEIGHT_TABLE);
        db.execSQL(SQL_INIT_WEIGHT_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_JOB_TABLE);
        db.execSQL(SQL_DELETE_WEIGHT_TABLE);
        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public List getAllJobs() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Job> allJobs = new ArrayList<Job>();
        String sortOrder = JobReaderContract.JobEntry.COLUMN_NAME_SCORE + " DESC";
        Cursor cursor = db.query(JobReaderContract.JobEntry.TABLE_NAME, null, null, null, null, null, sortOrder);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Job job = new Job(
                        cursor.getString(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_COMPANY)),
                        cursor.getString(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_LOCATION)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_COL)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_SALARY)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_BONUS)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_STOCK)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_HOME)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_HOLIDAY)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_INTERNET)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_CURRENT)) > 0
                );
                job.setUuid(cursor.getLong(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry._ID)));
                job.setJob_score(cursor.getLong(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_SCORE)));
                if (cursor.getInt(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_CURRENT)) > 0) {
                    allJobs.add(0, job);
                } else {
                    allJobs.add(job);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return allJobs;
    }

    public Job getCurrentJob() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = JobReaderContract.JobEntry.COLUMN_NAME_CURRENT + " = ?";
        String[] selectionArgs = { "1" };
        Cursor cursor = db.query(JobReaderContract.JobEntry.TABLE_NAME, null, selection, selectionArgs, null, null, null);
        Job job = null;
        assert cursor.getCount() <= 1;
        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            job = new Job(
                    cursor.getString(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_COMPANY)),
                    cursor.getString(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_LOCATION)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_COL)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_SALARY)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_BONUS)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_STOCK)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_HOME)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_HOLIDAY)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_INTERNET)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_CURRENT)) > 0
            );
            job.setUuid(cursor.getLong(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry._ID)));
            job.setJob_score(cursor.getLong(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry.COLUMN_NAME_SCORE)));
        }
        cursor.close();
        return job;
    }

    public long addNewJob(int current, String title, String company, String location, double yearly_salary, double yearly_bonus, double stock_options, double home_buy_prog_fund, int personal_choice_holidays, double monthly_internet_stipend, double score ) {

        long uuid = 0;

        if(current > 0) {
            SQLiteDatabase db = this.getReadableDatabase();
            String selection = JobReaderContract.JobEntry.COLUMN_NAME_CURRENT + " = ?";
            String[] selectionArgs = { "1" };
            Cursor cursor = db.query(JobReaderContract.JobEntry.TABLE_NAME, null, selection, selectionArgs, null, null, null);
            if (cursor.getCount() == 1) {
                cursor.moveToFirst();
                uuid = cursor.getLong(cursor.getColumnIndexOrThrow(JobReaderContract.JobEntry._ID));
            }
        }

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(JobReaderContract.JobEntry.COLUMN_NAME_TITLE, title);
        values.put(JobReaderContract.JobEntry.COLUMN_NAME_COMPANY, company);
        values.put(JobReaderContract.JobEntry.COLUMN_NAME_LOCATION, location);
//        values.put(JobReaderContract.JobEntry.COLUMN_NAME_COL, cost_of_living);
        values.put(JobReaderContract.JobEntry.COLUMN_NAME_SALARY, yearly_salary);
        values.put(JobReaderContract.JobEntry.COLUMN_NAME_BONUS, yearly_bonus);
        values.put(JobReaderContract.JobEntry.COLUMN_NAME_STOCK, stock_options);
        values.put(JobReaderContract.JobEntry.COLUMN_NAME_HOME, home_buy_prog_fund);
        values.put(JobReaderContract.JobEntry.COLUMN_NAME_HOLIDAY, personal_choice_holidays);
        values.put(JobReaderContract.JobEntry.COLUMN_NAME_INTERNET, monthly_internet_stipend);
        values.put(JobReaderContract.JobEntry.COLUMN_NAME_SCORE, score);
        values.put(JobReaderContract.JobEntry.COLUMN_NAME_CURRENT, current);

        if (uuid > 0) {
            String selection = JobReaderContract.JobEntry._ID + " = ?";
            String[] selectionArgs = { String.valueOf(uuid) };
            db.updateWithOnConflict(JobReaderContract.JobEntry.TABLE_NAME, values, selection, selectionArgs, SQLiteDatabase.CONFLICT_REPLACE);
        }
        else {
            uuid = db.insert(JobReaderContract.JobEntry.TABLE_NAME, null, values);

        }
        // db.close();
        return uuid;
    }

    public void updateCostOfLiving(String location, int cost_of_living) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(JobReaderContract.JobEntry.COLUMN_NAME_COL, cost_of_living);

        String selection = JobReaderContract.JobEntry.COLUMN_NAME_LOCATION + " = ?";
        String[] selectionArgs = { location };

        db.update(JobReaderContract.JobEntry.TABLE_NAME, values, selection, selectionArgs);
    }


    public ComparisonSettings getComparisonSettings() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(JobReaderContract.WeightsEntry.TABLE_NAME, null, null, null, null, null, null);
        ComparisonSettings settings = null;
        if (cursor.moveToFirst()) {
            int salaryWeightIndex = cursor.getColumnIndex(JobReaderContract.WeightsEntry.COLUMN_NAME_WSALARY);
            int bonusWeightIndex = cursor.getColumnIndex(JobReaderContract.WeightsEntry.COLUMN_NAME_WBONUS);
            int stockWeightIndex = cursor.getColumnIndex(JobReaderContract.WeightsEntry.COLUMN_NAME_WSTOCK);
            int homeBuyingProgramFundWeightIndex = cursor.getColumnIndex(JobReaderContract.WeightsEntry.COLUMN_NAME_WHBP);
            int personalChoiceHolidaysWeightIndex = cursor.getColumnIndex(JobReaderContract.WeightsEntry.COLUMN_NAME_WHOLIDAY);
            int internetStipendWeightIndex = cursor.getColumnIndex(JobReaderContract.WeightsEntry.COLUMN_NAME_WINTERNET);
            if (salaryWeightIndex >= 0 && bonusWeightIndex >= 0 /* check other indexes here */) {
                int salaryWeight = cursor.getInt(salaryWeightIndex);
                int bonusWeight = cursor.getInt(bonusWeightIndex);
                int stockWeight = cursor.getInt(stockWeightIndex);
                int homeBuyingProgramFundWeight = cursor.getInt(homeBuyingProgramFundWeightIndex);
                int personalChoiceHolidaysWeight = cursor.getInt(personalChoiceHolidaysWeightIndex);
                int internetStipendWeight = cursor.getInt(internetStipendWeightIndex);



                settings = new ComparisonSettings(salaryWeight, bonusWeight, stockWeight, homeBuyingProgramFundWeight, personalChoiceHolidaysWeight, internetStipendWeight);
            } else {

                settings = new ComparisonSettings(1, 1, 1, 1, 1, 1); // Example fallback to default values
            }
        } else {

            settings = new ComparisonSettings(1, 1, 1, 1, 1, 1); // Default values
        }

        cursor.close();
        return settings;

    }



    public void updateComparisonSettings(ComparisonSettings settings) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(JobReaderContract.WeightsEntry.COLUMN_NAME_WSALARY, settings.getSalaryWeight());
        values.put(JobReaderContract.WeightsEntry.COLUMN_NAME_WBONUS, settings.getBonusWeight());
        values.put(JobReaderContract.WeightsEntry.COLUMN_NAME_WSTOCK, settings.getStockWeight());
        values.put(JobReaderContract.WeightsEntry.COLUMN_NAME_WHBP, settings.getHomeBuyingProgramFundWeight());
        values.put(JobReaderContract.WeightsEntry.COLUMN_NAME_WHOLIDAY, settings.getPersonalChoiceHolidaysWeight());
        values.put(JobReaderContract.WeightsEntry.COLUMN_NAME_WINTERNET, settings.getInternetStipendWeight());

        int num_rows_updated = db.update(JobReaderContract.WeightsEntry.TABLE_NAME, values, null, null);
        if (num_rows_updated > 0) {
            // settings was successfully saved to database, now update all job_scores
            // simulate SQL TRIGGER statement, but here update all one-by-one
            List<Job> allJobs = this.getAllJobs();
            for (Job job: allJobs) {
                job.compute_score(settings);
                ContentValues job_values = new ContentValues();
                job_values.put(JobReaderContract.JobEntry.COLUMN_NAME_SCORE, job.getJob_score());
                String selection = JobReaderContract.JobEntry._ID + " = ?";
                String[] selectionArgs = { String.valueOf(job.getUuid()) };
                db.updateWithOnConflict(JobReaderContract.JobEntry.TABLE_NAME, job_values, selection, selectionArgs, SQLiteDatabase.CONFLICT_REPLACE);
            }
        }
    }

    public void clearDatabase() {
        SQLiteDatabase db = getWritableDatabase();
        // Drop or truncate your tables here
        String statement = "DELETE FROM " + JobReaderContract.JobEntry.TABLE_NAME;
        db.execSQL(statement);
        // or use TRUNCATE or other statements to clear data
        db.close();
    }
}
