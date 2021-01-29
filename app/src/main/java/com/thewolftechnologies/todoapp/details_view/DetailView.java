package com.thewolftechnologies.todoapp.details_view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.thewolftechnologies.todoapp.R;

public class DetailView extends AppCompatActivity {
    private TextView tv_details_date,tv_details_status,tv_details_desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        Bundle bundle = getIntent().getExtras();
        if (bundle !=null) {
            String Details_date = bundle.getString("date");
            String Details_status = bundle.getString("status");
            String Details_desc = bundle.getString("desc");


            tv_details_desc = findViewById(R.id.tv_desc);
            tv_details_desc.setText(Details_desc);

            tv_details_date = findViewById(R.id.tv_date);
            tv_details_date.setText(Details_date);

            tv_details_status = findViewById(R.id.tv_status);
            tv_details_status.setText(Details_status);
        }
    }
}