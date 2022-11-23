package com.example.memorygame.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memorygame.Database.Model.User;
import com.example.memorygame.Database.Operate.DB_User_Operate;
import com.example.memorygame.Object.CheckboxModel;
import com.example.memorygame.R;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegistrationActivity";
    private EditText mUserName;
    private RadioGroup genderRadioGroup,workingStatusRadioGroup;
    private RadioButton genderRadioButton,workingStatusRadioButton;
    private EditText age,educationYear;


    Button RegiterButton;
    ListView mWorkDaysInWeeklistView;
    public CheckboxModel[] checkboxModelItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.genderRadioGroup = findViewById(R.id.gender_radioGroup);
        this.genderRadioButton = findViewById(this.genderRadioGroup.getCheckedRadioButtonId());
        if (this.genderRadioButton != null)
        {

        }
    }
}