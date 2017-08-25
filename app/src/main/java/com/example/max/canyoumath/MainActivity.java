package com.example.max.canyoumath;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button submit;
    EditText input;
    TextView math;
    LinearLayout back;
    int answer, score, range =100;
    boolean started  = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = (Button)findViewById(R.id.submit);
        input = (EditText)findViewById(R.id.input);
        math = (TextView)findViewById(R.id.text);
        back = (LinearLayout)findViewById(R.id.background);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!started) {
                    submit.setText("Check");
                    started = true;
                    newAns();
                    input.setVisibility(View.VISIBLE);
                }else
                    if(input.getText().toString().trim().equals(""))
                        Toast.makeText(MainActivity.this, "Please input a value", Toast.LENGTH_SHORT).show();
                    else
                        check(Integer.parseInt(input.getText().toString().trim()));
            }
        });

        input.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if(input.getText().toString().trim().equals(""))
                        Toast.makeText(MainActivity.this, "Please input a value", Toast.LENGTH_SHORT).show();
                    else
                        check(Integer.parseInt(input.getText().toString().trim()));
                    return true;
                }
                return false;
            }
        });
    }


    private void check(int user) {
        if(user==answer) {
            back.setBackgroundColor(Color.GREEN);
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            newAns();
            score++;
        }else{
            back.setBackgroundColor(Color.RED);
            Toast.makeText(this, "Should be "+answer, Toast.LENGTH_SHORT).show();
            newAns();
        }
        input.setText("");

    }
    private void newAns(){
        int x,y;
        x = (int)(Math.random()*range*2)-range;
        y = (int)(Math.random()*range*2)-range;

        char operator='?';
        switch ((int)(Math.random()*4)){
            case 0:
                operator = '+';
                answer = x+y;
                break;
            case 1:
                operator = '-';
                answer = x-y;
                break;
            case 2:
                if(y==0)
                    y+=1;
                operator = '/';
                answer = x/y;
                break;
            case 3:
                operator = '*';
                answer = x*y;
                break;
        }
        math.setText(x+" "+operator+" "+y+" = ?");


    }

}
