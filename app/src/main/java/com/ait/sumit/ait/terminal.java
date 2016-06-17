package com.ait.sumit.ait;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import com.ait.sumit.ait.ShellExecuter;
public class terminal extends AppCompatActivity  {

    EditText input;
    Button btn;
    TextView out;
    String command;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_terminal);
input = (EditText)findViewById(R.id.txt);
btn = (Button)findViewById(R.id.btn);
out = (TextView)findViewById(R.id.out);
btn.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View arg0) {
// TODO Auto-generated method stub
ShellExecuter exe = new ShellExecuter();
command = input.getText().toString();

String outp = exe.Executer(command);
out.setText(outp);
out.setMovementMethod(new ScrollingMovementMethod());
Log.d("Output", outp);
    }
});

    }

}