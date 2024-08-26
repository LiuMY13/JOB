package edu.gatech.seclass.jobcompare6300;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;

public class comparison_settings extends AppCompatActivity{
    private EditText yearlySalaryEditText;
    private EditText yearlyBonusEditText;
    private EditText stockOptionsEditText;
    private EditText homeBuyingProgramFundEditText;
    private EditText personalChoiceHolidaysEditText;
    private EditText monthlyInternetStipendEditText;
    private Button editButton;
    private Button returnToMainMenuButton;
    private Button saveButton;
    private Button cancelButton;
    private TextView bannerTextView;
    private boolean isEditable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comparison_settings);

        // Initialize EditTexts
        yearlySalaryEditText = findViewById(R.id.editTextYearlySalary);
        yearlyBonusEditText = findViewById(R.id.editTextYearlyBonus);
        stockOptionsEditText = findViewById(R.id.editTextStockOptions);
        homeBuyingProgramFundEditText = findViewById(R.id.editTextHomeBuyingProgramFund);
        personalChoiceHolidaysEditText = findViewById(R.id.editTextPersonalChoiceHolidays);
        monthlyInternetStipendEditText = findViewById(R.id.editTextMonthlyInternetStipend);

        loadSavedSettings();

        setFieldsEditable(false);

        // Initialize Button
        editButton = findViewById(R.id.btnEdit);
        returnToMainMenuButton = findViewById(R.id.btnReturnToMainMenu);
        saveButton = findViewById(R.id.btnSave);
        cancelButton = findViewById(R.id.btnCancel);

        bannerTextView = findViewById(R.id.welcomeBanner);

        // Set OnClickListener for the Edit button
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleEditability();
            }
        });

        returnToMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(comparison_settings.this, MainActivity.class);
                startActivity(intent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code to save the changes
                saveChanges();
            }
        });

        // Set OnClickListener for the Cancel button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code to cancel the changes
                cancelChanges();
            }
        });
    }

    private void loadSavedSettings() {
        JobDatabase db = JobDatabase.getObj(getApplicationContext());
        ComparisonSettings weights = db.getComparisonSettings();
        //SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);

        if (weights != null) {
            yearlySalaryEditText.setText(String.valueOf(weights.getSalaryWeight()));
            yearlyBonusEditText.setText(String.valueOf(weights.getBonusWeight()));
            stockOptionsEditText.setText(String.valueOf(weights.getStockWeight()));
            homeBuyingProgramFundEditText.setText(String.valueOf(weights.getHomeBuyingProgramFundWeight()));
            personalChoiceHolidaysEditText.setText(String.valueOf(weights.getPersonalChoiceHolidaysWeight()));
            monthlyInternetStipendEditText.setText(String.valueOf(weights.getInternetStipendWeight()));

        }
    }

    private void setFieldsEditable(boolean editable) {
        yearlySalaryEditText.setFocusable(editable);
        yearlySalaryEditText.setFocusableInTouchMode(editable);
        yearlyBonusEditText.setFocusable(editable);
        yearlyBonusEditText.setFocusableInTouchMode(editable);
        stockOptionsEditText.setFocusable(editable);
        stockOptionsEditText.setFocusableInTouchMode(editable);
        homeBuyingProgramFundEditText.setFocusable(editable);
        homeBuyingProgramFundEditText.setFocusableInTouchMode(editable);
        personalChoiceHolidaysEditText.setFocusable(editable);
        personalChoiceHolidaysEditText.setFocusableInTouchMode(editable);
        monthlyInternetStipendEditText.setFocusable(editable);
        monthlyInternetStipendEditText.setFocusableInTouchMode(editable);
    }

    private void toggleEditability() {
        isEditable = !isEditable;
        setFieldsEditable(isEditable);
        bannerTextView.setText(isEditable ? "Adjust Settings" : "Comparison Settings");


        //yearlySalaryEditText.setEnabled(isEditable);
        //yearlyBonusEditText.setEnabled(isEditable);
        //stockOptionsEditText.setEnabled(isEditable);
        //homeBuyingProgramFundEditText.setEnabled(isEditable);
        //personalChoiceHolidaysEditText.setEnabled(isEditable);
        //monthlyInternetStipendEditText.setEnabled(isEditable);

        editButton.setVisibility(isEditable ? View.GONE : View.VISIBLE);
        returnToMainMenuButton.setVisibility(isEditable ? View.GONE : View.VISIBLE);
        saveButton.setVisibility(isEditable ? View.VISIBLE : View.GONE); // Show "Save" when editable
        cancelButton.setVisibility(isEditable ? View.VISIBLE : View.GONE);
    }

    private void returnToMainMenu() {

        finish();

    }

    private void saveChanges() {
        int salaryWeight = Integer.parseInt(yearlySalaryEditText.getText().toString());
        int bonusWeight = Integer.parseInt(yearlyBonusEditText.getText().toString());
        int stockWeight = Integer.parseInt(stockOptionsEditText.getText().toString());
        int homeBuyingProgramFundWeight = Integer.parseInt(homeBuyingProgramFundEditText.getText().toString());
        int personalChoiceHolidaysWeight = Integer.parseInt(personalChoiceHolidaysEditText.getText().toString());
        int monthlyInternetStipendWeight = Integer.parseInt(monthlyInternetStipendEditText.getText().toString());

        ComparisonSettings settings = new ComparisonSettings(salaryWeight, bonusWeight, stockWeight, homeBuyingProgramFundWeight, personalChoiceHolidaysWeight, monthlyInternetStipendWeight);

        // Implement saving logic here
        JobDatabase db = JobDatabase.getObj(getApplicationContext());
        db.updateComparisonSettings(settings);

        // Notify the user that changes have been saved
        Toast.makeText(this, "Settings saved.", Toast.LENGTH_SHORT).show();


        isEditable = false;
        setFieldsEditable(false);

        editButton.setVisibility(View.VISIBLE);
        returnToMainMenuButton.setVisibility(isEditable ? View.GONE : View.VISIBLE);
        saveButton.setVisibility(View.GONE);
        cancelButton.setVisibility(View.GONE);
    }

    private void cancelChanges() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Cancel?")
                .setMessage("Are you sure? Details will not be saved!")
                .setPositiveButton("Yes, exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isEditable = false;
                        setFieldsEditable(false);
                        loadSavedSettings();
                        editButton.setVisibility(View.VISIBLE);
                        returnToMainMenuButton.setVisibility(isEditable ? View.GONE : View.VISIBLE);
                        saveButton.setVisibility(View.GONE);
                        cancelButton.setVisibility(View.GONE);

                    }
                })
                .setNegativeButton("No, take me back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User chose to stay, dismiss the dialog and stay in the activity
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
