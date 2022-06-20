package com.example.jservice3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorActivity extends AppCompatActivity {

    EditText Tnamapemilik,Tjenisbarang,Tdetail,Tmetode,Tstatus;
    Button btn_save,btn_delete;

    ApiInterface apiInterface;

    String xid,namapemilik, jenisbarang, detail, metode,status;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Intent intent = getIntent();
        xid = intent.getStringExtra("id");
        namapemilik = intent.getStringExtra("namapemilik");
        jenisbarang = intent.getStringExtra("jenisbarang");
        detail = intent.getStringExtra("detail");
        metode = intent.getStringExtra("metode");
        status = intent.getStringExtra("status");



        Tnamapemilik = findViewById(R.id.nama);
        Tjenisbarang = findViewById(R.id.jenis);
        Tdetail = findViewById(R.id.detail);
        Tmetode = findViewById(R.id.metode);
        Tstatus = findViewById(R.id.status);
        btn_save = findViewById(R.id.save);
        btn_delete = findViewById(R.id.delete);

        condition(xid);


    }

    private void condition(String id) {

        if (id == null){
            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Add();
                }
            });
            btn_delete.setVisibility(View.GONE);
        }else{
            Tnamapemilik.setText(namapemilik);
            Tjenisbarang.setText(jenisbarang);
            Tdetail.setText(detail);
            Tmetode.setText(metode);
            Tstatus.setText(status);

            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Update();
                }
            });

            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Delete();
                }
            });

        }
    }

    private void Delete() {
        String yid = xid;

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call <User> call = apiInterface.delete(yid);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")){
                    Toast.makeText(EditorActivity.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(EditorActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(EditorActivity.this, t.toString(), Toast.LENGTH_SHORT).show();


            }
        });

    }



    private void Update() {
        String yid = xid;
        String namapemilik = Tnamapemilik.getText().toString();
        String jenisbarang = Tjenisbarang.getText().toString();
        String detail = Tdetail.getText().toString();
        String metode = Tmetode.getText().toString();
        String status = Tstatus.getText().toString();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call <User> call = apiInterface.update(yid,namapemilik,jenisbarang,detail,metode,status);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")){
                    Toast.makeText(EditorActivity.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(EditorActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(EditorActivity.this, t.toString(), Toast.LENGTH_SHORT).show();


            }
        });



    }

    private void Add() {
        String namapemilik = Tnamapemilik.getText().toString().trim();
        String jenisbarang = Tjenisbarang.getText().toString().trim();
        String detail = Tdetail.getText().toString().trim();
        String metode = Tmetode.getText().toString().trim();
        String status = Tstatus.getText().toString().trim();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call <User> call = apiInterface.insert(namapemilik,jenisbarang,detail,metode,status);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")){
                    Toast.makeText(EditorActivity.this, message.toString(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(EditorActivity.this, message.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(EditorActivity.this, t.toString(), Toast.LENGTH_SHORT).show();


            }
        });


    }




}