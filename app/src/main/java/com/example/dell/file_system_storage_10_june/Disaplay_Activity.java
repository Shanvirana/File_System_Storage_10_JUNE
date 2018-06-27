package com.example.dell.file_system_storage_10_june;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Dell on 23-06-2018.
 */

public class Disaplay_Activity extends AppCompatActivity{

int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_activity);

        final TextView name=findViewById(R.id.text_name);
        final TextView email=findViewById(R.id.Text_mail);
        final TextView mobile=findViewById(R.id.text_mobile);
        TextView state=findViewById(R.id.text_state);
        final TextView city=findViewById(R.id.text_city);
        Button back=findViewById(R.id.button_submit);


        name.setText(getIntent().getStringExtra("name"));
        email.setText(getIntent().getStringExtra("mail"));
        mobile.setText(getIntent().getStringExtra("phone"));
        state.setText(getIntent().getStringExtra("state"));
        city.setText(getIntent().getStringExtra("city"));


    }


}
