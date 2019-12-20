package com.example.bmi;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.format;


public class MainActivity extends AppCompatActivity {

    EditText ht;
    EditText wt;
    Button calculate;
    Button reset;
    TextView result;
    TextView value;
    TextView suggest;
    private static final String TAG="myMessage";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    ht=(EditText)findViewById(R.id.height);
    wt=(EditText)findViewById(R.id.weight);
    calculate=(Button)findViewById(R.id.calc);
    reset=(Button)findViewById(R.id.clear);
    value=(TextView)findViewById(R.id.bmivalue);
    result=(TextView)findViewById(R.id.bmiresult);
    suggest=(TextView)findViewById(R.id.suggestion);
    try {
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e(TAG, "onClick");


                if ((ht.getText().toString()).equals("") || wt.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter details", Toast.LENGTH_LONG).show();
                } else {
                    float height = Float.parseFloat(ht.getText().toString());
                    float weight = Float.parseFloat(wt.getText().toString());

                    float resultvalue = weight / (height * height);

                    String refined_resultvalue = format("%,.2f", resultvalue);
                    value.setText("BMI " + refined_resultvalue);

                    if (resultvalue < 18.5) {

                        float sug= (float) (18.5-resultvalue);
                        String refined_sug=format("%,.2f",sug);
                        result.setText("Under Weight");
                        result.setTextColor(Color.YELLOW);
                        suggest.setText("Increase"+refined_sug);
                        Toast.makeText(MainActivity.this, "Eat more", Toast.LENGTH_SHORT).show();

                    }
                    if (resultvalue >= 18.5 && resultvalue <= 24.9) {

                        result.setText("Normal");
                        result.setTextColor(Color.GREEN);
                        suggest.setText("No sugesstion");
                        Toast.makeText(MainActivity.this, "Well done Keep it up", Toast.LENGTH_SHORT).show();
                    }
                    if (resultvalue > 25.0) {
                        float sug=(float)(resultvalue-25.0);
                        String refined_sug=format("%,.2f",sug);
                        result.setText("Over Weight");
                        result.setTextColor(Color.RED);
                        suggest.setText("Reduce"+refined_sug);
                        Toast.makeText(MainActivity.this, "Exercise more", Toast.LENGTH_SHORT).show();
                    }


                }
            }

        });
    }catch (NumberFormatException e){
        Toast.makeText(MainActivity.this,"Error Occured",Toast.LENGTH_LONG).show();
    }

    reset.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            result.setText("");
            value.setText("");
            suggest.setText("");
            ht.setText("");
            wt.setText("");
        }
    });


    }


}
