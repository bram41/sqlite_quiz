package com.bram41.tugasquizbackend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bram41.tugasquizbackend.util.Data;
import com.bram41.tugasquizbackend.util.SqliteHelper;

import java.util.List;

public class soal extends AppCompatActivity {

    List<Data> quesList;
    int score=0;
    int qid=0;
    int soaltotal = 0;
    Data currentQ;
    TextView txtQuestion;
    RadioButton rda, rdb, rdc, rdd;
    Button butNext;
    int nomor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);
        SqliteHelper db=new SqliteHelper(this);
        quesList=db.getAllQuestions();
        soaltotal = db.rowcount() ;
        nomor=0;
        currentQ=quesList.get(nomor);
        txtQuestion=(TextView)findViewById(R.id.textView1);
        rda=(RadioButton)findViewById(R.id.radio0);
        rdb=(RadioButton)findViewById(R.id.radio1);
        rdc=(RadioButton)findViewById(R.id.radio2);
        rdd=(RadioButton)findViewById(R.id.radio3);
        butNext=(Button)findViewById(R.id.button1);
        setQuestionView();
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
                grp.clearCheck();
                Log.d("yourans", currentQ.getBenar()+" "+answer.getText());
                if(currentQ.getBenar().equals(answer.getText()))
                {
                    score++;
                    Log.d("score", "Your score"+score);
                }

                nomor++;
                if(nomor<soaltotal){
                    currentQ=quesList.get(qid);
                    setQuestionView();
                }else{
                    Intent intent = new Intent(soal.this, hasil.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score); //Your score
                    intent.putExtras(b); //Put your score to your next Intent
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_quiz, menu);
        return true;
    }
    private void setQuestionView()
    {
        txtQuestion.setText(currentQ.getSoal());
        rda.setText(currentQ.getPertama());
        rdb.setText(currentQ.getKedua());
        rdc.setText(currentQ.getKetiga());
        rdd.setText(currentQ.getKeempat());
        qid++;
    }
}
