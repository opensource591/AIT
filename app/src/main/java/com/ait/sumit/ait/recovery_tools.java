package com.ait.sumit.ait;

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
                        TextView textView = (TextView)findViewById(R.id.backup_recovery);
                        textView.setText("Saved to sdcard/AIT/Boot.img");
                        Shell.SU.run("mkdir /sdcard/AIT");
                        Shell.SU.run("dd if=/dev/block/mmcblk0p6 of=sdcard/AIT/recovery.img");

                    }

                }
        );
        Button flash = (Button)findViewById(R.id.flash_recovery);

        flash.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        TextView textView = (TextView)findViewById(R.id.flash_recovery);
                        textView.setText("Flashed boot.img from /sdcard/AIT/recovery.img");
                        Shell.SU.run("dd if=sdcard/AIT/boot.img of=/dev/block/mmcblk0p6");

                    }

                }
        );
    }
}