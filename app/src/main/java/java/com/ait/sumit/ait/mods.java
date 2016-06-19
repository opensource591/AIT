package com.ait.sumit.ait;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import eu.chainfire.libsuperuser.Shell;

public class mods extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mods);

        Button enforcing = (Button)findViewById(R.id.selinux_enforcing);
        enforcing.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        boolean suAvailable = false;
                        suAvailable = Shell.SU.available();
                        if (suAvailable) {

                            TextView textView = (TextView) findViewById(R.id.changed_selinux);
                            textView.setText("changed to enforcing");
                            Shell.SU.run("setenforce 1");
                        }
                        else{

                            TextView textView = (TextView) findViewById(R.id.changed_selinux);
                            textView.setTextColor(getResources().getColor(R.color.errorColor));
                            textView.setText(R.string.not_rooted);                        }

                    }

                }
        );
        Button permssive = (Button)findViewById(R.id.selinux_permssive);
        permssive.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        boolean suAvailable = false;
                        suAvailable = Shell.SU.available();
                        if (suAvailable) {
                            TextView textView = (TextView) findViewById(R.id.changed_selinux);
                            textView.setText("changed to permssive");
                            Shell.SU.run("setenforce 0");

                        }
                        else {
                            TextView textView = (TextView) findViewById(R.id.changed_selinux);
                            textView.setTextColor(getResources().getColor(R.color.errorColor));
                            textView.setText(R.string.not_rooted);
                        }
                    }

                }
        );
    }
}
