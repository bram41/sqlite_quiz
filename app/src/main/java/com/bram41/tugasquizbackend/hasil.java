package com.bram41.tugasquizbackend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bram41.tugasquizbackend.util.SqliteHelper;

public class hasil extends AppCompatActivity {
    TextView hasil_txt, txt_benar, txt_salah;
    int jumlah_soal, benar, salah, nilai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        SqliteHelper db=new SqliteHelper(this);

        hasil_txt = findViewById(R.id.hasil);
        txt_benar = findViewById(R.id.jawaban_benar);
        txt_salah = findViewById(R.id.jawaban_salah);

        Bundle b = getIntent().getExtras();

        jumlah_soal = db.rowcount();

        int score= b.getInt("score");

        salah = jumlah_soal - score;
        nilai = 100 / jumlah_soal;
        benar = score * nilai;
        txt_benar.setText("Jawaban benar = "+ score);
        txt_salah.setText("Jawaban salah = "+ salah);
        hasil_txt.setText("Nilai = "+benar);
    }
    public void ulang(View view){
        Intent intent = new Intent(hasil.this, soal.class);
        startActivity(intent);
    }
}
