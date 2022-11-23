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
    private EditText mEmail;
    private EditText mUserPassword;
    private RadioButton mFlexibleTypeWorkRadioButton;
    private RadioButton mStandardTypeWorkRadioButton;
    private Button mRegisterButton;
    private Button mWorkTimeLengthButton;
    private Button mStartStandardWorkTimeRegisterButton;
    private Button mWorkDaysInWeek;
    private RadioGroup mRadioGroupStandard;
    private RadioGroup mRadioGroupFlexible;

    Button RegiterButton;
    ListView mWorkDaysInWeeklistView;
    public CheckboxModel[] checkboxModelItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //initialize
        mUserName = (EditText) findViewById(R.id.userNameRegisteredEditText);
        mEmail = (EditText) findViewById(R.id.emailRegisteredEditText);
        mUserPassword = (EditText) findViewById(R.id.passwordRegisteredEditText);
        mRegisterButton = (Button) findViewById(R.id.registeredButton);
        mWorkTimeLengthButton = (Button) findViewById(R.id.length_work_hours_register_button);
        mFlexibleTypeWorkRadioButton = (RadioButton) findViewById(R.id.flexibleTypeWorkRegisterRadioButton);
        mStandardTypeWorkRadioButton = (RadioButton) findViewById(R.id.standardTypeWorkRegisterRadioButton);
        mStartStandardWorkTimeRegisterButton = (Button) findViewById(R.id.startStandardWorkTimeRegisterButton);
        mRadioGroupStandard = findViewById(R.id.idRadio_group1_standard_type_work);
        mRadioGroupFlexible =  findViewById(R.id.idRadio_group2_flexible_type_work);
        mWorkDaysInWeek = findViewById(R.id.work_days_in_week);
        mWorkDaysInWeeklistView = findViewById(R.id.work_days_in_week_list);

        DB_User_Operate DB = new DB_User_Operate(this);
        RegiterButton.setOnClickListener(view -> {
            User user = new User();
            user.setName(mUserName.getText().toString());
            if(user.getName().equals("")||user.getName().equals(""))
                Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else{
                Boolean checkuserpass = DB.checkUsernameExist(user.getName());
                if(checkuserpass==true){
                    Toast.makeText(RegisterActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                    Intent intent  = new Intent(getApplicationContext(), SelectObjectActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(RegisterActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}