package com.example.comp1786lecture3forminput;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
    private String[] workStatusArray = {
            "Full Time",
            "Part Time",
            "Unemployment"
    };

    private Spinner spinnerWorkStatus;
    private EditText nameInput;
    private EditText emailInput;
    private EditText phoneInput;
    private Button saveButton;
    private CheckBox checkBoxAgreement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerWorkStatus = (Spinner) findViewById(R.id.spinnerWorkStatus);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, workStatusArray);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWorkStatus.setAdapter((dataAdapter));

        saveButton = findViewById(R.id.buttonSave);

        saveButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                checkBoxAgreement = findViewById(R.id.checkboxAgreement);
                if (!checkBoxAgreement.isChecked()){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "You must agree to the terms", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                getInputs();
            }
        });
    }

    public void showDatePickerDialog(View v){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void updateDOB(LocalDate dob){
        TextView dobText = (TextView) findViewById(R.id.inputDob);
        dobText.setText(dob.toString());
    }

    private void getInputs(){
        nameInput = findViewById(R.id.inputName);
        emailInput = findViewById(R.id.inputEmail);
        phoneInput = findViewById(R.id.inputPhone);
        spinnerWorkStatus = findViewById(R.id.spinnerWorkStatus);

        String strName = nameInput.getText().toString();
        String strPhone = phoneInput.getText().toString();
        String strEmail = emailInput.getText().toString();
        String strWork = spinnerWorkStatus.getSelectedItem().toString();

        displayNextAlert(strName, strPhone, strEmail, strWork);
    }

    private void displayNextAlert(String strName, String strPhone, String strEmail, String strWork){
        new AlertDialog.Builder(this).setTitle("Details entered").setMessage("Details entered: " +
                "\n" + strName +
                "\n" + strPhone +
                "\n" + strEmail +
                "\n" + strWork
        ).setNeutralButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();
    }



}