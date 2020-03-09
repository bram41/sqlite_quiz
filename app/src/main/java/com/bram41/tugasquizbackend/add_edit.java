package com.bram41.tugasquizbackend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bram41.tugasquizbackend.util.SqliteHelper;

public class add_edit extends AppCompatActivity {

    EditText txt_id, txt_soal, txt_benar, txt_pertama, txt_kedua, txt_ketiga, txt_keempat;
    Button btn_submit, btn_cancel;
    SqliteHelper SQLite = new SqliteHelper(this);
    String id, soal, benar, pertama, kedua, ketiga, keempat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_id = (EditText) findViewById(R.id.txt_id);
        txt_soal = (EditText) findViewById(R.id.txt_name);
        txt_benar = (EditText) findViewById(R.id.txt_jawaban4);
        txt_pertama = (EditText) findViewById(R.id.txt_jawaban1);
        txt_kedua = (EditText) findViewById(R.id.txt_jawaban2);
        txt_ketiga = (EditText) findViewById(R.id.txt_jawaban3);
        txt_keempat = (EditText) findViewById(R.id.txt_jawaban5);

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        id = getIntent().getStringExtra(home.TAG_ID);
        soal = getIntent().getStringExtra(home.TAG_SOAL);
        benar = getIntent().getStringExtra(home.TAG_BENAR);
        pertama = getIntent().getStringExtra(home.TAG_PERTAMA);
        kedua = getIntent().getStringExtra(home.TAG_KEDUA);
        ketiga = getIntent().getStringExtra(home.TAG_KETIGA);
        keempat = getIntent().getStringExtra(home.TAG_KEEMPAT);

        if (id == null || id == "") {
            setTitle("Add Data");
        } else {
            setTitle("Edit Data");
            txt_id.setText(id);
            txt_soal.setText(soal);
            txt_benar.setText(benar);
            txt_pertama.setText(pertama);
            txt_kedua.setText(kedua);
            txt_ketiga.setText(ketiga);
            txt_keempat.setText(keempat);
        }

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (txt_id.getText().toString().equals("")) {
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e){
                    Log.e("Submit", e.toString());
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blank();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                blank();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // Make blank all Edit Text
    private void blank() {
        txt_soal.requestFocus();
        txt_id.setText(null);
        txt_soal.setText(null);
        txt_benar.setText(null);
        txt_pertama.setText(null);
        txt_kedua.setText(null);
        txt_ketiga.setText(null);
        txt_keempat.setText(null);
    }


    // Save data to SQLite database
    private void save() {
        if (String.valueOf(txt_soal.getText()).equals(null) || String.valueOf(txt_soal.getText()).equals("") ||
                String.valueOf(txt_benar.getText()).equals(null) || String.valueOf(txt_benar.getText()).equals("") ||
                String.valueOf(txt_pertama.getText()).equals(null) || String.valueOf(txt_pertama.getText()).equals("") ||
                String.valueOf(txt_kedua.getText()).equals(null) || String.valueOf(txt_kedua.getText()).equals("") ||
                String.valueOf(txt_keempat.getText()).equals(null) || String.valueOf(txt_keempat.getText()).equals("") ||
                String.valueOf(txt_ketiga.getText()).equals(null) || String.valueOf(txt_ketiga.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Mohon masukkan semua data ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(
                    txt_soal.getText().toString().trim(),
                    txt_benar.getText().toString().trim(),
                    txt_pertama.getText().toString().trim(),
                    txt_kedua.getText().toString().trim(),
                    txt_ketiga.getText().toString().trim(),
                    txt_keempat.getText().toString().trim());
            blank();
            finish();
        }
    }

    // Update data in SQLite database
    private void edit() {
        if (String.valueOf(txt_soal.getText()).equals(null) || String.valueOf(txt_soal.getText()).equals("") ||
                String.valueOf(txt_benar.getText()).equals(null) || String.valueOf(txt_benar.getText()).equals("") ||
                String.valueOf(txt_pertama.getText()).equals(null) || String.valueOf(txt_pertama.getText()).equals("") ||
                String.valueOf(txt_kedua.getText()).equals(null) || String.valueOf(txt_kedua.getText()).equals("") ||
                String.valueOf(txt_keempat.getText()).equals(null) || String.valueOf(txt_keempat.getText()).equals("") ||
                String.valueOf(txt_ketiga.getText()).equals(null) || String.valueOf(txt_ketiga.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Mohon masukkan semua data ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.update(
                    Integer.parseInt(txt_id.getText().toString().trim()),
                    txt_soal.getText().toString().trim(),
                    txt_benar.getText().toString().trim(),
                    txt_pertama.getText().toString().trim(),
                    txt_kedua.getText().toString().trim(),
                    txt_ketiga.getText().toString().trim(),
                    txt_keempat.getText().toString().trim());
            blank();
            finish();
        }
    }
}

