package com.example.memorygame.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memorygame.Database.Model.User;
import com.example.memorygame.Database.Operate.DB_User_Operate;
import com.example.memorygame.GlobalObject;
import com.example.memorygame.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText mUserName,age,educationYear;
    private RadioGroup genderRadioGroup,workingStatusRadioGroup;
    private RadioButton genderRadioButton,workingStatusRadioButton;
    Button RegiterButton;
    DB_User_Operate DB_User_Operate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.genderRadioGroup = findViewById(R.id.gender_radioGroup);
        this.workingStatusRadioGroup = findViewById(R.id.workingStatus_radioGroup);
        this.mUserName = findViewById(R.id.name);
        this.age = findViewById(R.id.age);
        this.educationYear = findViewById(R.id.educationyear);
        this.RegiterButton = findViewById(R.id.registerbutton);
        this.DB_User_Operate = new DB_User_Operate(this);

        RegiterButton.setOnClickListener(v -> {
            String currentEducationValue = educationYear.getText().toString();
            String userName = mUserName.getText().toString();
            genderRadioButton = findViewById(genderRadioGroup.getCheckedRadioButtonId());
            workingStatusRadioButton = findViewById(workingStatusRadioGroup.getCheckedRadioButtonId());


            if (    genderRadioButton == null  ||
                    workingStatusRadioButton==null ||
                    currentEducationValue.isEmpty())
            {
                Toast.makeText(RegisterActivity.this, "Plear fill in the form", Toast.LENGTH_SHORT).show();
                return;
            }
            String currentGenderValue = genderRadioButton.getText().toString();
            Boolean currentworkingValue = workingStatusRadioButton.getText().toString().equals("是");
            User user = new User();
            user.setEducationLevel(currentEducationValue);
            user.setName(userName);
            user.setAge(Integer.parseInt(age.getText().toString()));
            user.setSex(currentGenderValue);
            user.setWorking(workingStatusRadioButton.getText().toString().equals("是"));
            if (this.DB_User_Operate.checkNameExist(user)){
                Toast.makeText(RegisterActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
            }
            if(this.DB_User_Operate.insertData(user)){
                GlobalObject.getInstance().setSession(user);
                Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),SelectObjectActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}