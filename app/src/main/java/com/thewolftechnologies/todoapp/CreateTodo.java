package com.thewolftechnologies.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.thewolftechnologies.todoapp.utills.APIService;
import com.thewolftechnologies.todoapp.utills.Network;
import com.thewolftechnologies.todoapp.utills.RetroInstance;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateTodo extends AppCompatActivity {
    private EditText et_todo;
    private Button btn_save_todo;
    Network mNetwork = new Network();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_todo);

        et_todo = findViewById(R.id.ed_todo);
        btn_save_todo = findViewById(R.id.btn_save_todo);
        btn_save_todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNetwork.isConnected(CreateTodo.this)) {
                    postTodo();
                } else {
                    Toast.makeText(CreateTodo.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void postTodo() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Map<String, String> fields = new HashMap<>();
        fields.put("name", et_todo.getText().toString());
        fields.put("state", "0");
        APIService apiService = RetroInstance.getRetrofit().create(APIService.class);
        Call<Void> call = apiService.createTodo(fields);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    //progressDialog.dismiss();
                    et_todo.setText("");
                    Toast.makeText(CreateTodo.this, "Todo saved Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateTodo.this, "Todo saved Failed"+response.message(), Toast.LENGTH_SHORT).show();

                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}