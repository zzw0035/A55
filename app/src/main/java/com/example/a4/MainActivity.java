package com.example.a4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TableRow;
import android.widget.TableLayout;
import android.database.Cursor;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    TextView text;
    TableLayout hist;
    EditText time;
    EditText usd;
    EditText item;
    Button add;
    Button sub;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db = new DatabaseHelper(this);
        text = findViewById(R.id.text);
        time = findViewById(R.id.date);
        usd = findViewById(R.id.dollar);
        item = findViewById(R.id.item);
        add = findViewById(R.id.add1);
        sub = findViewById(R.id.sub);
        hist = findViewById(R.id.tab);


        Click();
        History();
    }

    public void Click(){


        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        double dollar = Double.parseDouble(usd.getText().toString());
                        db.Create(time.getText().toString(), dollar, item.getText().toString());

                        History();
                        MainActivity.this.time.setText("");
                        MainActivity.this.usd.setText("");
                        MainActivity.this.item.setText("");
                    }
                }
        );

        sub.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        double dollar = - 1 * Double.parseDouble(usd.getText().toString());
                        db.Create(time.getText().toString(), dollar, item.getText().toString());
                        History();
                        MainActivity.this.time.setText("");
                        MainActivity.this.usd.setText("");
                        MainActivity.this.item.setText("");
                    }
                }
        );
    }

    public void History(){
        Double total = 0.0;
        Cursor data = db.getData();

        if (hist.getChildCount() > 0) {
            hist.removeAllViews();

        }

        while(data.moveToNext()){
            double dollar = Double.parseDouble(data.getString(2));
            total += dollar;
            TableRow r = new TableRow(this);
            TableRow.LayoutParams cl = new TableRow.LayoutParams();
            cl.weight = 1;

            TextView D = new TextView(this);
            D.setLayoutParams(cl);
            D.setText(data.getString(1));
            r.addView(D);

            TextView amount = new TextView(this);
            amount.setLayoutParams(cl);
            amount.setText(data.getString(2));
            r.addView(amount);

            TextView Cg = new TextView(this);
            Cg.setLayoutParams(cl);
            Cg.setText(data.getString(3));
            r.addView(Cg);

            hist.addView(r, new TableLayout.LayoutParams());

        }
        MainActivity.this.text.setText("Current Balance: $" + Double.toString(total));
    }
}