package com.ait.sumit.ait;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import eu.chainfire.libsuperuser.Shell;

public class recovery_tools extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_tools);
        Button newbutton = (Button)findViewById(R.id.backup_recovery);
        newbutton.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        boolean suAvailable = false;
                        suAvailable = Shell.SU.available();
                        if (suAvailable) {
                            TextView textView = (TextView) findViewById(R.id.flash_text);
                            textView.setText(R.string.saved_recovery);
                            Shell.SU.run("mkdir /sdcard/AIT");
                            Shell.SU.run("dd if=/dev/block/mmcblk0p6 of=sdcard/AIT/recovery.img");
                        }
                        else {
                            TextView textView = (TextView) findViewById(R.id.flash_text);
                            textView.setTextColor(getResources().getColor(R.color.errorColor));
                            textView.setText(R.string.not_rooted);
                        }

                    }

                }
        );
        Button flash = (Button)findViewById(R.id.flash_recovery);

        flash.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        boolean suAvailable = false;
                        suAvailable = Shell.SU.available();
                        if (suAvailable) {
                            TextView textView = (TextView) findViewById(R.id.flash_text);
                            textView.setText(R.string.flashed_recovery);
                            Shell.SU.run("dd if=sdcard/AIT/recovery.img of=/dev/block/mmcblk0p6");

                        }
                        else {
                            TextView textView = (TextView) findViewById(R.id.flash_text);
                            textView.setTextColor(getResources().getColor(R.color.errorColor));
                            textView.setText(R.string.not_rooted);
                        }
                    }

                }
        );
    }
}
