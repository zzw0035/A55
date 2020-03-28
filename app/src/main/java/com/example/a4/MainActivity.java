package com.example.a4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.database.Cursor;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    TextView text;
    TextView hist;
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
        hist = findViewById(R.id.historyT);


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
        StringBuffer str = new StringBuffer();
        Cursor data = db.getData();

        while(data.moveToNext()){
            double dollar = Double.parseDouble(data.getString(2));
            if (dollar < 0) {

                str.append("Spent $" + (-1 * dollar) + " on " + data.getString(1)
                        + " for " + data.getString(3) + "\n");

                total += dollar;
            }
            else {
                str.append("Added $" + dollar + " on " + data.getString(1)
                        + " for " + data.getString(3) + "\n");

                total += dollar;
            }
        }
        MainActivity.this.text.setText("Current Balance: $" + Double.toString(total));
        MainActivity.this.hist.setText(str);
    }
}
