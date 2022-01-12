package com.example.jsonandasycntask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class UserDetail extends AppCompatActivity {
    ImageView avatarDetail;
    TextView usernameDetail, nameDetail, emailDetail, addressDetail, phoneDetail, companyDetail;
    UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        avatarDetail = findViewById(R.id.avatarDetail);
        usernameDetail = findViewById(R.id.usernameDetail);
        nameDetail = findViewById(R.id.nameDetail);
        emailDetail = findViewById(R.id.emailDetail);
        addressDetail = findViewById(R.id.addressDetail);
        phoneDetail = findViewById(R.id.phoneDetail);
        companyDetail = findViewById(R.id.companyDetail);

        Intent intent = getIntent();
        if(intent != null) {
            user = (UserModel) intent.getSerializableExtra("key1");
            Picasso.get().load(user.getAvatarDetail()).into(avatarDetail);
            usernameDetail.setText(user.getUsernameDetail());
            nameDetail.setText(user.getNameDetail());
            emailDetail.setText(user.getEmailDetail());
            addressDetail.setText(user.getAddressDetail());
            phoneDetail.setText(user.getPhoneDetail());
            companyDetail.setText(user.getCompanyDetail());
        }
    }
}