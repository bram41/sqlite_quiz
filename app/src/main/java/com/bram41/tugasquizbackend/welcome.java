package com.bram41.tugasquizbackend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bram41.tugasquizbackend.util.SessionManager;
import com.bram41.tugasquizbackend.util.SqliteHelper;

public class welcome extends AppCompatActivity {
    TextView tentang;
    int nomor, total;
    SqliteHelper db;
    int soaltotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tentang = findViewById(R.id.tentang);
        nomor = 1;
        tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomor++;
                Log.e("Submit", String.valueOf(nomor));
                if (nomor == 5){
                    Intent intent = new Intent(welcome.this, login.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void mulai(View view){
        db=new SqliteHelper(this);
        soaltotal = db.rowcount() ;
        if(soaltotal<1){
            Toast.makeText(getApplicationContext(),
                    "Mohon isi terlebih dahulu soal dan jawaban!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(welcome.this, soal.class);
            startActivity(intent);
        }
    }

}
