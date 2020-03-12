package com.bram41.tugasquizbackend;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bram41.tugasquizbackend.util.Adapter;
import com.bram41.tugasquizbackend.util.Data;
import com.bram41.tugasquizbackend.util.SessionManager;
import com.bram41.tugasquizbackend.util.SqliteHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class home extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> itemList = new ArrayList<Data>();
    Adapter adapter;
    SqliteHelper SQLite = new SqliteHelper(this);
    SessionManager session;

    public static final String TAG_ID = "id";
    public static final String TAG_SOAL = "soal";
    public static final String TAG_BENAR = "benar";
    public static final String TAG_PERTAMA = "pertama";
    public static final String TAG_KEDUA = "kedua";
    public static final String TAG_KETIGA = "ketiga";
    public static final String TAG_KEEMPAT = "keempat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        session = new SessionManager(getApplicationContext());

        session.checkLogin();

        SQLite = new SqliteHelper(getApplicationContext());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        listView = (ListView) findViewById(R.id.list_view);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this, add_edit.class);
                startActivity(intent);
            }
        });

        adapter = new Adapter(home.this, itemList);
        listView.setAdapter(adapter);

        // long press listview to show edit and delete
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub
                final String idx = itemList.get(position).getId();
                final String soal = itemList.get(position).getSoal();
                final String benar = itemList.get(position).getBenar();
                final String pertama = itemList.get(position).getPertama();
                final String kedua = itemList.get(position).getKedua();
                final String ketiga = itemList.get(position).getKetiga();
                final String keempat = itemList.get(position).getKeempat();

                final CharSequence[] dialogitem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(home.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                Intent intent = new Intent(home.this, add_edit.class);
                                intent.putExtra(TAG_ID, idx);
                                intent.putExtra(TAG_SOAL, soal);
                                intent.putExtra(TAG_BENAR, benar);
                                intent.putExtra(TAG_PERTAMA, pertama);
                                intent.putExtra(TAG_KEDUA, kedua);
                                intent.putExtra(TAG_KETIGA, ketiga);
                                intent.putExtra(TAG_KEEMPAT, keempat);
                                startActivity(intent);
                                break;
                            case 1:
                                SQLite.delete(Integer.parseInt(idx));
                                itemList.clear();
                                getAllData();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });

        getAllData();

    }

    public void onBackPressed(){
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                session.logoutUser();
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
    }
    private void getAllData() {
        ArrayList<HashMap<String, String>> row = SQLite.getAllData();

        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).get(TAG_ID);
            String soal = row.get(i).get(TAG_SOAL);
            String benar = row.get(i).get(TAG_BENAR);
            String pertama = row.get(i).get(TAG_PERTAMA);
            String kedua = row.get(i).get(TAG_KEDUA);
            String ketiga = row.get(i).get(TAG_KETIGA);
            String keempat = row.get(i).get(TAG_KEEMPAT);

            Data data = new Data();

            data.setId(id);
            data.setSoal(soal);
            data.setBenar(benar);
            data.setPertama(pertama);
            data.setKedua(kedua);
            data.setKetiga(ketiga);
            data.setKeempat(keempat);

            itemList.add(data);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList.clear();
        getAllData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            session.logoutUser();

            //User Logged in Successfully Launch You home screen activity
            Intent intent=new Intent(home.this,login.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp(){
        //code it to launch an intent to the activity you want
        finish();
        return true;
    }


}
