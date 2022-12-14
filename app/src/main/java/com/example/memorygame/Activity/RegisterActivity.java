package com.example.memorygame.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memorygame.Database.DB_Instance;
import com.example.memorygame.Database.Entity.User;
import com.example.memorygame.Database.Operate.DB_User_Operate;
import com.example.memorygame.GlobalObject;
import com.example.memorygame.R;

import java.util.ArrayList;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private EditText mUserName,age;
    private AutoCompleteTextView educationYear;
    private RadioGroup genderRadioGroup,workingStatusRadioGroup;
    private RadioButton genderRadioButton,workingStatusRadioButton;
    Button RegiterButton;
    DB_Instance DB_User_Operate;
    String[] education = new String[]{"未受教育", "國小", "國中", "高中（職）", "專科", "大學", "碩士", "博士"};
    ArrayAdapter<String> adapterItem;
    AutoCompleteTextView autoCompleteTextView;

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
        this.DB_User_Operate = new DB_Instance(this);
        this.adapterItem = new ArrayAdapter<>(this, R.layout.session_item);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_menu_popup_item,
                        education);

        educationYear.setAdapter(adapter);


        RegiterButton.setOnClickListener(v -> {
            String currentEducationValue = educationYear.getText().toString();
            String userName = mUserName.getText().toString();
            String currentAge = age.getText().toString();
            genderRadioButton = findViewById(genderRadioGroup.getCheckedRadioButtonId());
            workingStatusRadioButton = findViewById(workingStatusRadioGroup.getCheckedRadioButtonId());

            if (userName == null || userName.isEmpty()){
                Toast.makeText(RegisterActivity.this, "姓氏向未填寫", Toast.LENGTH_SHORT).show();
            return;
            }
            if (currentAge == null || currentAge.isEmpty()){
                Toast.makeText(RegisterActivity.this, "年齡向未填寫", Toast.LENGTH_SHORT).show();
                return;
            }
            if(genderRadioButton == null){
                Toast.makeText(RegisterActivity.this, "請選擇性別", Toast.LENGTH_SHORT).show();
                return;
            }
            if (workingStatusRadioButton==null)
            {
                Toast.makeText(RegisterActivity.this, "請選擇工作狀態", Toast.LENGTH_SHORT).show();
                return;
            }
            if (currentEducationValue == null || currentEducationValue.isEmpty()){
                Toast.makeText(RegisterActivity.this, "教育年數向未填寫", Toast.LENGTH_SHORT).show();
                return;
            }
            String currentGenderValue = genderRadioButton.getText().toString();
            Boolean currentworkingValue = workingStatusRadioButton.getText().toString().equals("是");
            User user = new User(userName,
                    Integer.parseInt(age.getText().toString()),
                    currentGenderValue,
                    currentEducationValue,
                    currentworkingValue?1:0,
                    new Date().toString()
                    );
            if (this.DB_User_Operate.checkNameExist(user)){
                Toast.makeText(RegisterActivity.this, "此姓氏已存在，請用別的姓氏，或者以已的資料的身份來進行遊戲", Toast.LENGTH_SHORT).show();
                return;
            }
            user.generateUUID();

            if(this.DB_User_Operate.insertData(user)){
                GlobalObject.getInstance().setSession(user);
                Toast.makeText(RegisterActivity.this, "註冊成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),SelectObjectActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}